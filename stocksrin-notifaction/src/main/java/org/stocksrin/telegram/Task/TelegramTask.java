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
import org.stocksrin.telegram.util.MorningNotifcationUtils;
import org.stocksrin.v2.common.model.option.MaxPainResponse;

import java.util.Date;
import java.util.TimerTask;

@Service
public class TelegramTask extends TimerTask {


    private static final Logger log = LoggerFactory.getLogger(TelegramTask.class);

    private String formate = "dd-MMM-yyyy HH:mm:ss";

    @Autowired
    private RestClient restClient;

    private long timeInteval10min = 600000; // 10 min
    private long timeInteval5min = 600000; // 5 min


    @Override
    public void run() {
        log.info("******* TelegramTask Notification task Started **********");
        if (!DateUtils.isWeekEndDay() && !NSEHolidayUtils.isHoliday()) {
            // daily send notifcation to admin
            MorningNotifcationUtils.morningNotifaction();
            new Thread(() -> task()).start();
        }
    }


    private void task() {
        while (CommonUtils.getEveningTime()) {
            niftyMothlyTask();
            niftyWeeklyTask();
        }
        log.info("###### TelegramTask Not Running ##### ");
    }

    private void niftyMothlyTask() {
        try {

            log.info("Monthly Max TelegramTask Running.... ");
            MaxPainResponse maxpain = restClient.getMonthlyMaxPain("Nifty");
            Date date = DateUtils.stringToDate(maxpain.getLastupdateTimeStamp(), formate);
            MaxPainData.monthlyExpiry = maxpain.getExpiry();
            MaxPainData.put(date, maxpain.getMaxPain(), MaxPainData.monthlyMaxPain, MaxPainData.monthlyObservableMap);
            Thread.sleep(timeInteval5min);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private void niftyWeeklyTask() {
        try {

            log.info("Monthly Max TelegramTask Running.... ");
            MaxPainResponse maxpain = restClient.getMonthlyMaxPain("Nifty");
            Date date = DateUtils.stringToDate(maxpain.getLastupdateTimeStamp(), formate);
            MaxPainData.weeklyExpiry = maxpain.getExpiry();
            MaxPainData.put(date, maxpain.getMaxPain(), MaxPainData.weeklyMaxPain, MaxPainData.weeklyObservableMap);
            Thread.sleep(timeInteval5min);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        String time = "21-Aug-2020 15:30:00";
        String formate = "dd-MMM-yyyy HH:mm:ss";
        Date d = DateUtils.stringToDate(time, formate);
        System.out.println(d);
    }
}