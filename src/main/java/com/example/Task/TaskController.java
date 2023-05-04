package com.example.Task;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private List<Task> taskList = new ArrayList<>();

    @GetMapping
    public List<Task> getTasks(@RequestBody Task task) {
        return taskList;
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        task.setId((long) (taskList.size() + 1));
        taskList.add(task);
        return task;
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskList.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PutMapping("/{id}")
    public Task updateTaskById(@PathVariable Long id, @RequestBody Task task) {
        Task taskToUpdate = taskList.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (taskToUpdate != null) {
            if (task.getTitle() != null) {
                taskToUpdate.setTitle(task.getTitle());
            }

            if (task.getDescription() != null) {
                taskToUpdate.setDescription(task.getDescription());
            }

            if (task.getCompleted() != null) {
                taskToUpdate.setCompleted(task.getCompleted());
            }
        }

        return taskToUpdate;
    }

    @DeleteMapping("/{id}")
    public void deleteTaskById(@PathVariable Long id) {
        taskList.removeIf(task -> task.getId().equals(id));
    }
}