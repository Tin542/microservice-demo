package com.tinnt.task_service.controller;

import com.tinnt.task_service.assembler.TaskModelAssembler;
import com.tinnt.task_service.dto.request.TaskRequestBodyDto;
import com.tinnt.task_service.dto.request.TaskRequestParamDto;
import com.tinnt.task_service.dto.response.TaskResponseDto;
import com.tinnt.task_service.entity.TaskEntity;
import com.tinnt.task_service.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    TaskService taskService;

    @Autowired
    TaskModelAssembler assembler;

    @Autowired
    PagedResourcesAssembler<TaskEntity> pagedAssembler;

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<TaskResponseDto>> getTask(@PathVariable("id") String id) {
        TaskEntity task = taskService.detailTask(id);
        return ResponseEntity.ok(assembler.toModel(task));
    }

    @GetMapping("")
    public ResponseEntity<PagedModel<EntityModel<TaskResponseDto>>> getAll(@ModelAttribute TaskRequestParamDto params, @PageableDefault(size = 5)Pageable pageable) {
        Page<TaskEntity> tasks = taskService.getAll(params, pageable);
        PagedModel<EntityModel<TaskResponseDto>> pagedModel = pagedAssembler.toModel(tasks, assembler);
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<EntityModel<TaskResponseDto>> createTask(@RequestBody TaskRequestBodyDto dto) {
        TaskEntity result = taskService.createTask(dto);
        return new ResponseEntity<>(assembler.toModel(result), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EntityModel<TaskResponseDto>> updateTask(@PathVariable("id") String taskId, @RequestBody TaskRequestBodyDto dto) {
        TaskEntity result = taskService.updateTask(taskId, dto);
        return new ResponseEntity<>(assembler.toModel(result), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable("id") String taskId) {
        return new ResponseEntity<>(taskService.deleteTask(taskId), HttpStatus.OK);
    }
}
