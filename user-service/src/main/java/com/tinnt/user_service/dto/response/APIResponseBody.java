package com.tinnt.user_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class APIResponseBody<T> {
    private HttpStatus errorCode;
    private T data;
    private String message;
}
