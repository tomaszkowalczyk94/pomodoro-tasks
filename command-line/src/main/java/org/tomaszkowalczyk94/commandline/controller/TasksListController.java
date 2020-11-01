package org.tomaszkowalczyk94.commandline.controller;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.tomaszkowalczyk94.commandline.core.CoreApi;
import org.tomaszkowalczyk94.commandline.core.TaskDto;

import java.util.List;

@AllArgsConstructor
public class TasksListController {

    CoreApi coreApi;

    @SneakyThrows
    public void reloadTasksList() {
        List<TaskDto> body = coreApi.tasks().execute().body();

    }

}
