package org.tomaszkowalczyk94.pomodorotasksmanager.coremodel;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.Duration;
import java.util.Set;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    Long id;

    private Set<DoneTaskDataDto> doneTaskInfo;

    String name;

    Duration duration;

    private int quantity;
}
