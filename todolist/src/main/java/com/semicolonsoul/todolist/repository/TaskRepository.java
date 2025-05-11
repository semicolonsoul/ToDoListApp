package com.semicolonsoul.todolist.repository;

import com.semicolonsoul.todolist.dto.TaskDTO;
import com.semicolonsoul.todolist.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

      Optional<Task> findByTitle(String title);

      void deleteByTitle(String title);
}
