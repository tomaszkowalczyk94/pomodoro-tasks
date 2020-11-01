package org.tomaszkowalczyk94.commandline.gui;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

import java.util.Collections;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class GuiRunner {

    public static GuiRunner build() {
        return new GuiRunner(new TasksPanelBuilder(), new AddTaskPanelBuilder());
    }

    TasksPanelBuilder tasksPanelBuilder;
    AddTaskPanelBuilder addTaskPanelBuilder;

    @SneakyThrows
    public void run() {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();

        try (Screen screen = defaultTerminalFactory.createScreen()) {
            screen.startScreen();

            final WindowBasedTextGUI textGui = new MultiWindowTextGUI(screen);

            Window window = createWindow(textGui);
            window.setHints(Collections.singletonList(Window.Hint.CENTERED));

            textGui.addWindowAndWait(window);
        }
    }

    private Window createWindow(WindowBasedTextGUI textGui) {
        final Window window = new BasicWindow("Tasks");

        Panel mainPanel = new Panel();
        mainPanel.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));

        mainPanel.addComponent(tasksPanelBuilder.build(textGui).withBorder(Borders.singleLine("tasks")));
        mainPanel.addComponent(addTaskPanelBuilder.build(textGui).withBorder(Borders.singleLine("add task")));

        window.setComponent(mainPanel);
        return window;
    }
}
