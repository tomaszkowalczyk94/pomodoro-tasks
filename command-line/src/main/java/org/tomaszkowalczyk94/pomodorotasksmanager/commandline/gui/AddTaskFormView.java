package org.tomaszkowalczyk94.pomodorotasksmanager.commandline.gui;


import org.tomaszkowalczyk94.pomodorotasksmanager.coremodel.TaskDto;

public interface AddTaskFormView {
    void addTaskButtonEvent(Runnable runnable);
    TaskDto getTaskFromForm();
}
