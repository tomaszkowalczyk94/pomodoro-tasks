package org.tomaszkowalczyk94.pomodorotasksmanager.commandline.gui;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class AddTaskFormRegistry {
    @NonNull Panel panel;
    @NonNull TextBox nameTextBox;
    @NonNull TextBox minutesTextBox;
    @NonNull Button addButton;
}
