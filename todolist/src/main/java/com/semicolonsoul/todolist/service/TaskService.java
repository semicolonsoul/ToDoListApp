package com.semicolonsoul.todolist.service;

import com.semicolonsoul.todolist.dto.TaskDTO;
import com.semicolonsoul.todolist.entity.Task;
import com.semicolonsoul.todolist.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
       return taskRepository.save(task);

    }

    public Optional<Task> getTaskByName(String title) {
        return taskRepository.findByTitle(title);
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Transactional
    public void deleteTaskByTitle(String title) {
        taskRepository.deleteByTitle(title);
    }
}
