package org.tomaszkowalczyk94.pomodorotasksmanager.core.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.stream.Stream;

import static java.time.DayOfWeek.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ScheduledCalculatorTest {

    @ParameterizedTest
    @MethodSource("isScheduledForProvider")
    void isScheduledFor(LocalDate date, String dayOfTheWeek, String dayOfTheMonth, String month, boolean expected) {
        //given
        ScheduledCalculator scheduledCalculator = new ScheduledCalculator(dayOfTheWeek, dayOfTheMonth, month);

        //when
        boolean actual = scheduledCalculator.isScheduledFor(date);

        //then
        assertThat(actual)
                .withFailMessage(String.format("fail for date: %s and params %s %s %s", date.toString(), dayOfTheWeek, dayOfTheMonth, month))
                .isEqualTo(expected);

    }

    @ParameterizedTest
    @MethodSource("isScheduledWithInvalidStrings")
    void isScheduledWithInvalidStrings(String dayOfTheWeek, String dayOfTheMonth, String month) {
//        //given
//        ScheduledCalculator scheduledCalculator = new ScheduledCalculator(dayOfTheWeek, dayOfTheMonth, month);
//        LocalDate date = LocalDate.parse("2007-12-03");
//
//        //when & then
//        assertThatThrownBy(() -> scheduledCalculator.isScheduledFor(date))
//                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> isScheduledForProvider() {
        return Stream.of(
                arguments(LocalDate.parse("2020-11-01"), "*", "*", "*", true),

                arguments(LocalDate.parse("2020-11-01"), toString(SUNDAY), "1", "11", true),
                arguments(LocalDate.parse("2020-11-01"), "*", "1", "11", true),
                arguments(LocalDate.parse("2020-11-01"), toString(MONDAY), "1", "11", false), //its not monday!

                arguments(LocalDate.parse("2021-05-31"), toString(MONDAY), "31", "05", true),
                arguments(LocalDate.parse("2021-05-31"), toString(MONDAY), "30", "05", false),
                arguments(LocalDate.parse("2021-05-31"), toString(MONDAY), "*", "05", true),

                arguments(LocalDate.parse("1990-01-01"), "*", "*", "01", true),
                arguments(LocalDate.parse("1990-01-01"), "*", "*", "09", false),

                arguments(LocalDate.parse("2020-11-02"), toString(MONDAY), "*", "*", true),
                arguments(LocalDate.parse("2020-11-03"), toString(MONDAY), "*", "*", false), // not monday
                arguments(LocalDate.parse("2020-11-09"), toString(MONDAY), "*", "*", true),
                arguments(LocalDate.parse("2020-11-11"), toString(MONDAY), "*", "*", false), // not monday
                arguments(LocalDate.parse("2020-11-16"), toString(MONDAY), "*", "*", true),
                arguments(LocalDate.parse("2020-11-23"), toString(MONDAY), "*", "*", true)
        );
    }

    static Stream<Arguments> isScheduledWithInvalidStrings() {
        return Stream.of(
                arguments("invalid", "invalid", "invalid"),
                arguments("x", "x", "x"),

                arguments("8", "1", "1"),
                arguments("-1", "1", "1"),

                arguments("1", "32", "1"),
                arguments("1", "0", "1"),
                arguments("1", "-1", "1"),

                arguments("1", "1", "13"),
                arguments("1", "1", "0"),
                arguments("1", "1", "01"),

                arguments("-", "-", "-"),
                arguments("-", "1", "1"),
                arguments("1", "-", "1"),
                arguments("1", "1", "-")
        );
    }

    static String toString(DayOfWeek dayOfWeek) {
        return String.valueOf(dayOfWeek.getValue());

    }
}