package org.tomaszkowalczyk94.pomodorotasksmanager.core.entity.dsl.fun;

import org.junit.jupiter.api.Test;
import org.tomaszkowalczyk94.pomodorotasksmanager.core.entity.Task;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;

class TaskBuilderTest {

    @Test
    void name() {

        Task task = Task.build(t -> {
            t.quantity(1);
            t.withDate(LocalDate.parse("2020-01-01"));
        });

        assertThat(task)
                .extracting("quantity", "taskDates.date")
                .isEqualTo(tuple(1, LocalDate.parse("2020-01-01")));
    }
}