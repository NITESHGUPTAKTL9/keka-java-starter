package com.example.kekastarter.task;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TaskRepository {
    private final ConcurrentMap<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public Task save(String title, String description) {
        long id = nextId.getAndIncrement();
        Task task = new Task(id, title, description);
        tasks.put(id, task);
        return task;
    }

    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(tasks.get(id));
    }

    public List<Task> findAll() {
        return List.copyOf(tasks.values());
    }
}
