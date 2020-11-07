package org.tomaszkowalczyk94.core.pomodorotasksmanager;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.tomaszkowalczyk94.core.pomodorotasksmanager.entity.Task;
import org.tomaszkowalczyk94.core.pomodorotasksmanager.model.TaskDto;
import org.tomaszkowalczyk94.core.pomodorotasksmanager.repository.TaskRepository;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@RequestMapping("/tasks")
@RestController
public class TaskController {
    TaskRepository taskRepository;

    ModelMapper modelMapper;

    @GetMapping
    public Iterable<TaskDto> all() {
        return StreamSupport.stream(taskRepository.findAll().spliterator(), false)
                .map(task -> modelMapper.map(task, TaskDto.class))
                .collect(Collectors.toSet());

    }

    @GetMapping("{id}")
    public Task byId(@PathVariable Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public TaskDto newEntry(@RequestBody TaskDto taskDto) {
        Task task = modelMapper.map(taskDto, Task.class);
        return modelMapper.map(taskRepository.save(task), TaskDto.class);
    }

    @PutMapping("{id}")
    public TaskDto replace(@RequestBody TaskDto newTaskDto, @PathVariable Long id) {
        Task task = modelMapper.map(newTaskDto, Task.class);

        Task modifiedTask = taskRepository.findById(id)
                .map(taskRepository::save)
                .orElseGet(() -> {
                    newTaskDto.setId(id);
                    return taskRepository.save(task);
                });
        return modelMapper.map(modifiedTask, TaskDto.class);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
}

