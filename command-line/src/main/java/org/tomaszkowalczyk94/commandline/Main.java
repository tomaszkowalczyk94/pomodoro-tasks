package org.tomaszkowalczyk94.commandline;


import org.hexworks.zircon.api.*;
import org.hexworks.zircon.api.application.AppConfig;
import org.hexworks.zircon.api.component.Button;
import org.hexworks.zircon.api.component.VBox;
import org.hexworks.zircon.api.data.Size;
import org.hexworks.zircon.api.graphics.BoxType;
import org.hexworks.zircon.api.grid.TileGrid;
import org.hexworks.zircon.api.screen.Screen;
import org.jetbrains.annotations.NotNull;
import static org.hexworks.zircon.api.ComponentDecorations.box;

import static org.hexworks.zircon.api.Components.*;

public class Main {
    public static void main(String[] args) {
        TileGrid tileGrid = createTileGrid();
        Screen taskScreen = createTasksScreen(tileGrid);
        SwingApplications
        taskScreen.display();

        taskScreen.setTheme(ColorThemes.afterglow());
    }

    @NotNull
    private static Screen createTasksScreen(TileGrid tileGrid) {
        Screen taskScreen = Screen.create(tileGrid);

        VBox column = vbox()
                .withSize(taskScreen.getSize())
                .withDecorations(box(BoxType.DOUBLE, "taski"))
                .build();

        column.addComponent(createTaskButton());
        column.addComponent(createTaskButton());
        column.addComponent(createTaskButton());

        taskScreen.addComponent(column);

        return taskScreen;
    }

    @NotNull
    private static TileGrid createTileGrid() {
        return SwingApplications.startTileGrid(
                AppConfig.newBuilder()
                        .withSize(Size.create(60, 30))
                        .withDefaultTileset(CP437TilesetResources.rexPaint16x16())
                        .build());
    }

    private static Button createTaskButton() {
       return button()
                .withText("Click me!")
               .build();
    }


}
