package com.semicolonsoul.todolist.controller;


import com.semicolonsoul.todolist.dto.TaskDTO;
import com.semicolonsoul.todolist.dto.TaskMapper;
import com.semicolonsoul.todolist.entity.Task;
import com.semicolonsoul.todolist.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.*;

@RestController
@RequestMapping("/api/todolist")
public class TaskController {


    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping("/getTask")
    public ResponseEntity<List<TaskDTO>> getAllTask(){
        List<Task> task= taskService.getTasks();
       List<TaskDTO> taskDTOs=  task.stream().map(TaskMapper::toDto).toList();
       return ResponseEntity.ok(taskDTOs);
    }

    @GetMapping("/getTask/name/{title}")
    public ResponseEntity<TaskDTO> getTaskByName(@PathVariable String title) throws Exception {
        Optional<Task> taskFound= taskService.getTaskByName(title);
        return taskFound.map(task -> new ResponseEntity<>(TaskMapper.toDto(task), HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/createTask")
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO){
        Task task= TaskMapper.toEntity(taskDTO);
        Task savedTask= taskService.createTask(task);
        return new ResponseEntity<>(TaskMapper.toDto(savedTask), HttpStatus.CREATED);
    }

    @PutMapping("/updateTask/{title}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable String title, @RequestBody TaskDTO taskDto){
        Optional<Task> taskFound= taskService.getTaskByName(title);
        if(taskFound.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            Task task= TaskMapper.toEntity(taskDto);
            Task taskToUpdate=taskFound.get();
            taskToUpdate.setTitle(task.getTitle());
            taskToUpdate.setDescription(task.getDescription());
            taskToUpdate.setImportance(task.getImportance());
            taskToUpdate.setStatus(task.getStatus());
            taskToUpdate.setDueDate(task.getDueDate());

            Task savedTask= taskService.createTask(taskToUpdate);
            return ResponseEntity.ok(TaskMapper.toDto(savedTask));
        }
    }

    @PatchMapping("/tasks/{title}")
    public ResponseEntity<TaskDTO> updateTaskByField(@PathVariable String title, @RequestBody Map<String, String> updates){
        Optional<Task> taskFound=taskService.getTaskByName(title);
        if(taskFound.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            Task taskToUpdate= taskFound.get();

            updates.forEach((key, value)->{
                Field field= ReflectionUtils.findField(Task.class, key);
                if(field!=null){
                    field.setAccessible(true);
                    if(field.getType().isEnum()){
                        ReflectionUtils.setField(field, taskToUpdate, Enum.valueOf((Class<Enum>) field.getType(), value));
                    }
                    else {
                        ReflectionUtils.setField(field, taskToUpdate, value);
                    }
                }
            });

            TaskDTO savedTask= TaskMapper.toDto(taskService.createTask(taskToUpdate));
            return ResponseEntity.ok(savedTask);
        }
    }


    @DeleteMapping("/deleteTask/{title}")
    public ResponseEntity<TaskDTO> deleteTaskByTitle(@PathVariable String title){

        Optional<Task> taskFound=taskService.getTaskByName(title);
        if(taskFound.isPresent()){
            taskService.deleteTaskByTitle(title);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
