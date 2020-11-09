package org.tomaszkowalczyk94.pomodorotasksmanager.commandline.gui;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
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

        Terminal terminal = defaultTerminalFactory.createTerminal();

        MainElementsRegistry mainElementsRegistry = createMainElements(terminal);
        TasksListRegistry tasksListRegistry = createTasksListElements(mainElementsRegistry);
        AddTaskFormRegistry addTaskFormRegistry = createAddTaskFormRegistry(mainElementsRegistry);
        TaskProgressElementsRegistry taskProgressElementsRegistry = createTaskProgressElementsRegistry();

        ViewsRegistry viewsRegistry = ViewsRegistry.builder()
                .taskListView(new TaskListViewImpl(tasksListRegistry, mainElementsRegistry))
                .addTaskView(new AddTaskFormViewImpl(addTaskFormRegistry))
                .taskProgressView(new TaskProgressViewImpl(mainElementsRegistry, taskProgressElementsRegistry))
                .build();

        LanternaElementsRegistry lanternaElementsRegistry = LanternaElementsRegistry.builder()
                .mainElementsRegistry(mainElementsRegistry)
                .tasksListRegistry(tasksListRegistry)
                .addTaskFormRegistry(addTaskFormRegistry)
                .taskProgressElementsRegistry(taskProgressElementsRegistry)
                .build();

        return GuiImpl.builder()
                .viewsRegistry(viewsRegistry)
                .lanternaElementsRegistry(lanternaElementsRegistry)
                .build();
    }

    private TaskProgressElementsRegistry createTaskProgressElementsRegistry() {
        return new TaskProgressElementsBuilder().build();
    }

    private MainElementsRegistry createMainElements(Terminal terminal) {
        return new MainElementsBuilder(terminal).build();
    }

    private TasksListRegistry createTasksListElements(MainElementsRegistry mainElementsRegistry) {
        return new TasksListBuilder(mainElementsRegistry).build();
    }

    private AddTaskFormRegistry createAddTaskFormRegistry(MainElementsRegistry mainElementsRegistry) {
        return new AddTaskFormBuilder(mainElementsRegistry).build();
    }
}
