package org.tomaszkowalczyk94.commandline.gui;

import com.googlecode.lanterna.gui2.Panel;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class AddTaskFormRegistry {
    Panel panel;
}
