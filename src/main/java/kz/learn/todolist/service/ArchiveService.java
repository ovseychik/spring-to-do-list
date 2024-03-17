package kz.learn.todolist.service;

import kz.learn.todolist.entity.Task;
import kz.learn.todolist.entity.User;
import kz.learn.todolist.repository.TaskRepository;
import kz.learn.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ArchiveService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public ArchiveService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    private User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername());
    }


    public List<Task> getAllCompletedTasks() {
        User user = getCurrentUser();
        return taskRepository.findAllCompleted(user);
    }

    public List<Task> getCompletedTasksByDate(LocalDate date) {
        User user = getCurrentUser();
        return taskRepository.findAllCompletedByDate(date, user);
    }

}
