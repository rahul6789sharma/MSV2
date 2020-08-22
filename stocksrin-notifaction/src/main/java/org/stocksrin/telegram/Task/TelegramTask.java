package org.stocksrin.telegram.Task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stocksrin.common.utils.CommonUtils;
import org.stocksrin.common.utils.DateUtils;
import org.stocksrin.common.utils.NSEHolidayUtils;
import org.stocksrin.telegram.client.RestClient;
import org.stocksrin.telegram.util.MaxPainData;

import java.util.Date;
import java.util.TimerTask;

@Service
public class TelegramTask extends TimerTask {


    private static final Logger log = LoggerFactory.getLogger(TelegramTask.class);
    @Autowired
    RestClient restClient;
    private long timeInteval10min = 600000; // 10 min
    private long timeInteval5min = 30000; // 5 min

    @Override
    public void run() {
        log.info("******* TelegramTask Notification task Started **********");
        if (!DateUtils.isWeekEndDay() && !NSEHolidayUtils.isHoliday()) {
            new Thread(() -> task()).start();
        }
    }

    private void task() {
        while (CommonUtils.getEveningTime()) {
            try {

                log.info("TelegramTask Running.... ");

                Integer maxpain = restClient.getMonthlyMaxPain("Nifty");
                MaxPainData.put(new Date(), maxpain);

                Thread.sleep(timeInteval5min);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("TelegramTask Not Running ##### ");
    }
}
