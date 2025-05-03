package com.tinnt.task_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestBodyDto {
    private String title;
    private String content;
    private String assignedTo;
    private LocalDateTime deadline;
    private boolean isCompleted;
}
