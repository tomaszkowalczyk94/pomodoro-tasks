package org.tomaszkowalczyk94.commandline.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Panel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class TasksListBuilder implements GuiElementsBuilder<TasksListRegistry> {

    MainElementsRegistry mainElementsRegistry;

    public TasksListRegistry build() {
        Panel panel = new Panel();

        ActionListBox actionListBoxOfTasks = new ActionListBox(new TerminalSize(14, 10));
        panel.addComponent(actionListBoxOfTasks);

        mainElementsRegistry
                .getMainPanel()
                .addComponent(panel.withBorder(Borders.singleLine("tasks")));

        return TasksListRegistry.builder()
                .panel(panel)
                .actionListBoxOfTasks(actionListBoxOfTasks)
                .build();
    }
}
