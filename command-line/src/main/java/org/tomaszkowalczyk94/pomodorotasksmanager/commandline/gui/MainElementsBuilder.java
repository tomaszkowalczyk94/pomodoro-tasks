package org.tomaszkowalczyk94.pomodorotasksmanager.commandline.gui;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.Terminal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

import java.util.Collections;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class MainElementsBuilder implements GuiElementsBuilder<MainElementsRegistry> {

    Terminal terminal;

    @SneakyThrows
    @Override
    public MainElementsRegistry build() {

        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();

        WindowBasedTextGUI textGui = new MultiWindowTextGUI(screen);
        Window window = new BasicWindow("TEST");

        Panel mainPanel = new Panel();
        mainPanel.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));

        window.setComponent(mainPanel);

        window.setHints(Collections.singletonList(Window.Hint.CENTERED));
        textGui.addWindow(window);

        return MainElementsRegistry.builder()
                .terminal(terminal)
                .screen(screen)
                .textGui(textGui)
                .window(window)
                .mainPanel(mainPanel)
                .build();
    }
}
