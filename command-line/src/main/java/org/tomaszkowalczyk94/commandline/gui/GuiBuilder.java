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
    public Gui build() {

        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        Screen screen = defaultTerminalFactory.createScreen(); //NOSONAR
        screen.startScreen();

        MainElementsRegistry mainElementsRegistry = createMainElements(screen);
        TasksListRegistry tasksListRegistry = createTasksListElements(mainElementsRegistry);
        AddTaskFormRegistry addTaskFormRegistry = createAddTaskFormRegistry(mainElementsRegistry);

        ViewsRegistry viewsRegistry = ViewsRegistry.builder()
                .taskListView(new TaskViewImpl(tasksListRegistry, mainElementsRegistry))
                .build();

        LanternaElementsRegistry lanternaElementsRegistry = LanternaElementsRegistry.builder()
                .mainElementsRegistry(mainElementsRegistry)
                .tasksListRegistry(tasksListRegistry)
                .addTaskFormRegistry(addTaskFormRegistry)
                .build();

        return GuiImpl.builder()
                .viewsRegistry(viewsRegistry)
                .lanternaElementsRegistry(lanternaElementsRegistry)
                .build();

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
