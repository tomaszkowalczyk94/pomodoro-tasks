package org.tomaszkowalczyk94.pomodorotasksmanager.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tomaszkowalczyk94.pomodorotasksmanager.core.entity.dsl.fun.TaskDateBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.function.Consumer;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "SimpleBuilder")
@Entity
public class TaskDate {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Task task;

    private LocalDate date;

    public static TaskDate build(Consumer<TaskDateBuilder> consumer) {
        return TaskDateBuilder.build(consumer);
    }
}
