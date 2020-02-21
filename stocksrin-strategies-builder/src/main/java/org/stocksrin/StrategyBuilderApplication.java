package org.stocksrin;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.stocksrin.email.SendEmail;

@SpringBootApplication
public class StrategyBuilderApplication {

	public static boolean status;

	private static final Logger log = LoggerFactory.getLogger(StrategyBuilderApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StrategyBuilderApplication.class, args);
		log.info("******StrategyBuilderApplication Started **********");
		status = true;
	}

	public static void appWait() {
		while (!StrategyBuilderApplication.status) {
			try {
				log.info("wating");
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@PreDestroy
	public void destory() {
		System.out.println("*********** pre destory Construct ************");
		SendEmail.sentMail("!!CRITICAL!  shutdown ", " Strategy-Builder ", "Strategy-Builder");
	}
}
