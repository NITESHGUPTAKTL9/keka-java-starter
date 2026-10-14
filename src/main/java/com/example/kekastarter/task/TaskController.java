package com.example.kekastarter.task;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping("/v1")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse create(@Valid @RequestBody CreateTaskRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<TaskResponse> findAll() {
        return service.findAll();
    }
}

























/*
@PutMapping(
    "/api/companies/{companyId}/employees/{employeeId}/tasks/{taskId}"
)
public ResponseEntity<TaskResponse> updateTask(
        @PathVariable Long companyId,
        @PathVariable Long employeeId,
        @PathVariable Long taskId,
        @RequestParam(defaultValue = "false") boolean notify,
        @RequestParam(defaultValue = "MEDIUM") String priority,
        @Valid @RequestBody UpdateTaskRequest request) {

    TaskResponse response = taskService.updateTask(
            companyId, employeeId, taskId,
            notify, priority, request
    );

    return ResponseEntity.ok(response);
}
 */