package org.tomaszkowalczyk94.pomodorotasksmanager.core.entity.dsl.fun;

import org.tomaszkowalczyk94.pomodorotasksmanager.core.entity.TaskDate;

import java.util.function.Consumer;

public class TaskDateBuilder extends TaskDate.SimpleBuilder {

    public TaskDateBuilder() {
        super();
    }

    public static TaskDate build(Consumer<TaskDateBuilder> consumer) {
        TaskDateBuilder taskDateBuilder = new TaskDateBuilder();
        consumer.accept(taskDateBuilder);
        return taskDateBuilder.build();
    }

}
