package org.tomaszkowalczyk94.pomodorotasksmanager.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.tomaszkowalczyk94.pomodorotasksmanager.core.entity.Task;
import org.tomaszkowalczyk94.pomodorotasksmanager.coremodel.TaskDto;


import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.tomaszkowalczyk94.pomodorotasksmanager.core.RestUtil.createAndGetResource;
import static org.tomaszkowalczyk94.pomodorotasksmanager.core.RestUtil.getResource;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class TaskControllerIT {

    @LocalServerPort
    private int port;

    @Test
    void newEntryTest() {
        //given
        var spec = RestUtil.buildRequestSpecification(port);

        TaskDto dummyTask = createDummyTask();

        //when
        TaskDto resource = createAndGetResource(spec, "/tasks", dummyTask, TaskDto.class);

        //then
        assertThat(resource).isEqualToIgnoringGivenFields(dummyTask, "id");
    }

    @Test
    void getEntryTest() {
        //given
        var spec = RestUtil.buildRequestSpecification(port);
        TaskDto dummyTask = createDummyTask();
        TaskDto createdTask = createAndGetResource(spec, "/tasks", dummyTask, TaskDto.class);

        //when
        Task task = getResource(spec, "/tasks/" + createdTask.getId(), Task.class);

        //then
        assertThat(task).isEqualToIgnoringGivenFields(createdTask, "id");
    }

    private TaskDto createDummyTask() {
        return TaskDto.builder()
                .name("testName")
                .doneTaskInfo(Collections.emptySet())
                .build();
    }
}