package org.stocksrin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.stocksrin.collector.option.data.PriceUtils;
import org.stocksrin.email.SendEmail;
import org.stocksrin.v2.data.Data;

@SpringBootApplication
public class LiveDataCollectorApplication {
	public static boolean status = false;
	private final static Logger log = LoggerFactory.getLogger(LiveDataCollectorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LiveDataCollectorApplication.class, args);

	}

	public static void appWait() throws Exception {

		while (!LiveDataCollectorApplication.status) {
			try {
				PriceUtils.fetchData();
				log.info("wating .. " + Data.shortedExpiry.size());
				if (!Data.shortedExpiry.isEmpty()) {
					status = true;
				}
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * // @PostConstruct public void init() { try {
	 * DataLoaderOnStartUP.loadOIData();
	 * DataLoaderOnStartUP.loadParticipantOIDataOn(); Map<String, Map<String,
	 * Straddle>> data = (Map<String, Map<String, Straddle>>)
	 * FileUtils.fromJson(FileUtils.lasFilePath(AppConstant.STOCKSRIN_AI)); //
	 * System.out.println(data); NIftyAIData.add(data); //
	 * System.out.println(NIftyAIData.straddleData); } catch (Exception e) { throw
	 * new IllegalStateException("Liva-Data-Collector can not started"); } }
	 */

	// @PreDestroy
	public void destory() {
		System.out.println("*********** pre destory Construct ************");
		SendEmail.sentMail("!!CRITICAL!  shutdown ", " Liva-Data-Collector ", "Data-Downloader");
	}

}
