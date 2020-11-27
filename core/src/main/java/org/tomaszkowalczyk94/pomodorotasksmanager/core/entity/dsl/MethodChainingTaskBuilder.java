package org.tomaszkowalczyk94.pomodorotasksmanager.core.entity.dsl;

import org.tomaszkowalczyk94.pomodorotasksmanager.core.entity.DoneTask;
import org.tomaszkowalczyk94.pomodorotasksmanager.core.entity.ScheduledTaskData;
import org.tomaszkowalczyk94.pomodorotasksmanager.core.entity.Task;
import org.tomaszkowalczyk94.pomodorotasksmanager.core.entity.TaskDate;

import java.time.Duration;
import java.time.LocalDate;

public class MethodChainingTaskBuilder {
    public final Task task = new Task();

    private MethodChainingTaskBuilder(String taskName) {
        task.setName(taskName);
    }

    public static MethodChainingTaskBuilder withName(String taskName) {
        return new MethodChainingTaskBuilder(taskName);
    }

    public MethodChainingTaskBuilder withDate(LocalDate date) {
        return withDate(date, 0);
    }
    public MethodChainingTaskBuilder withDate(LocalDate date, long id) {
        TaskDate taskDate = new TaskDate();
        taskDate.setId(id);
        taskDate.setDate(date);
        task.getTaskDates().add(taskDate);

        return this;
    }

    /**
     * @param fullCronValue {@link #withScheduledTaskData(String fullCronValue, long id)}
     */
    public MethodChainingTaskBuilder withScheduledTaskData(String fullCronValue) {
        return withScheduledTaskData(fullCronValue, 0);
    }

    /**
     * @param fullCronValue string like:                                    <br>
     *  " *     *     * "                                                   <br>
     *    ^     ^     ^                                                     <br>
     *    |     |     |                                                     <br>
     *    |     |     +----- month. "*" for all months                      <br>
     *    |     |                                                           <br>
     *    |     +------- dayOfTheMonth.  "*" for all days                   <br>
     *    |                                                                 <br>
     *    +--------- dayOfTheWeek. "*" for all days                         <br>
     */
    public MethodChainingTaskBuilder withScheduledTaskData(String fullCronValue, long id) {
        String[] split = fullCronValue.split("%d");

        ScheduledTaskData scheduledTaskData = new ScheduledTaskData();

        scheduledTaskData.setDayOfTheWeek(split[0]);
        scheduledTaskData.setDayOfTheMonth(split[1]);
        scheduledTaskData.setMonth(split[2]);

        scheduledTaskData.setId(id);

        scheduledTaskData.setTask(task);
        task.getScheduledTaskInfos().add(scheduledTaskData);

        return this;
    }

    public MethodChainingTaskBuilder doneTaskAt(LocalDate date) {
        return doneTaskAt(date, 0);
    }

    public MethodChainingTaskBuilder doneTaskAt(LocalDate date, long id) {
        DoneTask doneTask = new DoneTask();

        doneTask.setDone(date);
        doneTask.setId(id);

        doneTask.setTask(task);
        task.getDoneTasks().add(doneTask);

        return this;
    }

    public MethodChainingTaskBuilder duration(Duration duration) {
        task.setDuration(duration);
        return this;
    }

    public MethodChainingTaskBuilder quantity(int quantity) {
        task.setQuantity(quantity);
        return this;
    }

    public Task end() {
        return task;
    }


}
