package org.tomaszkowalczyk94.pomodorotasksmanager.commandline.gui;

import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.Panel;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class TasksListRegistry {
    Panel panel;
    ActionListBox actionListBoxOfTasks;
}
