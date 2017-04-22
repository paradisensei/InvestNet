package org.singularnost.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

/**
 * @author aleksandrpliskin on 22.04.17.
 */
@Component
public class EventChecker {

    private class EventCheckTask implements Runnable {

        @Override
        public void run() {

        }
    }

    @Autowired
    private TaskExecutor taskExecutor;

    public void checkEvents() {
        taskExecutor.execute(new EventCheckTask());
    }

}
