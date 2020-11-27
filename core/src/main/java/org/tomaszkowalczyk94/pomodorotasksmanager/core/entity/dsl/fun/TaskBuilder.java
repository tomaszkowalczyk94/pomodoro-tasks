package org.tomaszkowalczyk94.pomodorotasksmanager.core.entity.dsl.fun;

import org.tomaszkowalczyk94.pomodorotasksmanager.core.entity.Task;


import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class TaskBuilder extends Task.SimpleBuilder {

    public TaskBuilder() {
        super();
    }

    List<Consumer<Task>> afterBuild = Collections.emptyList();

    public static Task build(Consumer<TaskBuilder> consumer) {
        TaskBuilder taskBuilder = new TaskBuilder();
        consumer.accept(taskBuilder);
        return taskBuilder.build();
    }

    public TaskBuilder withDate(Consumer<TaskDateBuilder> consumer) {
        taskDate(TaskDateBuilder.build(consumer));
        afterBuild.add(linkDatesToTask());
        return this;
    }

    public TaskBuilder withDate(LocalDate date) {
        return withDate(date, 0);
    }
    public TaskBuilder withDate(LocalDate date, long id) {
        return withDate(builder -> {
            builder.date(date);
            builder.id(id);
        });
    }

    private Consumer<Task> linkDatesToTask() {
        return task -> task.getTaskDates().forEach(taskDate -> taskDate.setTask(task));
    }

    @Override
    public Task build() {
        Task task = super.build();
        afterBuild.forEach(consumer -> consumer.accept(task));
        return task;
    }
}
