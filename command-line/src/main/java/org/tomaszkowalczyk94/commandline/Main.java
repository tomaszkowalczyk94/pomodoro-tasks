package org.tomaszkowalczyk94.commandline;

import org.tomaszkowalczyk94.commandline.controller.AddTaskFormController;
import org.tomaszkowalczyk94.commandline.controller.TasksListController;
import org.tomaszkowalczyk94.commandline.core.CoreApi;
import org.tomaszkowalczyk94.commandline.core.CoreApiFactory;
import org.tomaszkowalczyk94.commandline.gui.Gui;
import org.tomaszkowalczyk94.commandline.gui.GuiBuilder;
import org.tomaszkowalczyk94.commandline.gui.ViewsRegistry;

public class Main {
    public static void main(String[] args) {
        CoreApi coreApi = new CoreApiFactory().create();

        Gui gui = new GuiBuilder()
                .build();

        ViewsRegistry viewsRegistry = gui.getViewsRegistry();

        TasksListController tasksListController = new TasksListController(coreApi, viewsRegistry.getTaskListView());
        tasksListController.init();

        AddTaskFormController addTaskFormController = new AddTaskFormController(
                tasksListController,
                coreApi,
                viewsRegistry.getAddTaskView()
        );
        
        addTaskFormController.init();

        tasksListController.reloadTasksList();

        gui.waitForExit();
    }
}
