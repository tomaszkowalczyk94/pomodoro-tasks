package org.tomaszkowalczyk94.pomodorotasksmanager.core.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.function.Predicate;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class ScheduledCalculator {

    String dayOfTheWeek;
    String dayOfTheMonth;
    String month;

    Predicate<LocalDate> predicate;

    public ScheduledCalculator(String dayOfTheWeek, String dayOfTheMonth, String month) {
        this.dayOfTheWeek = dayOfTheWeek;
        this.dayOfTheMonth = dayOfTheMonth;
        this.month = month;

        predicate = buildPredicatesChain();
    }

    public boolean isScheduledFor(LocalDate localDate) {
        return predicate.test(localDate);
    }


    private Predicate<LocalDate> buildPredicatesChain() {
        Predicate<LocalDate> dayOfTheWeekPredicate = buildStarPredicate(dayOfTheWeek)
                .or(dateParam -> dateParam.getDayOfWeek().getValue() == Long.parseLong(dayOfTheWeek));

        Predicate<LocalDate> dayOfTheMonthPredicate = buildStarPredicate(dayOfTheMonth)
                .or(dateParam -> dateParam.getDayOfMonth() == Integer.parseInt(dayOfTheMonth));

        Predicate<LocalDate> monthPredicate = buildStarPredicate(month)
                .or(dateParam -> dateParam.getMonthValue() == Long.parseLong(month));

        return dayOfTheWeekPredicate
                .and(dayOfTheMonthPredicate)
                .and(monthPredicate);
    }

    private Predicate<LocalDate> buildStarPredicate(String cronValue) {
        return date -> cronValue.equals("*");
    }
}
