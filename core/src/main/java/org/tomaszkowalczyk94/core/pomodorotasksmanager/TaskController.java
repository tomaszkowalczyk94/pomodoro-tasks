package org.tomaszkowalczyk94.core.pomodorotasksmanager;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.tomaszkowalczyk94.core.pomodorotasksmanager.entity.Task;
import org.tomaszkowalczyk94.core.pomodorotasksmanager.repository.TaskRepository;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@RequestMapping("/tasks")
@RestController
public class TaskController {
    TaskRepository taskRepository;

    @GetMapping
    public Iterable<Task> all() {
        return taskRepository.findAll();
    }

    @GetMapping("{id}")
    public Task byId(@PathVariable Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Task newEntry(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @PutMapping("{id}")
    public Task replace(@RequestBody Task newTask, @PathVariable Long id) {

        return taskRepository.findById(id)
                .map(taskRepository::save)
                .orElseGet(() -> {
                    newTask.setId(id);
                    return taskRepository.save(newTask);
                });
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
}

