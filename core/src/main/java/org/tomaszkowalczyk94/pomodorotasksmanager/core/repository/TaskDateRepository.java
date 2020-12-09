package org.tomaszkowalczyk94.pomodorotasksmanager.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.tomaszkowalczyk94.pomodorotasksmanager.core.entity.TaskDate;

import java.time.LocalDate;
import java.util.List;

public interface TaskDateRepository extends CrudRepository<TaskDate, Long> {

    List<TaskDate> getByDate(LocalDate date);

}
