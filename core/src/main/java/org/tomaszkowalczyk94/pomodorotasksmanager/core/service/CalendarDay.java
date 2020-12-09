package org.tomaszkowalczyk94.pomodorotasksmanager.core.service;

import lombok.Data;

import java.util.List;

@Data
public class CalendarDay {

    List<CalendarItem> items;

}
