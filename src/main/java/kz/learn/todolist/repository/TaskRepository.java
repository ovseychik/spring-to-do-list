package kz.learn.todolist.repository;

import kz.learn.todolist.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE Task t SET t.isCompleted = true, t.completedAt=CURRENT_TIMESTAMP  WHERE t.id = :id")
    void markAsCompleted(Long id);

    // Working with archive: (1) retrieve all completed tasks; (2) retrieve completed tasks for a certain day
    @Query("SELECT t FROM Task t WHERE t.isCompleted = true AND t.username = :username")
    List<Task> findAllCompleted(String username);

    @Query("SELECT t FROM Task t WHERE t.isCompleted = true AND CAST(t.completedAt AS DATE) = :date AND t.username = :username")
    List<Task> findAllCompletedByDate(LocalDate date, String username);

}
