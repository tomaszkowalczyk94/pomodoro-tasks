package org.tomaszkowalczyk94.pomodorotasksmanager.commandline.gui;

import com.googlecode.lanterna.gui2.*;

public class TaskProgressElementsBuilder implements GuiElementsBuilder<TaskProgressElementsRegistry> {
    @Override
    public TaskProgressElementsRegistry build() {

        Window window = new BasicWindow("Task in progress");

        Panel panel = createEmptyPanel();
        window.setComponent(panel);

        Label taskNameLabel = new Label("taskNameLabel");
        panel.addComponent(taskNameLabel);

        Label timeLabel = new Label("timeLabel");
        panel.addComponent(timeLabel);

        Button stopButton = new Button("Stop");
        panel.addComponent(stopButton);

        return TaskProgressElementsRegistry.builder()
                .window(window)
                .taskNameLabel(taskNameLabel)
                .timeLabel(timeLabel)
                .stopButton(stopButton)
                .build();
    }

    private Panel createEmptyPanel() {
        Panel panel = new Panel();
        panel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
        return panel;
    }
}
