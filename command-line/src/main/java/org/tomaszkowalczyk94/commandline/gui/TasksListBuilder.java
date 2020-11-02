package org.tomaszkowalczyk94.commandline.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.ActionListDialog;
import com.googlecode.lanterna.gui2.dialogs.ActionListDialogBuilder;
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


        actionListBoxOfTasks.addItem("test 1", () -> openActionList(mainElementsRegistry.getTextGui()));
        actionListBoxOfTasks.addItem("test 2", () -> openActionList(mainElementsRegistry.getTextGui()));

        mainElementsRegistry
                .getMainPanel()
                .addComponent(panel.withBorder(Borders.singleLine("tasks")));

        return TasksListRegistry.builder()
                .panel(panel)
                .build();
    }

    private void openActionList(WindowBasedTextGUI textGUI) {
        createActionList().showDialog(textGUI);
    }

    private ActionListDialog createActionList() {
        return new ActionListDialogBuilder()
                .setTitle("Action List Dialog")
                .setDescription("Choose an item")
                .addAction("First Item", () -> {
                    // Do 1st thing...
                })
                .addAction("Second Item", () -> {
                    // Do 2nd thing...
                })
                .addAction("Third Item", () -> {
                    // Do 3rd thing...
                })
                .build();
    }

}
