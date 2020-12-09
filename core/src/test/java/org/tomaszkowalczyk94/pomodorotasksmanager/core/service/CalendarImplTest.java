package org.tomaszkowalczyk94.pomodorotasksmanager.core.service;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.tomaszkowalczyk94.pomodorotasksmanager.core.entity.ScheduledTaskInfo;
import org.tomaszkowalczyk94.pomodorotasksmanager.core.entity.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CalendarImplTest {

    @AllArgsConstructor
    @FieldDefaults(makeFinal = true)
    private static class GetScheduledTasksByDayParam {
        LocalDate date;
        List<ScheduledTaskInfo> scheduledTaskData;
        Consumer<CalendarDay> assertsConsumer;
    }

    @ParameterizedTest
    @MethodSource("getScheduledTasksByDayProvider")
    void getScheduledTasksByDay(
            LocalDate date,
            List<ScheduledTaskInfo> scheduledTaskData,
            Consumer<CalendarDay> assertsConsumer
    ) {
        //@todo
//        //given
//        var scheduledTaskDataRepository = mock(ScheduledTaskDataRepository.class);
//        when(scheduledTaskDataRepository.findAll()).thenReturn(scheduledTaskData);
//        Calendar calendar = new CalendarImpl(scheduledTaskDataRepository, mock(TaskDateRepository.class));
//
//        //when
//        CalendarDay day = calendar.getDay(date);
//
//        //then
//        assertThat(day).isNotNull();
//        assertsConsumer.accept(day);
    }

    static Stream<Arguments> getScheduledTasksByDayProvider() {
        return Stream.of(
                arguments(
                        LocalDate.of(2000, 12, 5),
                        List.of(
                                createDummyScheduledTask(createDummyTask("myDummyTask"), "* * *")
                        ),
                        (Consumer<CalendarDay>) day -> assertThat(day.getItems())
                                .extracting("status", "task")
                                .containsExactly(tuple(CalendarItemStatus.TODO, createDummyTask("myDummyTask")))
                ),
                arguments(
                        LocalDate.of(2020, 11, 22),
                        List.of(
                                createDummyScheduledTask(createDummyTask("sundayTask"), "7 * *"),
                                createDummyScheduledTask(createDummyTask("saturdayTask"), "6 * *")
                        ),
                        (Consumer<CalendarDay>) day -> assertThat(day.getItems())
                                .extracting("status", "task")
                                .containsExactly(tuple(CalendarItemStatus.TODO, createDummyTask("sundayTask")))

                ),
                arguments(
                        LocalDate.of(2020, 11, 22),
                        List.of(
                                createDummyScheduledTask(createDummyTask("task22dayOfMonth"), "* 22 *"),
                                createDummyScheduledTask(createDummyTask("task1dayOfMonth"), "* 1 *")
                        ),
                        (Consumer<CalendarDay>) day -> assertThat(day.getItems())
                                .extracting("status", "task")
                                .containsExactly(tuple(CalendarItemStatus.TODO, createDummyTask("task22dayOfMonth")))

                ),
                arguments(
                        LocalDate.of(2020, 11, 22),
                        List.of(
                                createDummyScheduledTask(createDummyTask("taskDecember"), "* * 12"),
                                createDummyScheduledTask(createDummyTask("taskOctober"), "* * 11")
                        ),
                        (Consumer<CalendarDay>) day -> assertThat(day.getItems())
                                .extracting("status", "task")
                                .containsExactly(tuple(CalendarItemStatus.TODO, createDummyTask("taskOctober")))

                )
        );
    }

    private static ScheduledTaskInfo createDummyScheduledTask(Task dummyTask, String cronValue) {
        return ScheduledTaskInfo.build(b -> b
                .withCron(cronValue)
                .task(dummyTask)
        );
    }

    private static Task createDummyTask(String name) {
        return Task.build(b -> b.name(name));
    }
}