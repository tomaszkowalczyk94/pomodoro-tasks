package org.tomaszkowalczyk94.pomodorotasksmanager.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Format like in cron tasks. https://en.wikipedia.org/wiki/Cron
 * for example, hour can have value "*", or number from 0 to 23,
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ScheduledTaskData {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Task task;


    String minute;
    String hour;
    String dayOfTheMonth;
    String month;
    String dayOfTheWeek;
}
