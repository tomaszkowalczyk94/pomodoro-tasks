package org.tomaszkowalczyk94.commandline.gui;

import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.ActionListDialog;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.tomaszkowalczyk94.commandline.core.TaskDto;

import java.util.Objects;
import java.util.function.Consumer;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class TaskListViewImpl implements TaskListView {

    final TasksListRegistry tasksListRegistry;
    final MainElementsRegistry mainElementsRegistry;

    Consumer<TaskDto> onRemoveAction;

    @Override
    @SneakyThrows
    public void addTask(TaskDto taskDto) {
        tasksListRegistry
                .getActionListBoxOfTasks()
                .addItem(taskDto.getName(), () -> openActionList(mainElementsRegistry.getTextGui(), taskDto));

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

    @Override
    public void setOnRemoveAction(Consumer<TaskDto> onRemoveAction) {
        this.onRemoveAction = onRemoveAction;
    }

    private void openActionList(WindowBasedTextGUI textGUI, TaskDto taskDto) {
        Objects.requireNonNull(onRemoveAction);

        ActionListDialog actionListDialog = new TaskActionListBuilder(
                () -> onRemoveAction.accept(taskDto)
        ).build();

        actionListDialog.showDialog(textGUI);
    }


}
