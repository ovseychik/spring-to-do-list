package kz.learn.todolist.service;

import kz.learn.todolist.entity.Task;
import kz.learn.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final JdbcUserDetailsManager jdbcUserDetailsManager;

    @Autowired
    public TaskService(TaskRepository taskRepository, JdbcUserDetailsManager jdbcUserDetailsManager) {
        this.taskRepository = taskRepository;
        this.jdbcUserDetailsManager = jdbcUserDetailsManager;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findByUsername(getCurrentUser().getUsername());
    }

    public Task saveTask(Task task) {
        task.setUsername(getCurrentUser().getUsername());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void markAsCompleted(Long id) {
        taskRepository.markAsCompleted(id);
    }

    private User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) jdbcUserDetailsManager.loadUserByUsername(userDetails.getUsername());
        return user;
    }

}
