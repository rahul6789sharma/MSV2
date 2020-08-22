package org.stocksrin.telegram.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/strategiesBuilder")
public class Controller {

    // Define the log object for this class
    // private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${logging.file}")
    private String name;

    // http://13.234.37.254:8088/strategiesBuilder/strategiesIntraDay
    @RequestMapping("/hello")
    public String sayHello() {

        return null;
    }


}
