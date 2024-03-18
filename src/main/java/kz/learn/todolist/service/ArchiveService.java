package kz.learn.todolist.service;

import kz.learn.todolist.entity.Task;
import kz.learn.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ArchiveService {
    private final TaskRepository taskRepository;
    private final JdbcUserDetailsManager jdbcUserDetailsManager;

    @Autowired
    public ArchiveService(TaskRepository taskRepository, JdbcUserDetailsManager jdbcUserDetailsManager) {
        this.taskRepository = taskRepository;
        this.jdbcUserDetailsManager = jdbcUserDetailsManager;
    }

    private User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (User) jdbcUserDetailsManager.loadUserByUsername(userDetails.getUsername());
    }


    public List<Task> getAllCompletedTasks() {
        return taskRepository.findAllCompleted(getCurrentUser().getUsername());
    }

    public List<Task> getCompletedTasksByDate(LocalDate date) {
        return taskRepository.findAllCompletedByDate(date, getCurrentUser().getUsername());
    }

}
