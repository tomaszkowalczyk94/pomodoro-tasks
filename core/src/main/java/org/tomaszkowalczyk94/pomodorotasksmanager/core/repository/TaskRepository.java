package org.tomaszkowalczyk94.pomodorotasksmanager.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.tomaszkowalczyk94.pomodorotasksmanager.core.entity.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {

}
