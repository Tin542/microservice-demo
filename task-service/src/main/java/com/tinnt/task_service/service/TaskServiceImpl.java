package com.tinnt.task_service.service;

import com.tinnt.task_service.client.UserClient;
import com.tinnt.task_service.dto.request.TaskRequestBodyDto;
import com.tinnt.task_service.dto.request.TaskRequestParamDto;
import com.tinnt.task_service.dto.response.UserResponseDto;
import com.tinnt.task_service.entity.TaskEntity;
import com.tinnt.task_service.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    UserClient userClient;

    @Autowired
    TaskRepository taskRepository;

    @Override
    public TaskEntity createTask(TaskRequestBodyDto dto) {

        UserResponseDto assignedToUser = userClient.getUserForAssigned(dto.getAssignedTo());
        dto.setAssignedTo(assignedToUser.getUsername());
        TaskEntity te = taskRepository.create(dto);
        return te;
    }

    @Override
    public TaskEntity updateTask(String id, TaskRequestBodyDto dto) {
        TaskEntity te = taskRepository.update(id, dto);
        return te;
    }

    @Override
    public TaskEntity detailTask(String taskId) {
        return taskRepository.getById(taskId);
    }

    @Override
    public Page<TaskEntity> getAll(TaskRequestParamDto params, Pageable pageable) {
        return taskRepository.getAll(params, pageable);
    }

    @Override
    public Boolean deleteTask(String id) {
        return taskRepository.removeTask(id);
    }
}
