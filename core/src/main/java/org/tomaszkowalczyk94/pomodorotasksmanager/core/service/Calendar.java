package org.tomaszkowalczyk94.pomodorotasksmanager.core.service;

import java.time.LocalDate;
import java.util.Collection;

public interface Calendar {

    CalendarDay getDay(LocalDate day);
    Collection<CalendarDay> getDays(Collection<CalendarDay> days);
    Collection<CalendarDay> getDays(LocalDate from, LocalDate to);
}
