package org.stocksrin.telegram.client;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class RestClient {

    public static void main(String[] args) {
        System.out.println(getRandom());
    }

    public static int getRandom() {
        List<Integer> givenList = Arrays.asList(12000, 12100, 12200);
        Random rand = new Random();
        int randomElement = givenList.get(rand.nextInt(givenList.size()));
        return randomElement;
    }

    public Integer getWeeklyMaxPain(String symbole) {
        return getRandom();
    }

    public Integer getMonthlyMaxPain(String symbole) {
        return getRandom();
    }

}

