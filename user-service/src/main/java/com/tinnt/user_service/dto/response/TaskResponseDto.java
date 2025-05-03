package com.tinnt.user_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskResponseDto {
    private String id;
    private String title;
    private String content;
    private String assignedTo;
    private String assignedBy;
    private Date deadline;
    private boolean isCompleted;
}
