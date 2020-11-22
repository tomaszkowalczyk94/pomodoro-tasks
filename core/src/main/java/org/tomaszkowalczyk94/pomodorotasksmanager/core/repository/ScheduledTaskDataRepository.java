package org.tomaszkowalczyk94.pomodorotasksmanager.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.tomaszkowalczyk94.pomodorotasksmanager.core.entity.ScheduledTaskData;

public interface ScheduledTaskDataRepository extends CrudRepository<ScheduledTaskData, Long> {

}
