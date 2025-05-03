package com.tinnt.task_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskResponseDto {
    private String id;
    private String title;
    private String content;
    private String assignedTo;
    private String assignedBy;
    private LocalDateTime deadline;
    private boolean isCompleted;
}
