package org.tomaszkowalczyk94.core.pomodorotasksmanager.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.tomaszkowalczyk94.core.pomodorotasksmanager.entity.Task;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class DoneTaskDataDto {
    private Long id;

    private Task task;

    private LocalDate done;

}
