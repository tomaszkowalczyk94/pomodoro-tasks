package org.tomaszkowalczyk94.commandline.gui;

import com.googlecode.lanterna.gui2.dialogs.ActionListDialog;
import com.googlecode.lanterna.gui2.dialogs.ActionListDialogBuilder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TaskActionListBuilder implements GuiElementsBuilder<ActionListDialog>  {

    Runnable onRemove;

    @Override
    public ActionListDialog build() {
        return new ActionListDialogBuilder()
                .setTitle("Actions")
                .addAction("Remove", onRemove)
                .build();
    }
}
