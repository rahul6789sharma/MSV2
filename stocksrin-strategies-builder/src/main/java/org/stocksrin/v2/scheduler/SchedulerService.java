package org.stocksrin.v2.scheduler;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stocksrin.common.utils.Scheduler;
import org.stocksrin.strategies.automation.InMemeoryStrategyDataUpdater;
import org.stocksrin.strategies.automation.InMemeoryStrategyLoader;
import org.stocksrin.v2.strategies.builder.task.IntraDayStrategyFileBuilderTask;
import org.stocksrin.v2.strategies.builder.task.PositionalStrategyFileBuilderTask;

@Service
public class SchedulerService {

	// Define the log object for this class
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired(required = true)
	private IntraDayStrategyFileBuilderTask intraDayStrategyFileBuilder;

	@Autowired(required = true)
	private PositionalStrategyFileBuilderTask positionalStrategyFileBuilderTask;

	@Autowired(required = true)
	private InMemeoryStrategyLoader inMemeoryStrategyLoader;

	@Autowired(required = true)
	private InMemeoryStrategyDataUpdater inMemeoryStrategyDataUpdater;

	@PostConstruct
	public void init() {

		log.info("######### SchedulerService Starting ############");
		try {
			Scheduler.scheduleTask(9, 25, intraDayStrategyFileBuilder);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// read files from dir and update startegy in memory
			Scheduler.scheduleTask(9, 27, inMemeoryStrategyLoader);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// update the price of in Memories startegies
			Scheduler.scheduleTask(9, 28, inMemeoryStrategyDataUpdater);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// update the price of in Memories startegies
			Scheduler.scheduleTask(15, 20, positionalStrategyFileBuilderTask);
		} catch (Exception e) {
			e.printStackTrace();
		}

		log.info("######### SchedulerService Started ############");
	}
}