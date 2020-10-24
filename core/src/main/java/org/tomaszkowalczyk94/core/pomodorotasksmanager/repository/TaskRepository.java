package org.tomaszkowalczyk94.core.pomodorotasksmanager.repository;

import org.springframework.data.repository.CrudRepository;
import org.tomaszkowalczyk94.core.pomodorotasksmanager.entity.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {

}
