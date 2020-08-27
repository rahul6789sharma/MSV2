package org.stocksrin.telegram.util;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class MaxPainData {

    private static final int MAX_ENTRIES = 5;
    private static final Logger log = LoggerFactory.getLogger(MaxPainData.class);
    // store last 5 value of maxpain
    // dateTime and its maxPain
    //public static LinkedHashMap<String, Integer> maxPain = new LinkedHashMap<>();

    //private  static String chatId = "-439206897";
    private  static String chatId = "@stocksrin";

    public static String monthlyExpiry = null;
    public static SortedMap<Date, Integer> monthlyMaxPain = new TreeMap<>(new DateComparator());
    public static ObservableMap<Date, Integer> monthlyObservableMap = FXCollections.observableMap(monthlyMaxPain);

    public static String weeklyExpiry = null;
    public static SortedMap<Date, Integer> weeklyMaxPain = new TreeMap<>(new DateComparator());
    public static ObservableMap<Date, Integer> weeklyObservableMap = FXCollections.observableMap(weeklyMaxPain);

    static {
        weeklyObservableMap.addListener(new MapChangeListener<Date, Integer>() {
            @Override
            public void onChanged(Change<? extends Date, ? extends Integer> change) {

                if (change.wasAdded()) {
                    log.info("********** Weekly value changed ******** Added");
                    String message = TelegramMessageBuilder.buid(weeklyMaxPain, weeklyExpiry);
                    TelegramNotifactionUtils.sendNotifcation(message + " : Weekly", chatId);

                } else if (change.wasRemoved()) {
                    log.info("********** value changed ******** Removed");
                }
            }
        });
    }

    static {
        monthlyObservableMap.addListener(new MapChangeListener<Date, Integer>() {
            @Override
            public void onChanged(Change<? extends Date, ? extends Integer> change) {

                if (change.wasAdded()) {
                    log.info("********** Monthly value changed ******** Added");
                    String message = TelegramMessageBuilder.buid(monthlyMaxPain, monthlyExpiry);
                    TelegramNotifactionUtils.sendNotifcation(message +" : Monthy", chatId);

                } else if (change.wasRemoved()) {
                    log.info("********** value changed ******** Removed");
                }
            }
        });
    }

    public static void put(Date timeStamp, Integer maxPainValue, SortedMap<Date, Integer> maxPain, ObservableMap<Date, Integer> observableMap) {
        //System.out.println(maxPain);

        boolean status = putIfLAstMaxPainChanged(timeStamp, maxPainValue , maxPain, observableMap);

        if (maxPain.size() >= MAX_ENTRIES) {
            System.out.println(" Max Entries Reached ");
            // need to remove first entry
            if (status) {
                observableMap.remove(maxPain.firstKey());
            }
        }

        // put only When max Pain changed with last value
        //boolean status = putIfLAstMaxPainChanged(timeStamp, maxPainValue);

    }

    private static boolean putIfLAstMaxPainChanged(Date timeStamp, Integer maxPainValue, SortedMap<Date, Integer> maxPain, ObservableMap<Date, Integer> observableMap) {
        boolean status = false;
        if (maxPain.isEmpty()) {
            observableMap.put(timeStamp, maxPainValue);
            return true;
        }
        Integer lastMaxPain = maxPain.get(maxPain.lastKey());

        if (!maxPainValue.equals(lastMaxPain)) {
            observableMap.put(timeStamp, maxPainValue);
            status = true;
            //  Changed Notification need to send Telegram
        }
        return status;
    }

    public static SortedMap<Date, Integer> getMonthlyMaxPain() {
        return monthlyMaxPain;
    }

    public static void main(String[] args) throws Exception {

    }
}

