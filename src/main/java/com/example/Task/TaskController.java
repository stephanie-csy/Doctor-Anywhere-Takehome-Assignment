package com.example.Task;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private List<Task> taskList = new ArrayList<>();

    @GetMapping
    public List<Task> getTasks() {
        return taskList;
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        if (task.getId() == null) {
            Long lastID = taskList.get(taskList.size() - 1).getId();
            task.setId(lastID + 1);
            taskList.add(task);
            return task;
        } else {
            throw new InvalidRequestException();
        }

    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskList.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
        if (task.isPresent()) {
            return task.get();
        } else {
            throw new TaskNotFoundException(id);
        }
    }

    @PutMapping("/{id}")
    public Task updateTaskById(@PathVariable Long id, @RequestBody Task task) {
        if (task.getId() != null) {
            throw new InvalidRequestException();
        } else {
            Optional<Task> taskToUpdate = taskList.stream()
                    .filter(t -> t.getId().equals(id))
                    .findFirst();
            if (taskToUpdate.isPresent()) {
                if (task.getTitle() != null) {
                    taskToUpdate.get().setTitle(task.getTitle());
                }

                if (task.getDescription() != null) {
                    taskToUpdate.get().setDescription(task.getDescription());
                }

                if (task.getCompleted() != null) {
                    taskToUpdate.get().setCompleted(task.getCompleted());
                }

                return taskToUpdate.get();
            } else {
                throw new TaskNotFoundException(id);
            }
        }
    }

    @DeleteMapping("/{id}")
    public String deleteTaskById(@PathVariable Long id) {
        Optional<Task> task = taskList.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
        if (task.isPresent()) {
            taskList.remove(task);
            return "Task " + id + " successfully deleted.";
        } else {
            throw new TaskNotFoundException(id);
        }
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleException(Exception ex) {
        return "An error has occurred: " + ex.getMessage();
    }

    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleInvalidRequest(InvalidRequestException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleTaskNotFound(TaskNotFoundException ex) {
        return ex.getMessage();
    }
}