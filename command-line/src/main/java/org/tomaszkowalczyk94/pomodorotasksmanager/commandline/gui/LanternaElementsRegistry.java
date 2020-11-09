package org.tomaszkowalczyk94.pomodorotasksmanager.commandline.gui;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Registry of all gui elements
 */
@Getter
@Builder
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class LanternaElementsRegistry {
    @NonNull MainElementsRegistry mainElementsRegistry;
    @NonNull TasksListRegistry tasksListRegistry;
    @NonNull AddTaskFormRegistry addTaskFormRegistry;
    @NonNull TaskProgressElementsRegistry taskProgressElementsRegistry;
}
