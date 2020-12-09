package org.tomaszkowalczyk94.pomodorotasksmanager.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.tomaszkowalczyk94.pomodorotasksmanager.core.entity.ScheduledTaskInfo;

public interface ScheduledTaskDataRepository extends CrudRepository<ScheduledTaskInfo, Long> {

}
