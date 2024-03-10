package kz.learn.todolist.service;

import kz.learn.todolist.entity.Task;
import kz.learn.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ArchiveService {
    private final TaskRepository taskRepository;

    @Autowired
    public ArchiveService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllCompletedTasks() {
        return taskRepository.findAllCompleted();
    }

    public List<Task> getCompletedTasksByDate(LocalDate date) {
        return taskRepository.findAllCompletedByDate(date);
    }

}
