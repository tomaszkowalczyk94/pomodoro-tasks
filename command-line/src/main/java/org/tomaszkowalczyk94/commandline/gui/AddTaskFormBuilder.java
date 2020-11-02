package org.tomaszkowalczyk94.commandline.gui;

import com.googlecode.lanterna.gui2.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class AddTaskFormBuilder implements GuiElementsBuilder<AddTaskFormRegistry> {

    MainElementsRegistry mainElementsRegistry;

    @Override
    public AddTaskFormRegistry build() {
        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));

        panel.addComponent(new Label("name:"));
        panel.addComponent(new TextBox());

        panel.addComponent(new Label("test val 2:"));
        panel.addComponent(new TextBox());

        panel.addComponent(new Label("test val 3:"));
        panel.addComponent(new TextBox());

        panel.addComponent(new Button("Dodaj taska"));

        mainElementsRegistry
                .getMainPanel()
                .addComponent(panel.withBorder(Borders.singleLine("tasks")));

        return AddTaskFormRegistry.builder()
                .panel(panel)
                .build();
    }
}
