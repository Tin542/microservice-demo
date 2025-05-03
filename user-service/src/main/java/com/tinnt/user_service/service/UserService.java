package com.tinnt.user_service.service;

import java.util.List;

import com.tinnt.user_service.dto.request.UserRequestBodyDTO;
import com.tinnt.user_service.dto.request.UserRequestParamDto;
import com.tinnt.user_service.dto.response.UserResponseDTO;
import com.tinnt.user_service.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public interface UserService {
	List<UserEntity> getAllUser ();

	UserResponseDTO getDetailluser(String id);
	
	UserEntity createUser (UserRequestBodyDTO dto);

	UserEntity updateUser (UserRequestBodyDTO dto, String id) throws Exception;

	Page<UserEntity> findUsers(UserRequestParamDto params, Pageable pageable);

}
