package org.tomaszkowalczyk94.pomodorotasksmanager.commandline;

import org.tomaszkowalczyk94.pomodorotasksmanager.commandline.controller.AddTaskFormController;
import org.tomaszkowalczyk94.pomodorotasksmanager.commandline.controller.TaskProgressController;
import org.tomaszkowalczyk94.pomodorotasksmanager.commandline.controller.TasksListController;
import org.tomaszkowalczyk94.pomodorotasksmanager.commandline.core.CoreApi;
import org.tomaszkowalczyk94.pomodorotasksmanager.commandline.core.CoreApiFactory;
import org.tomaszkowalczyk94.pomodorotasksmanager.commandline.gui.Gui;
import org.tomaszkowalczyk94.pomodorotasksmanager.commandline.gui.GuiBuilder;
import org.tomaszkowalczyk94.pomodorotasksmanager.commandline.gui.ViewsRegistry;

public class Main {
    public static void main(String[] args) {
        CoreApi coreApi = new CoreApiFactory().create();

        Gui gui = new GuiBuilder()
                .build();

        ViewsRegistry viewsRegistry = gui.getViewsRegistry();

        TaskProgressController taskProgressController = new TaskProgressController(viewsRegistry.getTaskProgressView());
        taskProgressController.init();

        TasksListController tasksListController = new TasksListController(
                coreApi,
                viewsRegistry.getTaskListView(),
                taskProgressController
        );
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
