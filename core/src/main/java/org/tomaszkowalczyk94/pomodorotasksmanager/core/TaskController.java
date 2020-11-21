package org.tomaszkowalczyk94.pomodorotasksmanager.core;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.tomaszkowalczyk94.pomodorotasksmanager.core.entity.Task;
import org.tomaszkowalczyk94.pomodorotasksmanager.core.repository.TaskRepository;
import org.tomaszkowalczyk94.pomodorotasksmanager.coremodel.TaskDto;

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
    public TaskDto byId(@PathVariable Long id) {
        return taskRepository.findById(id)
                .map(task -> modelMapper.map(task, TaskDto.class))
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

