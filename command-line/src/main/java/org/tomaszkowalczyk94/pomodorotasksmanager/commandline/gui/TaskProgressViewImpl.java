package org.tomaszkowalczyk94.pomodorotasksmanager.commandline.gui;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.time.DurationFormatUtils;

import java.time.Duration;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskProgressViewImpl implements TaskProgressView {
    final MainElementsRegistry mainElementsRegistry;
    final TaskProgressElementsRegistry taskProgressElementsRegistry;

    @SneakyThrows
    @Override
    public void openModal(String taskName) {
        mainElementsRegistry.getTextGui().addWindow(
                taskProgressElementsRegistry.getWindow()
        );
        mainElementsRegistry.getScreen().refresh();
    }

    @SneakyThrows
    @Override
    public void setDurationLeft(Duration duration) {
        taskProgressElementsRegistry.getTimeLabel().setText(
                humanReadableFormat(duration)
        );
    }

    @Override
    public void closeModal() {
        mainElementsRegistry.getTextGui().removeWindow(
                taskProgressElementsRegistry.getWindow()
        );
    }

    public void addStopButtonEvent(Runnable runnable) {
        taskProgressElementsRegistry.getStopButton().addListener(button -> runnable.run());
    }

    @SneakyThrows
    @Override
    public void bell() {
        mainElementsRegistry.getTerminal().bell();
    }

    private String humanReadableFormat(Duration duration) {
        return DurationFormatUtils.formatDuration(duration.toMillis(), "**mm:ss**", true);
    }
}
