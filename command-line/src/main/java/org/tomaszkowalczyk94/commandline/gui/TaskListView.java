package org.tomaszkowalczyk94.commandline.gui;

import org.tomaszkowalczyk94.commandline.core.TaskDto;

/**
 * classic view, like in mvc pattern
 */
public interface TaskListView {
    void addTask(TaskDto taskDto);
}
