package org.tomaszkowalczyk94.commandline.gui;

import org.tomaszkowalczyk94.commandline.core.TaskDto;

public interface AddTaskFormView {
    void addTaskButtonEvent(Runnable runnable);
    TaskDto getTaskFromForm();
}
