package org.tomaszkowalczyk94.pomodorotasksmanager.commandline.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.tomaszkowalczyk94.pomodorotasksmanager.commandline.gui.TaskProgressView;
import org.tomaszkowalczyk94.pomodorotasksmanager.coremodel.TaskDto;

import java.time.Duration;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskProgressController {

    final TaskProgressView taskProgressView;
    Timer timer;

    public void init() {
        taskProgressView.addStopButtonEvent(this::terminate);
    }

    @AllArgsConstructor
    static class UpdateDurationLeftTask extends TimerTask {
        TaskProgressView taskProgressView;
        Duration durationLeft;
        Runnable onDurationLeft;

        public void run() {
            durationLeft = durationLeft.minus(Duration.ofSeconds(1));

            if (durationLeft.isNegative()) {
                onDurationLeft.run();
                return;
            }

            taskProgressView.setDurationLeft(durationLeft);
        }
    }

    public void start(TaskDto taskDto) {
        taskProgressView.openModal(taskDto.getName());
        taskProgressView.setDurationLeft(taskDto.getDuration());

        timer = new Timer("Timer");

        UpdateDurationLeftTask updateDurationLeftTask = new UpdateDurationLeftTask(
                taskProgressView,
                taskDto.getDuration(),
                this::onDurationLeft
        );

        timer.scheduleAtFixedRate(
                updateDurationLeftTask,
                0,
                1000L
        );
    }

    private void onDurationLeft() {
        taskProgressView.bell();
        terminate();
    }

    public void terminate() {
        Optional.ofNullable(timer)
                .ifPresent(timerParam -> {
                    timerParam.cancel();
                    taskProgressView.closeModal();
                });
    }

}
