package com.tinnt.task_service.mapper;

import com.tinnt.task_service.dto.response.TaskResponseDto;
import com.tinnt.task_service.entity.TaskEntity;

public class TaskMapper {
    public static TaskResponseDto toDto (TaskEntity entity) {
        TaskResponseDto dto = new TaskResponseDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setAssignedTo(entity.getAssignedTo());
        dto.setDeadline(entity.getDeadline());
        dto.setAssignedBy(entity.getAssignedBy());
        dto.setCompleted(entity.isCompleted());

        return dto;
    }
}
