package org.tomaszkowalczyk94.pomodorotasksmanager.core.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class ScheduledCalculator {

    String dayOfTheWeek;
    String dayOfTheMonth;
    String month;
    
    boolean isScheduledFor(LocalDate date) {
        return false;
    }
}
