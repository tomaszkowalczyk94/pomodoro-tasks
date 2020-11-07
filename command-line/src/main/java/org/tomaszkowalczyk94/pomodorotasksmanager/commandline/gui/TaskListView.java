package org.tomaszkowalczyk94.pomodorotasksmanager.commandline.gui;



import org.tomaszkowalczyk94.pomodorotasksmanager.coremodel.TaskDto;

import java.util.function.Consumer;

/**
 * classic view, like in mvc pattern
 */
public interface TaskListView {
    void removeAll();
    void addTask(TaskDto taskDto);
    void setOnRemoveAction(Consumer<TaskDto> onRemoveAction);
}
