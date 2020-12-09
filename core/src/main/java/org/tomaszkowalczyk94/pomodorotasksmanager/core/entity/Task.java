package org.tomaszkowalczyk94.pomodorotasksmanager.core.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(buildMethodName = "simpleBuild")
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    @Singular
    private Set<TaskDate> taskDates;

    @OneToMany
    @Builder.Default
    private Set<ScheduledTaskInfo> scheduledTaskInfos = new HashSet<>();

    @OneToMany
    @Singular
    private Set<DoneTask> doneTasks;

    private String name;

    private Duration duration;

    private int quantity;

    public static class TaskBuilder {

        private final List<Consumer<Task>> afterBuild = List.of(
            this::linkDatesToTask
        );

        public TaskBuilder withDate(Consumer<TaskDate.TaskDateBuilder> consumer) {
            taskDate(TaskDate.builder().build(consumer));
            return this;
        }

        public Task build() {
            Task task = simpleBuild();
            afterBuild.forEach(consumer->consumer.accept(task));
            return task;
        }

        private void linkDatesToTask(Task task) {
            task.getTaskDates().forEach(taskDate -> taskDate.setTask(task));
        }
    }

    public static Task build(Consumer<TaskBuilder> consumer) {
        TaskBuilder taskBuilder = new TaskBuilder();
        consumer.accept(taskBuilder);
        return taskBuilder.build();
    }

}
