package com.tinnt.task_service.service;

import com.tinnt.task_service.dto.request.TaskRequestBodyDto;
import com.tinnt.task_service.dto.request.TaskRequestParamDto;
import com.tinnt.task_service.entity.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TaskService {

    TaskEntity createTask(TaskRequestBodyDto dto);
    TaskEntity updateTask(String id, TaskRequestBodyDto dto);
    TaskEntity detailTask(String taskId);
    Page<TaskEntity> getAll(TaskRequestParamDto params, Pageable pageable);
    Boolean deleteTask (String id);
}
