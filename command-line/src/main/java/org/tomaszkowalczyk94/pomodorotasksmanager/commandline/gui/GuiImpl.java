package org.tomaszkowalczyk94.pomodorotasksmanager.commandline.gui;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GuiImpl implements Gui {

    @NonNull @Getter
    ViewsRegistry viewsRegistry;

    @NonNull
    LanternaElementsRegistry lanternaElementsRegistry;

    @SneakyThrows
    public void waitForExit() {
        lanternaElementsRegistry
                .getMainElementsRegistry()
                .getWindow()
                .waitUntilClosed();

        lanternaElementsRegistry.getMainElementsRegistry().getScreen().close();
    }
}
