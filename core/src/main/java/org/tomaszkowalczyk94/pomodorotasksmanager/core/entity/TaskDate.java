package org.tomaszkowalczyk94.pomodorotasksmanager.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.function.Consumer;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(buildMethodName = "simpleBuild")
@Entity
public class TaskDate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Task task;

    private LocalDate date;

    public static class TaskDateBuilder implements LambdaBuilder<TaskDate> {

        public TaskDateBuilder with(LocalDate date, long id) {
            date(date);
            id(id);
            return this;
        }

        public TaskDate build(Consumer<TaskDateBuilder> consumer) {
            consumer.accept(this);
            return build();
        }
    }

    public static TaskDate build(Consumer<TaskDateBuilder> consumer) {
        return TaskDate.builder().build(consumer);
    }
}
