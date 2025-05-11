package com.semicolonsoul.todolist.dto;

import com.semicolonsoul.todolist.enumeration.Importance;
import com.semicolonsoul.todolist.enumeration.Status;

import java.time.LocalDateTime;

public class TaskDTO {

    private String title;
    private String description;
    private Status status;
    private Importance importance;
    private LocalDateTime dueDate;

    public TaskDTO() {
    }

    public TaskDTO(String title, String description, Status status, Importance importance, LocalDateTime dueDate) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.importance = importance;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Importance getImportance() {
        return importance;
    }

    public void setImportance(Importance importance) {
        this.importance = importance;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}
