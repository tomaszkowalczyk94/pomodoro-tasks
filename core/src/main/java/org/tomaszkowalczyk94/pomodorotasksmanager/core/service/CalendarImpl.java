package org.tomaszkowalczyk94.pomodorotasksmanager.core.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.tomaszkowalczyk94.pomodorotasksmanager.core.entity.ScheduledTaskData;
import org.tomaszkowalczyk94.pomodorotasksmanager.core.repository.ScheduledTaskDataRepository;
import org.tomaszkowalczyk94.pomodorotasksmanager.core.repository.TaskDateRepository;

import java.time.LocalDate;
import java.util.Collection;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class CalendarImpl implements Calendar {

    ScheduledTaskDataRepository scheduledTaskDataRepository;
    TaskDateRepository taskDateRepository;

    @Override
    public CalendarDay getDay(LocalDate day) {
        Iterable<ScheduledTaskData> all = scheduledTaskDataRepository.findAll();

        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Collection<CalendarDay> getDays(Collection<CalendarDay> days) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Collection<CalendarDay> getDays(LocalDate from, LocalDate to) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
