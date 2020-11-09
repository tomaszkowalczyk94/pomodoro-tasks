package org.tomaszkowalczyk94.pomodorotasksmanager.commandline.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.tomaszkowalczyk94.pomodorotasksmanager.commandline.core.CoreApi;
import org.tomaszkowalczyk94.pomodorotasksmanager.commandline.gui.TaskListView;
import org.tomaszkowalczyk94.pomodorotasksmanager.coremodel.TaskDto;
import retrofit2.Response;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TasksListController {

    final CoreApi coreApi;
    final TaskListView taskListView;
    final TaskProgressController taskProgressController;

    public void init() {
        taskListView.setOnRemoveAction(this::removeTask);
        taskListView.setOnStartTaskAction(this::startTask);
    }


    @SneakyThrows
    public void reloadTasksList() {

        Response<List<TaskDto>> response = coreApi.tasks().execute();

        if (response.isSuccessful()) {
            taskListView.removeAll();
            Objects.requireNonNull(response.body())
                    .forEach(taskListView::addTask);

        } else {
            throw new ResponseNotSuccessfulException();
        }
    }

    @SneakyThrows
    public void removeTask(TaskDto taskDto) {
        Response<Void> response = coreApi.removeTask(taskDto.getId()).execute();

        if (response.isSuccessful()) {
            reloadTasksList();
        } else {
            throw new ResponseNotSuccessfulException();
        }
    }

    public void startTask(TaskDto taskDto) {
        taskProgressController.start(taskDto);
    }

}
