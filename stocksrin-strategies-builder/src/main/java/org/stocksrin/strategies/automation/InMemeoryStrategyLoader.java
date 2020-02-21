package org.stocksrin.strategies.automation;

import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.stocksrin.collector.option.data.InMemoryStrategyies;
import org.stocksrin.common.utils.AppConstant;
import org.stocksrin.common.utils.CommonUtils;
import org.stocksrin.common.utils.DateUtils;
import org.stocksrin.common.utils.NSEHolidayUtils;

@Service
public class InMemeoryStrategyLoader extends TimerTask {

	private static long timeInteval = 120000;
	private static final Logger log = LoggerFactory.getLogger(InMemeoryStrategyLoader.class);
	//static long timeInteval = 50000;

	@Override
	public void run() {

		if (!DateUtils.isWeekEndDay() && !NSEHolidayUtils.isHoliday()) {
			InMemoryStrategyies.clear();
			log.info("strategy In memory builder.. startting");
			while (CommonUtils.getEveningTimeForStrategy()) {
				try {
					log.info("strategy In memory builder.. Starting");
					// intradaya
					// 
					StrategyFileReader.startManualStrategies(AppConstant.STOCKSRIN_STRATEGY_DIR_IntraDay_NIFTY);
					StrategyFileReader.startManualStrategies(AppConstant.STOCKSRIN_STRATEGY_DIR_IntraDay_BNF);
					
					StrategyFileReader.startManualStrategies(AppConstant.STOCKSRIN_STRATEGY_DIR_DAILY_NIFTY_Strategy);
					

					/*
					 * // daily StrategyFileReader.startManualStrategies(AppConstant.
					 * STOCKSRIN_STRATEGY_DIR_DAILY_BNF_Strategy);
					 * StrategyFileReader.startManualStrategies(AppConstant.
					 * STOCKSRIN_STRATEGY_DIR_DAILY_NIFTY_Strategy_AutoMated);
					 * StrategyFileReader.startManualStrategies(AppConstant.
					 * STOCKSRIN_STRATEGY_DIR_DAILY_BNF_Strategy_AutoMated);
					 * StrategyFileReader.startManualStrategies(AppConstant.
					 * STOCKSRIN_STRATEGY_DIR_DAILY_NIFTY_Strategy);
					 * 
					 * StrategyFileReader.startManualStrategies(AppConstant.
					 * STOCKSRIN_STRATEGY_DIR_DAILY_USDINR_Strategy_AutoMated);
					 */
					log.info("strategy In memory builder.. Completed");
					Thread.sleep(timeInteval);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}

}
