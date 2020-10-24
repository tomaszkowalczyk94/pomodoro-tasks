package org.tomaszkowalczyk94.core.pomodorotasksmanager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.tomaszkowalczyk94.core.pomodorotasksmanager.entity.Task;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.tomaszkowalczyk94.core.pomodorotasksmanager.RestUtil.createAndGetResource;
import static org.tomaszkowalczyk94.core.pomodorotasksmanager.RestUtil.getResource;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class TaskControllerIT {

    @LocalServerPort
    private int port;

    @Test
    void newEntryTest() {
        //given
        var spec = RestUtil.buildRequestSpecification(port);

        Task dummyTask = createDummyTask();

        //when
        Task resource = createAndGetResource(spec, "/tasks", dummyTask, Task.class);

        //then
        assertThat(resource).isEqualToIgnoringGivenFields(dummyTask, "id");
    }

    @Test
    void getEntryTest() {
        //given
        var spec = RestUtil.buildRequestSpecification(port);
        Task dummyTask = createDummyTask();
        Task createdTask = createAndGetResource(spec, "/tasks", dummyTask, Task.class);

        //when
        Task task = getResource(spec, "/tasks/" + createdTask.getId(), Task.class);

        //then
        assertThat(task).isEqualToIgnoringGivenFields(createdTask, "id");
    }

    private Task createDummyTask() {
        return Task.builder()
                .name("testName")
                .build();
    }
}