package org.stocksrin.v2.schedule;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stocksrin.common.utils.Scheduler;
import org.stocksrin.v2.arbitrage.ArbitrageTask2;
import org.stocksrin.v2.price.retrival.service.OptionChainDownloader2;
import org.stocksrin.v2.price.retrival.service.OptionChainPriceRetrivalService;

@Service
public class SchedulerService {

	@Autowired
	private OptionChainPriceRetrivalService optionChainPriceRetrivalService;

	@Autowired
	private OptionChainDownloader2 optionChainDownloader;

	@Autowired
	private ArbitrageTask2 arbitrageTask;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@PostConstruct
	public void init() {
		log.info("################# SchedulerService started #################");
		try {
			// new boz we nned new instance daily
			Scheduler.scheduleTask(9, 17, optionChainPriceRetrivalService);
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
			//Scheduler.scheduleTask(9, 25, arbitrageTask);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}