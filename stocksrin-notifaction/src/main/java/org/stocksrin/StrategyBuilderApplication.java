package org.stocksrin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StrategyBuilderApplication {

    private static final Logger log = LoggerFactory.getLogger(StrategyBuilderApplication.class);
    public static boolean status;

    public static void main(String[] args) {
        SpringApplication.run(StrategyBuilderApplication.class, args);
        log.info("******StrategyBuilderApplication Started **********");
        status = true;
    }


}
