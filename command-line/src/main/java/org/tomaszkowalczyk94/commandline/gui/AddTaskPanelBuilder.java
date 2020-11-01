package org.tomaszkowalczyk94.commandline.gui;

import com.googlecode.lanterna.gui2.*;

class AddTaskPanelBuilder {

    public Panel build(WindowBasedTextGUI textGUI) {
        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));

        panel.addComponent(new Label("name:"));
        panel.addComponent(new TextBox());

        panel.addComponent(new Label("test val 2:"));
        panel.addComponent(new TextBox());

        panel.addComponent(new Label("test val 3:"));
        panel.addComponent(new TextBox());


        panel.addComponent(new Button("Dodaj taska"));

        return panel;
    }

}
