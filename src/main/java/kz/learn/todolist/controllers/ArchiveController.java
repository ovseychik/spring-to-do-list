package kz.learn.todolist.controllers;

import kz.learn.todolist.entity.Task;
import kz.learn.todolist.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

    @Autowired
    public ArchiveController(ArchiveService archiveService) {
        this.archiveService = archiveService;
    }

    @GetMapping
    public String listCompletedTasks(Model model,
                                     @RequestParam(value = "date", required = false)
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                     LocalDate date) {
        List<Task> tasks;
        if (date != null) {
            tasks = archiveService.getCompletedTasksByDate(date);
        } else {
            tasks = archiveService.getAllCompletedTasks();
        }
        model.addAttribute("tasks", tasks);
        return "archive";
    }
}
