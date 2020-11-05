package org.tomaszkowalczyk94.commandline.gui;

import com.googlecode.lanterna.gui2.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.regex.Pattern;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class AddTaskFormBuilder implements GuiElementsBuilder<AddTaskFormRegistry> {

    MainElementsRegistry mainElementsRegistry;

    @Override
    public AddTaskFormRegistry build() {
        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));

        panel.addComponent(new Label("name:"));
        TextBox nameTextBox = new TextBox();
        panel.addComponent(nameTextBox);

        panel.addComponent(new Label("minutes:"));
        TextBox minutesTextBox = new TextBox().setValidationPattern(Pattern.compile("[0-9]*"));
        panel.addComponent(minutesTextBox);

        Button addButton = new Button("Add task");
        panel.addComponent(addButton);

        mainElementsRegistry
                .getMainPanel()
                .addComponent(panel.withBorder(Borders.singleLine("tasks")));

        return AddTaskFormRegistry.builder()
                .panel(panel)
                .nameTextBox(nameTextBox)
                .minutesTextBox(minutesTextBox)
                .addButton(addButton)
                .build();
    }
}
