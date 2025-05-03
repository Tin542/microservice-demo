package com.tinnt.task_service.repository;

import com.tinnt.task_service.dto.request.TaskRequestBodyDto;
import com.tinnt.task_service.dto.request.TaskRequestParamDto;
import com.tinnt.task_service.entity.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TaskRepository {
    TaskEntity create(TaskRequestBodyDto data);
    TaskEntity update(String id, TaskRequestBodyDto dto);
    TaskEntity getById(String id);
    Page<TaskEntity> getAll(TaskRequestParamDto params, Pageable pageable);
    Boolean removeTask(String id);
}
