package kz.learn.todolist.repository;

import kz.learn.todolist.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
