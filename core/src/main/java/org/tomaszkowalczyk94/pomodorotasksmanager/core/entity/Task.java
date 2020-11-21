package org.tomaszkowalczyk94.pomodorotasksmanager.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Duration;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private Set<DoneTaskData> doneTaskInfo;

    @OneToMany
    private Set<ScheduledTaskData> scheduledTaskData;

    @OneToMany
    private Set<TaskDateTime> taskDateTimes;

    private String name;

    private Duration duration;

    private int quantity;
}
