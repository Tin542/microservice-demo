package com.tinnt.task_service.client;

import com.tinnt.task_service.dto.response.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/api/users/{id}")
    UserResponseDto getUserForAssigned(@PathVariable("id") String userId);
}
