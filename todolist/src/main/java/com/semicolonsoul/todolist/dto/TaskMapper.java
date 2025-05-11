package com.semicolonsoul.todolist.dto;

import com.semicolonsoul.todolist.entity.Task;

public class TaskMapper {

    public static TaskDTO toDto(Task entity){
        TaskDTO dto= new TaskDTO();
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        dto.setImportance(entity.getImportance());
        dto.setDueDate(entity.getDueDate());
        return dto;
    }

    public static Task toEntity(TaskDTO dto){
        Task entity= new Task();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
        entity.setImportance(dto.getImportance());
        entity.setDueDate(dto.getDueDate());
        return entity;
    }
}
