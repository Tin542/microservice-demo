package com.tinnt.user_service.client;

import com.tinnt.user_service.dto.response.TaskResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "task-service")
public interface TaskClient {
    @GetMapping("/api/tasks")
    List<TaskResponseDto> getListTaskOfUser(@RequestParam("username") String username);
}
