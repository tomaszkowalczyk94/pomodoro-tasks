package org.tomaszkowalczyk94.pomodorotasksmanager.coremodel;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class DoneTaskDataDto {
    private Long id;

    private TaskDto task;

    private LocalDate done;

}
