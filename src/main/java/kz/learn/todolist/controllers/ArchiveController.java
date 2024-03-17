package kz.learn.todolist.controllers;

import kz.learn.todolist.entity.Task;
import kz.learn.todolist.entity.User;
import kz.learn.todolist.repository.UserRepository;
import kz.learn.todolist.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/tasks/archive")
public class ArchiveController {
    private final ArchiveService archiveService;
    private final UserRepository userRepository;

    @Autowired
    public ArchiveController(ArchiveService archiveService, UserRepository userRepository) {
        this.archiveService = archiveService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String listCompletedTasks(Model model,
                                     @RequestParam(value = "date", required = false)
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                     LocalDate date) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        List<Task> tasks;
        if (date != null) {
            tasks = archiveService.getCompletedTasksByDate(date, user);
        } else {
            tasks = archiveService.getAllCompletedTasks(user);
        }
        model.addAttribute("tasks", tasks);
        return "archive";
    }
}
