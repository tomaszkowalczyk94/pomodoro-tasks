package org.tomaszkowalczyk94.commandline.gui;

import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.ActionListDialog;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.tomaszkowalczyk94.commandline.core.TaskDto;

import java.util.Objects;
import java.util.function.Consumer;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class TaskListViewImpl implements TaskListView {

    final TasksListRegistry tasksListRegistry;
    final MainElementsRegistry mainElementsRegistry;
    private static final int TASK_NAME_LENGTH = 15;

    Consumer<TaskDto> onRemoveAction;

    @Override
    @SneakyThrows
    public void addTask(TaskDto taskDto) {
        tasksListRegistry
                .getActionListBoxOfTasks()
                .addItem(buildNameOfTask(taskDto), () -> openActionList(mainElementsRegistry.getTextGui(), taskDto));

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

    private String buildNameOfTask(TaskDto taskDto) {
        String taskNameWithSpaces = StringUtils.rightPad(taskDto.getName(), TASK_NAME_LENGTH);
        return String.format("%s %d min", taskNameWithSpaces, taskDto.getDuration().toMinutes());
    }


}
