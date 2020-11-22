package org.tomaszkowalczyk94.pomodorotasksmanager.core.service;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.tomaszkowalczyk94.pomodorotasksmanager.core.entity.ScheduledTaskData;
import org.tomaszkowalczyk94.pomodorotasksmanager.core.entity.Task;
import org.tomaszkowalczyk94.pomodorotasksmanager.core.entity.TaskDate;
import org.tomaszkowalczyk94.pomodorotasksmanager.core.repository.ScheduledTaskDataRepository;
import org.tomaszkowalczyk94.pomodorotasksmanager.core.repository.TaskDateRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CalendarImplTest {

    @AllArgsConstructor
    @FieldDefaults(makeFinal = true)
    private static class GetScheduledTasksByDayParam {
        LocalDate date;
        List<ScheduledTaskData> scheduledTaskData;
        Consumer<CalendarDay> assertsConsumer;
    }

    @ParameterizedTest
    @MethodSource("getScheduledTasksByDayProvider")
    void getScheduledTasksByDay(
            LocalDate date,
            List<ScheduledTaskData> scheduledTaskData,
            Consumer<CalendarDay> assertsConsumer
    ) {
        //given
        var scheduledTaskDataRepository = mock(ScheduledTaskDataRepository.class);
        when(scheduledTaskDataRepository.findAll()).thenReturn(scheduledTaskData);
        Calendar calendar = new CalendarImpl(scheduledTaskDataRepository, mock(TaskDateRepository.class));

        //when
        CalendarDay day = calendar.getDay(date);

        //then
        assertThat(day).isNotNull();
        assertsConsumer.accept(day);
    }

    static Stream<Arguments> getScheduledTasksByDayProvider() {
        return Stream.of(
                arguments(
                        LocalDate.of(2000, 12, 5),
                        List.of(
                                createDummyScheduledTask(createDummyTask("myDummyTask"), "*", "*", "*")
                        ),
                        (Consumer<CalendarDay>) day -> assertThat(day.getItems())
                                .extracting("status", "task")
                                .containsExactly(tuple(CalendarItemStatus.TODO, createDummyTask("myDummyTask")))
                ),
                arguments(
                        new GetScheduledTasksByDayParam(
                                LocalDate.of(2020, 11, 22),
                                List.of(
                                        createDummyScheduledTask(createDummyTask("sundayTask"), "7", "*", "*"),
                                        createDummyScheduledTask(createDummyTask("saturdayTask"), "6", "*", "*")
                                ),
                                day -> assertThat(day.getItems())
                                        .extracting("status", "task")
                                        .containsExactly(tuple(CalendarItemStatus.TODO, createDummyTask("sundayTask")))
                        )
                ),
                arguments(
                        new GetScheduledTasksByDayParam(
                                LocalDate.of(2020, 11, 22),
                                List.of(
                                        createDummyScheduledTask(createDummyTask("task22dayOfMonth"), "*", "22", "*"),
                                        createDummyScheduledTask(createDummyTask("task1dayOfMonth"), "*", "1", "*")
                                ),
                                day -> assertThat(day.getItems())
                                        .extracting("status", "task")
                                        .containsExactly(tuple(CalendarItemStatus.TODO, createDummyTask("task22dayOfMonth")))
                        )
                ),
                arguments(
                        new GetScheduledTasksByDayParam(
                                LocalDate.of(2020, 11, 22),
                                List.of(
                                        createDummyScheduledTask(createDummyTask("taskDecember"), "*", "*", "12"),
                                        createDummyScheduledTask(createDummyTask("taskOctober"), "*", "*", "11")
                                ),
                                day -> assertThat(day.getItems())
                                        .extracting("status", "task")
                                        .containsExactly(tuple(CalendarItemStatus.TODO, createDummyTask("taskOctober")))
                        )
                )
        );
    }

    private static ScheduledTaskData createDummyScheduledTask(Task dummyTask, String dayOfTheWeek, String dayOfTheMonth, String month) {
        ScheduledTaskData scheduledTaskData = ScheduledTaskData.builder()
                .id(99L)
                .dayOfTheMonth(dayOfTheMonth)
                .dayOfTheWeek(dayOfTheWeek)
                .month(month)
                .task(dummyTask)
                .build();

        dummyTask.setScheduledTaskData(Set.of(scheduledTaskData));
        scheduledTaskData.setTask(dummyTask);
        return scheduledTaskData;
    }

    private static Task createDummyTask(String name) {
        return Task.builder()
                .name(name)
                .build();
    }

}