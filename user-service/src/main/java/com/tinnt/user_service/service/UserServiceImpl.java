package com.tinnt.user_service.service;

import java.util.ArrayList;
import java.util.List;

import com.tinnt.user_service.client.TaskClient;
import com.tinnt.user_service.client.ValidateClient;
import com.tinnt.user_service.dto.request.UserRequestBodyDTO;
import com.tinnt.user_service.dto.request.UserRequestParamDto;
import com.tinnt.user_service.dto.response.TaskResponseDto;
import com.tinnt.user_service.dto.response.UserResponseDTO;
import com.tinnt.user_service.entity.UserEntity;
import com.tinnt.user_service.mapper.UserMapper;
import com.tinnt.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {
	private static final String MONGODB_COLLECTION = "users";


	@Autowired
	private MongoTemplate mongoTemplate;


	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ValidateClient validate;

	@Override
	public List<UserEntity> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public UserResponseDTO getDetailluser(String id) {
		Aggregation agg = Aggregation.newAggregation(Aggregation.match(Criteria.where("_id").is(id)));
		AggregationResults<UserEntity> results = mongoTemplate.aggregate(agg, MONGODB_COLLECTION, UserEntity.class);
		// Get user entity
		UserEntity ue = results.getUniqueMappedResult();
		UserResponseDTO dto = UserMapper.toDTO(ue);
		return dto;
	}

	@Override
	public UserEntity createUser(UserRequestBodyDTO dto) {
		UserEntity ue = new UserEntity();
		ue.setName(dto.getName());
		ue.setAge(dto.getAge());
		ue.setUsername(dto.getUsername());
		return userRepository.save(ue);
	}

	@Override
	public UserEntity updateUser(UserRequestBodyDTO dto, String id) throws Exception {
		Aggregation agg = Aggregation.newAggregation(Aggregation.match(Criteria.where("_id").is(id)));
		AggregationResults<UserEntity> results = mongoTemplate.aggregate(agg, "users", UserEntity.class);

		if(results == null) {
			throw new Exception("User not found");
		}

		UserEntity ue = results.getUniqueMappedResult();
		ue.setName(dto.getName());
		ue.setAge(dto.getAge());
		ue.setUsername(dto.getUsername());

		return userRepository.save(ue);
		
	}

	@Override
	public Page<UserEntity> findUsers(UserRequestParamDto params, Pageable pageable) {
		List<Criteria> filters = new ArrayList<>();

		if (StringUtils.hasText(params.getUsername())) {
			filters.add(Criteria.where("username").regex(params.getUsername(), "i"));
		}

		if (StringUtils.hasText(params.getName())) {
			filters.add(Criteria.where("name").regex(params.getName(), "i"));
		}

		Criteria criteria = filters.isEmpty()
				? new Criteria() // match all
				: new Criteria().andOperator(filters);

		// Count total items
		Query query = new Query();
		query.addCriteria(criteria);
		long total = mongoTemplate.count(query, MONGODB_COLLECTION);

		// Add pagination
		Aggregation pagedAgg = Aggregation.newAggregation(
				Aggregation.match(criteria),
				Aggregation.skip((long) pageable.getPageNumber() * pageable.getPageSize()),
				Aggregation.limit(pageable.getPageSize())
		);

		List<UserEntity> userList = mongoTemplate
				.aggregate(pagedAgg, MONGODB_COLLECTION, UserEntity.class)
				.getMappedResults();

		return new PageImpl<>(userList, pageable, total);
	}


}
