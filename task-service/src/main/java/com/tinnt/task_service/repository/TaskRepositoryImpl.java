package com.tinnt.task_service.repository;

import com.mongodb.client.result.DeleteResult;
import com.tinnt.task_service.dto.request.TaskRequestBodyDto;
import com.tinnt.task_service.dto.request.TaskRequestParamDto;
import com.tinnt.task_service.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepository{
    private static final String MONGODB_COLLECTION = "tasks";

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public TaskEntity create(TaskRequestBodyDto dto) {
        TaskEntity entity = new TaskEntity();
        entity.setAssignedTo(dto.getAssignedTo());
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setAssignedBy("Admin-01");
        entity.setDeadline(dto.getDeadline());
        entity.setCompleted(false);

        return mongoTemplate.save(entity);
    }

    @Override
    public TaskEntity update(String id, TaskRequestBodyDto dto) {
        TaskEntity taskEntity = this.getById(id);
        if(taskEntity == null) {
            return null;
        }

        taskEntity.setTitle(dto.getTitle());
        taskEntity.setContent(dto.getContent());
        taskEntity.setDeadline(dto.getDeadline());
        taskEntity.setAssignedBy("Admin-01");
        taskEntity.setAssignedTo(dto.getAssignedTo());
        taskEntity.setCompleted(dto.isCompleted());

        return mongoTemplate.save(taskEntity);
    }

    @Override
    public TaskEntity getById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        TaskEntity entity = mongoTemplate.findById(id, TaskEntity.class, MONGODB_COLLECTION);
        return entity;
    }

    @Override
    public Page<TaskEntity> getAll(TaskRequestParamDto params, Pageable pageable) {

        List<Criteria> filters = new ArrayList<>();

        if (StringUtils.hasText(params.getUsername())) {
            filters.add(Criteria.where("assignedTo").is(params.getUsername()));
        }

        if (StringUtils.hasText(params.getTitle())) {
            filters.add(Criteria.where("title").regex(params.getTitle(), "i"));
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

        List<TaskEntity> taskList = mongoTemplate
                .aggregate(pagedAgg, MONGODB_COLLECTION, TaskEntity.class)
                .getMappedResults();

        return new PageImpl<>(taskList, pageable, total);

    }

    @Override
    public Boolean removeTask(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        DeleteResult result = mongoTemplate.remove(query, MONGODB_COLLECTION);
        if(result == null) {
            return false;
        }
        return true;
    }

}
