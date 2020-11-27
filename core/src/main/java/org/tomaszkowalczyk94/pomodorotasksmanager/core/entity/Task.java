package org.tomaszkowalczyk94.pomodorotasksmanager.core.entity;

import lombok.*;
import org.tomaszkowalczyk94.pomodorotasksmanager.core.entity.dsl.fun.TaskBuilder;

import javax.persistence.*;
import java.time.Duration;
import java.util.Set;
import java.util.function.Consumer;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "SimpleBuilder", access = AccessLevel.PUBLIC)
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    @Singular
    private Set<TaskDate> taskDates;

    @OneToMany
    @Singular
    private Set<ScheduledTaskData> scheduledTaskInfos;

    @OneToMany
    @Singular
    private Set<DoneTask> doneTasks;

    private String name;

    private Duration duration;

    private int quantity;

    public static Task build(Consumer<TaskBuilder> consumer) {
        return TaskBuilder.build(consumer);
    }
}
