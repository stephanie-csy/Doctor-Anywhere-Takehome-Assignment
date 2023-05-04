package com.example.Task;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
            task.setId((long) (taskList.size() + 1));
            taskList.add(task);
            return task;
        } else {
            throw new InvalidRequestException();
        }

    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        if (taskList.size() >= id) {
            return taskList.get((int) (id-1));
        } else {
            throw new TaskNotFoundException(id);
        }
    }

    @PutMapping("/{id}")
    public Task updateTaskById(@PathVariable Long id, @RequestBody Task task) {
        if (task.getId() != null) {
            throw new InvalidRequestException();
        } else if (taskList.size() >= id) {
            Task taskToUpdate = taskList.get((int) (id-1));

            if (task.getTitle() != null) {
                taskToUpdate.setTitle(task.getTitle());
            }

            if (task.getDescription() != null) {
                taskToUpdate.setDescription(task.getDescription());
            }

            if (task.getCompleted() != null) {
                taskToUpdate.setCompleted(task.getCompleted());
            }

            return taskToUpdate;
        } else {
            throw new TaskNotFoundException(id);
        }
    }

    @DeleteMapping("/{id}")
    public String deleteTaskById(@PathVariable Long id) {
        if (taskList.size() >= id) {
            taskList.remove((int) (id-1));
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