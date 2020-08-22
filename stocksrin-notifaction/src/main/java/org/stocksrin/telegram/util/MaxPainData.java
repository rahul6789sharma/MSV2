package org.stocksrin.telegram.util;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

import java.util.Comparator;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

public class MaxPainData {

    private static final int MAX_ENTRIES = 5;

    // store last 5 value of maxpain
    // dateTime and its maxPain
    //public static LinkedHashMap<String, Integer> maxPain = new LinkedHashMap<>();
    private static SortedMap<Date, Integer> maxPain = new TreeMap<>(new DateComparator());

    private static ObservableMap<Date, Integer> observableMap = FXCollections.observableMap(maxPain);

    static {
        observableMap.addListener(new MapChangeListener<Date, Integer>() {
            @Override
            public void onChanged(Change<? extends Date, ? extends Integer> change) {

                if (change.wasAdded()) {
                    System.out.println("********** value changed ******** Added");
                    String message = TelegramMessageBuilder.buid(maxPain, "20Aug");
                    TelegramNotifactionUtils.sendNotifcation(message);

                } else if (change.wasRemoved()) {
                    System.out.println("********** value changed ******** Removed");
                }
            }
        });
    }

    public static void put(Date timeStamp, Integer maxPainValue) {
        System.out.println(maxPain);

        boolean status = putIfLAstMaxPainChanged(timeStamp, maxPainValue);

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

    private static boolean putIfLAstMaxPainChanged(Date timeStamp, Integer maxPainValue) {
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

    public static void main(String[] args) throws Exception {

    }
}

class DateComparator implements Comparator<Date> {
    @Override
    public int compare(Date o1, Date o2) {
        return o1.compareTo(o2);
    }
}