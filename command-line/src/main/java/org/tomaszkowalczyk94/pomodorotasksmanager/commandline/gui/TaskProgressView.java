package org.tomaszkowalczyk94.pomodorotasksmanager.commandline.gui;


import java.time.Duration;

public interface TaskProgressView {

    void openModal(String taskName);

    void setDurationLeft(Duration duration);

    void closeModal();

    void addStopButtonEvent(Runnable runnable);

    void bell();
}
