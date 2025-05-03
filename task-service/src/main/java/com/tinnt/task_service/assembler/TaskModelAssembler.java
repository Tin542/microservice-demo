package com.tinnt.task_service.assembler;

import com.tinnt.task_service.controller.TaskController;
import com.tinnt.task_service.dto.response.TaskResponseDto;
import com.tinnt.task_service.entity.TaskEntity;
import com.tinnt.task_service.mapper.TaskMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TaskModelAssembler implements RepresentationModelAssembler<TaskEntity, EntityModel<TaskResponseDto>> {

    @Override
    public EntityModel<TaskResponseDto> toModel(TaskEntity entity) {
        TaskResponseDto dto = TaskMapper.toDto(entity);
        return EntityModel.of(dto,
                linkTo(methodOn(TaskController.class).getTask(entity.getId())).withSelfRel()
        );
    }

    @Override
    public CollectionModel<EntityModel<TaskResponseDto>> toCollectionModel(Iterable<? extends TaskEntity> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
