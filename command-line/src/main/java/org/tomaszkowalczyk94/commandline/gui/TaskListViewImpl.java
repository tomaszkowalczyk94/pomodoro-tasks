package org.tomaszkowalczyk94.commandline.gui;

import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.ActionListDialog;
import com.googlecode.lanterna.gui2.dialogs.ActionListDialogBuilder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.tomaszkowalczyk94.commandline.core.TaskDto;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class TaskListViewImpl implements TaskListView {

    TasksListRegistry tasksListRegistry;
    MainElementsRegistry mainElementsRegistry;

    @Override
    @SneakyThrows
    public void addTask(TaskDto taskDto) {
        tasksListRegistry
                .getActionListBoxOfTasks()
                .addItem(taskDto.getName(), () -> openActionList(mainElementsRegistry.getTextGui()));

        mainElementsRegistry.getScreen().refresh();
    }

    @Override
    @SneakyThrows
    public void removeAll() {
        tasksListRegistry
                .getActionListBoxOfTasks()
                .clearItems();

        mainElementsRegistry.getScreen().refresh();
    }


    private void openActionList(WindowBasedTextGUI textGUI) {
        createActionList().showDialog(textGUI);
    }

    private ActionListDialog createActionList() {
        return new ActionListDialogBuilder()
                .setTitle("Action List Dialog")
                .setDescription("Choose an item")
                .addAction("First Item", () -> {
                    // Do 1st thing...
                })
                .addAction("Second Item", () -> {
                    // Do 2nd thing...
                })
                .addAction("Third Item", () -> {
                    // Do 3rd thing...
                })
                .build();
    }
}
