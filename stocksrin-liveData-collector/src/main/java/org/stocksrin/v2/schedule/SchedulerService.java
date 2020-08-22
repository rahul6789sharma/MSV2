package org.stocksrin.v2.schedule;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stocksrin.common.utils.Scheduler;
import org.stocksrin.v2.arbitrage.ArbitrageTask2;
import org.stocksrin.v2.download.OptionChainDailyDownloader;
import org.stocksrin.v2.download.OptionChainDownloader2;
import org.stocksrin.v2.download.ParticapentFnoDataDownloaderTask;
import org.stocksrin.v2.price.retrival.service.OptionChainPriceRetrivalTask;
import org.stocksrin.v2.ss.SSTask;

@Service
public class SchedulerService {

	@Autowired
	private OptionChainDailyDownloader optionChainDailyDownloader;

	@Autowired
	private OptionChainDownloader2 optionChainDownloader;

	@Autowired
	private ArbitrageTask2 arbitrageTask;

	@Autowired
	private SSTask ssTask;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@PostConstruct
	public void init() {
		log.info("################# SchedulerService started #################");
		try {
			// new boz we nned new instance daily
			Scheduler.scheduleTask(9, 17, new OptionChainPriceRetrivalTask(false));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// new boz we nned new instance daily
			Scheduler.scheduleTask(9, 25, optionChainDownloader);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// new boz we nned new instance daily
			// Scheduler.scheduleTask(9, 25, arbitrageTask);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// new boz we nned new instance daily
			Scheduler.scheduleTask(9, 25, ssTask);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// new boz we nned new instance daily
			Scheduler.scheduleTask(20, 00, new ParticapentFnoDataDownloaderTask());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// for evening data update 5 pm
			// fetch option chain in evening
			Scheduler.scheduleTask(18, 0, new OptionChainPriceRetrivalTask(true));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// new boz we nned new instance daily
			Scheduler.scheduleTask(18, 15, optionChainDailyDownloader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}