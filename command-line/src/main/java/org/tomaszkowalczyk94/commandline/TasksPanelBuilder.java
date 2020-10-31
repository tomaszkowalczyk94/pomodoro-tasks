package org.tomaszkowalczyk94.commandline;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.ActionListDialog;
import com.googlecode.lanterna.gui2.dialogs.ActionListDialogBuilder;

public class TasksPanelBuilder {

    public Panel build(WindowBasedTextGUI textGUI) {

        Panel contentPanel = new Panel();

        TerminalSize size = new TerminalSize(14, 10);
        ActionListBox actionListBox = new ActionListBox(size);
        contentPanel.addComponent(actionListBox);

        actionListBox.addItem("test 1", () -> openActionList(textGUI));
        actionListBox.addItem("test 2", () -> openActionList(textGUI));

        return contentPanel;
    }

    private static void openActionList(WindowBasedTextGUI textGUI) {
        createActionList().showDialog(textGUI);
    }

    private static ActionListDialog createActionList() {
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
