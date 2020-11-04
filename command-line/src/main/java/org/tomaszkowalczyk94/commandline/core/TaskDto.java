package org.tomaszkowalczyk94.commandline.core;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.Duration;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class TaskDto {

    Long id;

    String name;

    Duration duration;
}
