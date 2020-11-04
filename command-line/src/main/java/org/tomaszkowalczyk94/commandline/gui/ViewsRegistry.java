package org.tomaszkowalczyk94.commandline.gui;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

/**
 * Registry of all view objects
 */
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Builder
@Getter
public class ViewsRegistry {
    @NonNull TaskListView taskListView;
    @NonNull AddTaskFormView addTaskView;
}
