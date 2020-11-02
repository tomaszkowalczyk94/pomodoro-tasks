package org.tomaszkowalczyk94.commandline.gui;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class GuiRegistry {
    @NonNull MainElementsRegistry mainElementsRegistry;
    @NonNull TasksListRegistry tasksListRegistry;
    @NonNull AddTaskFormRegistry addTaskFormRegistry;
}
