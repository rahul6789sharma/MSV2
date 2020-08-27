package org.stocksrin.telegram.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.stocksrin.telegram.util.MaxPainData;

import java.util.Date;
import java.util.SortedMap;

@RestController
@RequestMapping("/maxpain")
public class Controller {

    // Define the log object for this class
    // private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${logging.file}")
    private String name;

    // http://localhost:8081/maxpain/nifty
    @RequestMapping("/nifty")
    public SortedMap<Date, Integer> maxpain() {
        return MaxPainData.getMonthlyMaxPain();
    }


}
