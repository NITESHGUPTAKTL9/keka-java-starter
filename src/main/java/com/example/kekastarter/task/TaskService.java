package com.example.kekastarter.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private static final Logger log = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public TaskResponse create(CreateTaskRequest request) {
        Task created = repository.save(request.getTitle().trim(), request.getDescription());
        log.info("Created task id={}", created.id());
        return new TaskResponse(created.id(), created.title(), created.description());
    }

    public List<TaskResponse> findAll() {
        return repository.findAll().stream()
                .map(t -> new TaskResponse(t.id(), t.title(), t.description()))
                .toList();
    }
}
