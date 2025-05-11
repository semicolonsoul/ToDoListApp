package com.semicolonsoul.todolist.entity;

import com.semicolonsoul.todolist.enumeration.Importance;
import com.semicolonsoul.todolist.enumeration.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="task")
public class Task {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private int id;

      @Column(unique = true)
      private String title;
      private String description;

      @Enumerated(EnumType.STRING)
      private Status status;

      @Enumerated(EnumType.STRING)
      private Importance importance;

      private LocalDateTime createdAt;
      private LocalDateTime dueDate;

      public int getId() {
            return id;
      }

      public void setId(int id) {
            this.id = id;
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

      public LocalDateTime getCreatedAt() {
            return createdAt;
      }

      public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
      }

      public LocalDateTime getDueDate() {
            return dueDate;
      }

      public void setDueDate(LocalDateTime dueDate) {
            this.dueDate = dueDate;
      }
}
