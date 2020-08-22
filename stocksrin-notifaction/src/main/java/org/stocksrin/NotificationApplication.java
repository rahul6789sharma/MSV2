package org.stocksrin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificationApplication {

    private static final Logger log = LoggerFactory.getLogger(NotificationApplication.class);
    public static boolean status;

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
        log.info("******StrategyBuilderApplication Started **********");
        status = true;
    }


}
