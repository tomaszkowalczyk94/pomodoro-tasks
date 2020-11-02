package org.tomaszkowalczyk94.commandline.gui;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GuiBuilder {

    @SneakyThrows
    public GuiRegistry build() {

        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        try (Screen screen = defaultTerminalFactory.createScreen()) {
            screen.startScreen();

            MainElementsRegistry mainElementsRegistry = createMainElements(screen);
            TasksListRegistry tasksListRegistry = createTasksListElements(mainElementsRegistry);
            AddTaskFormRegistry addTaskFormRegistry = createAddTaskFormRegistry(mainElementsRegistry);

            mainElementsRegistry.getWindow().waitUntilClosed();

            return GuiRegistry.builder()
                    .mainElementsRegistry(mainElementsRegistry)
                    .tasksListRegistry(tasksListRegistry)
                    .addTaskFormRegistry(addTaskFormRegistry)
                    .build();
        }
    }

    private MainElementsRegistry createMainElements(Screen screen) {
        return new MainElementsBuilder(screen).build();
    }

    private TasksListRegistry createTasksListElements(MainElementsRegistry mainElementsRegistry) {
        return new TasksListBuilder(mainElementsRegistry).build();
    }

    private AddTaskFormRegistry createAddTaskFormRegistry(MainElementsRegistry mainElementsRegistry) {
        return new AddTaskFormBuilder(mainElementsRegistry).build();
    }
}
