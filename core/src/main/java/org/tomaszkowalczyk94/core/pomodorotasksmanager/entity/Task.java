package org.tomaszkowalczyk94.core.pomodorotasksmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Duration;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    private Duration duration;
}
