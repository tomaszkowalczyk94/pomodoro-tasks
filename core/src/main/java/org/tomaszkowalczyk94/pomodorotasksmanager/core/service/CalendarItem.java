package org.tomaszkowalczyk94.pomodorotasksmanager.core.service;

import lombok.Data;
import org.tomaszkowalczyk94.pomodorotasksmanager.core.entity.Task;

@Data
public class CalendarItem {
    CalendarItemStatus status;
    Task task;
}
