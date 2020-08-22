package org.stocksrin.telegram.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stocksrin.common.utils.Scheduler;
import org.stocksrin.telegram.Task.TelegramTask;

import javax.annotation.PostConstruct;


@Service
public class SchedulerService {

    // Define the log object for this class
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired(required = true)
    private TelegramTask telegramTask;


    @PostConstruct
    public void init() {

        log.info("######### SchedulerService Starting ############");
        try {
            Scheduler.scheduleTask(9, 25, telegramTask);
        } catch (Exception e) {
            e.printStackTrace();
        }


        log.info("######### SchedulerService Started ############");
    }
}