package com.tinnt.task_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {
    @Id
    private String id;
    private String title;
    private String content;
    private String assignedTo;
    private String assignedBy;
    private LocalDateTime deadline;
    private boolean isCompleted;
}
