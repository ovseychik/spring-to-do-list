package kz.learn.todolist.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime timestamp;
    private String task;
    private Boolean isCompleted;

    public Task() {
    }

    public String getTask() {
        return task;
    }

    public Long getId() {
        return id;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
