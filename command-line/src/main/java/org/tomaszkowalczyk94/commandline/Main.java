package org.tomaszkowalczyk94.commandline;


import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();

        try (Screen screen = defaultTerminalFactory.createScreen()) {
            screen.startScreen();

            final WindowBasedTextGUI textGui = new MultiWindowTextGUI(screen);
            final Window window = new BasicWindow("Tasks");

            Panel contentPanel = new TasksPanelBuilder().build(textGui);
            window.setComponent(contentPanel);

            textGui.addWindowAndWait(window);
        }
    }
}
