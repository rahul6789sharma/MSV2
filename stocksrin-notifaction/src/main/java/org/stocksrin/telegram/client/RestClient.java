package org.stocksrin.telegram.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.stocksrin.common.utils.ComparatorBasedOnDate;
import org.stocksrin.v2.common.model.option.MaxPainResponse;

import java.util.*;

@Service
public class RestClient {

    @Autowired
    private Environment env;
    RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        System.out.println(getRandom());
    }
    public MaxPainResponse getMonthlyMaxPain(String symbole) {
        String uri = this.env.getProperty("microservice.liveData.url");
        uri = String.valueOf(uri) + "/niftydata/monthlyMaxPain";
        MaxPainResponse result = (MaxPainResponse) restTemplate.getForObject(uri, MaxPainResponse.class, new Object[0]);
        return result;
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

    //public Integer getMonthlyMaxPain(String symbole) {
       // return getRandom();
    //}

}

