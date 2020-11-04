package org.tomaszkowalczyk94.commandline.gui;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.tomaszkowalczyk94.commandline.core.TaskDto;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class AddTaskFormViewImpl implements AddTaskFormView {

    AddTaskFormRegistry addTaskFormRegistry;

    public void addTaskButtonEvent(Runnable runnable) {
        addTaskFormRegistry.getAddButton().addListener(button -> runnable.run());
    }

    @Override
    public TaskDto getTaskFromForm() {
        return TaskDto.builder()
                .name(addTaskFormRegistry.getNameTextBox().getText())
                .build();
    }
}
