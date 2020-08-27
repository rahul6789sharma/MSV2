package org.stocksrin.telegram.util;

import java.util.Date;
import java.util.SortedMap;

public class MorningNotifcationUtils {

    private static String chatId = "-439206897";

    public static void morningNotifaction() {
        SortedMap<Date, Integer> maxPain = MaxPainData.getMonthlyMaxPain();
        String message = TelegramMessageBuilder.buid(maxPain, MaxPainData.monthlyExpiry);
        TelegramNotifactionUtils.sendNotifcation(message, chatId);
    }
}
