package org.tomaszkowalczyk94.pomodorotasksmanager.core.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

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
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("isScheduledWithInvalidStrings")
    void isScheduledWithInvalidStrings(String dayOfTheWeek, String dayOfTheMonth, String month) {
        //given
        ScheduledCalculator scheduledCalculator = new ScheduledCalculator(dayOfTheWeek, dayOfTheMonth, month);
        LocalDate date = LocalDate.parse("2007-12-03");

        //when & then
        assertThatThrownBy(() -> scheduledCalculator.isScheduledFor(date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> isScheduledForProvider() {
        return Stream.of(
                arguments(LocalDate.parse("2020-11-01"), "01", "11", "2020", true),
                arguments(LocalDate.parse("2020-11-01"), "*", "*", "*", true),

                arguments(LocalDate.parse("2020-11-01"), "01", "*", "*", true),
                arguments(LocalDate.parse("2020-11-01"), "02", "*", "*", false),
                arguments(LocalDate.parse("2020-11-01"), "02", "11", "2020", false),

                arguments(LocalDate.parse("2020-11-01"), "*", "11", "*", true),
                arguments(LocalDate.parse("2020-11-01"), "*", "07", "*", false),
                arguments(LocalDate.parse("2020-11-01"), "01", "07", "2020", false),

                arguments(LocalDate.parse("2020-11-01"), "*", "*", "2020", true),
                arguments(LocalDate.parse("2020-11-01"), "*", "*", "1990", false),
                arguments(LocalDate.parse("2020-11-01"), "*", "*", "2111", false)
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
}