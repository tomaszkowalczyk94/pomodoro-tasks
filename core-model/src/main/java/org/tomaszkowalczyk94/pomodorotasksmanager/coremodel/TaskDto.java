package org.tomaszkowalczyk94.pomodorotasksmanager.coremodel;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    Long id;

    @Builder.Default
    private Set<DoneTaskDataDto> doneTaskInfo = new HashSet<>();

    String name;

    Duration duration;

    private int quantity;
}
