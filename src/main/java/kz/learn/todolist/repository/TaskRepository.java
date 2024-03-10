package kz.learn.todolist.repository;

import kz.learn.todolist.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Task t SET t.isCompleted = true, t.completedAt=CURRENT_TIMESTAMP  WHERE t.id = :id")
    void markAsCompleted(Long id);
}
