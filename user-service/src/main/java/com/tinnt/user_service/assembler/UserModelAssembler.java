package com.tinnt.user_service.assembler;

import com.tinnt.user_service.controller.UserController;
import com.tinnt.user_service.dto.response.UserResponseDTO;
import com.tinnt.user_service.entity.UserEntity;
import com.tinnt.user_service.mapper.UserMapper;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<UserEntity, EntityModel<UserResponseDTO>> {
    @Override
    public EntityModel<UserResponseDTO> toModel(UserEntity entity) {
        UserResponseDTO dto = UserMapper.toDTO(entity);
        return EntityModel.of(dto,
                linkTo(methodOn(UserController.class).getDetail(entity.getId())).withSelfRel()
        );
    }
}
