package com.tinnt.task_service.client;

import com.tinnt.task_service.dto.response.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "validation")
public interface ValidateClient {
    @PostMapping("/api/validate/{type}")
    List<String> validateObject(@PathVariable("type") String type, @RequestBody Object data);
}
