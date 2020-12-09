package org.tomaszkowalczyk94.pomodorotasksmanager.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.function.Consumer;

/**
 * Format like in cron tasks. https://en.wikipedia.org/wiki/Cron
 * for example, dayOfTheMonth can have value "*", or number from 0 to 31, or group of numbers,
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(buildMethodName = "simpleBuild")
@Entity
public class ScheduledTaskInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Task task;

    String dayOfTheWeek;
    String dayOfTheMonth;
    String month;

    public static class ScheduledTaskInfoBuilder implements LambdaBuilder<ScheduledTaskInfo> {

        /**
         * @param fullCronValue string like:                                    <br>
         *                      " *     *     * "                                                   <br>
         *                      ^     ^     ^                                                     <br>
         *                      |     |     |                                                     <br>
         *                      |     |     +----- month. "*" for all months                      <br>
         *                      |     |                                                           <br>
         *                      |     +------- dayOfTheMonth.  "*" for all days                   <br>
         *                      |                                                                 <br>
         *                      +--------- dayOfTheWeek. "*" for all days                         <br>
         */
        public ScheduledTaskInfoBuilder withCron(String fullCronValue) {
            String[] split = fullCronValue.split("\\s");

            if(split.length != 3) {
                throw new IllegalArgumentException(String.format("cron value invalid: %s", fullCronValue));
            }

            dayOfTheWeek(split[0]);
            dayOfTheMonth(split[1]);
            month(split[2]);

            return this;
        }

        public ScheduledTaskInfo build(Consumer<ScheduledTaskInfoBuilder> consumer) {
            consumer.accept(this);
            return build();
        }

        public List<Consumer<ScheduledTaskInfo>> getAfterBuild() {
            return List.of(this::linkScheduledTaskInfoWithTask);
        }

        private void linkScheduledTaskInfoWithTask(ScheduledTaskInfo scheduledTaskInfo) {
            scheduledTaskInfo
                    .getTask()
                    .getScheduledTaskInfos()
                    .add(scheduledTaskInfo);
        }
    }

    public static ScheduledTaskInfo build(Consumer<ScheduledTaskInfoBuilder> consumer) {
        return builder().build(consumer);
    }
}
