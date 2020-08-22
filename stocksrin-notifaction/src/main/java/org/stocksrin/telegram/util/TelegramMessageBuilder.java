package org.stocksrin.telegram.util;

import org.stocksrin.common.utils.DateUtils;

import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

public class TelegramMessageBuilder {

    private static final String seprator = "-------------------------------------";
    private static final String NewLine = "%0A";
    //private static final String NewLine = "\n";
    //


    public static String buid(SortedMap<Date, Integer> map, String expiry) {

        StringBuilder response = new StringBuilder();
        //System.out.println(maxPain);
        map.forEach((i, j) -> {
            try {
                response.append(DateUtils.dateToString(i, "ddMMM HH:mm") + " - [" + j + "]");
                response.append(NewLine);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return maxPainMessages(response.toString(), expiry);
    }

    private static String maxPainMessages(String message, String expiry) {
        StringBuilder response = new StringBuilder();

        //response.append(seprator);
        response.append(NewLine);
        response.append("Alert: MaxPain Changed, Expiry: " + expiry);
        response.append(NewLine);
        response.append(seprator);
        response.append(NewLine);
        response.append(NewLine);
        response.append(message);
        response.append(NewLine);
        //response.append("Updated Time : ")
        response.append(seprator);
        System.out.println(response);
        return response.toString();


    }

    public static void main(String[] args) {
        SortedMap<Date, Integer> maxPain = new TreeMap<>(new DateComparator());
        maxPain.put(new Date(), 12000);
        maxPain.put(new Date(), 12200);
        buid(maxPain, "20 Aug");
    }
}

