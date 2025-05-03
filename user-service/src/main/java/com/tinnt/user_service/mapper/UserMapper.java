package com.tinnt.user_service.mapper;


import com.tinnt.user_service.dto.response.UserResponseDTO;
import com.tinnt.user_service.entity.UserEntity;

import java.util.List;

public class UserMapper {
    public static UserResponseDTO toDTO (UserEntity entity) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setUsername(entity.getUsername());

        return dto;
    }

    public static List<UserResponseDTO> toListDto (List<UserEntity> listEntity) {
        return listEntity.stream().map(entity -> toDTO(entity)).toList();
    }
}
