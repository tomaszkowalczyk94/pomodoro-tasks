package org.tomaszkowalczyk94.pomodorotasksmanager.commandline.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.tomaszkowalczyk94.pomodorotasksmanager.commandline.core.CoreApi;
import org.tomaszkowalczyk94.pomodorotasksmanager.commandline.gui.AddTaskFormView;
import org.tomaszkowalczyk94.pomodorotasksmanager.coremodel.TaskDto;
import retrofit2.Response;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AddTaskFormController {

    TasksListController tasksListController;

    CoreApi coreApi;
    AddTaskFormView addTaskFormView;

    public void init() {
        addTaskFormView.addTaskButtonEvent(this::addTask);
    }

    @SneakyThrows
    private void addTask() {
        TaskDto taskFromForm = addTaskFormView.getTaskFromForm();

        Response<TaskDto> response = coreApi.createTask(taskFromForm).execute();

        if (response.isSuccessful()) {
            tasksListController.reloadTasksList();
        } else {
            throw new ResponseNotSuccessfulException();
        }
    }
}
