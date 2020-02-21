package org.stocksrin.v2.strategies.builder.task;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stocksrin.collector.option.data.InMemoryStrategyies;
import org.stocksrin.common.utils.AppConstant;
import org.stocksrin.common.utils.DateUtils;
import org.stocksrin.common.utils.FileUtils;
import org.stocksrin.common.utils.NSEHolidayUtils;
import org.stocksrin.email.SendEmail;
import org.stocksrin.strategies.automation.LockObject;
import org.stocksrin.v2.strategies.builder.IntraDayBNFStrategies;
import org.stocksrin.v2.strategies.builder.IntraDayNiftyStrategies;

@Service
public class PositionalStrategyFileBuilderTask extends TimerTask {

	private final Logger log = LoggerFactory.getLogger(IntraDayStrategyFileBuilderTask.class);

	@Autowired(required = true)
	private IntraDayNiftyStrategies niftyStrategies;

	@Autowired(required = true)
	private IntraDayBNFStrategies intraDayBNFStrategies;

	@Override
	public void run() {
		// StrategyBuilderApplication.appWait();
		log.info("******PositionalStrategyFileBuilderTask starting *****");
		try {
			LockObject.getWriteLock();
			if (!DateUtils.isWeekEndDay() && !NSEHolidayUtils.isHoliday()) {
				// intra day new dir
				deleteFile(AppConstant.STOCKSRIN_STRATEGY_DIR_DAILY_NIFTY_Strategy);

				List<String> files = new ArrayList<>();

				try {
					files.add(niftyStrategies.ratio("Ratio3Dis100@AUTO-Strategy_NIFTY", AppConstant.STOCKSRIN_STRATEGY_DIR_DAILY_NIFTY_Strategy, 100));
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					files.add(niftyStrategies.ratio("Ratio3Dis50@AUTO-Strategy_NIFTY", AppConstant.STOCKSRIN_STRATEGY_DIR_DAILY_NIFTY_Strategy, 50));
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					files.add(niftyStrategies.ratio("Ratio3ATM@AUTO-Strategy_NIFTY", AppConstant.STOCKSRIN_STRATEGY_DIR_DAILY_NIFTY_Strategy, 0));
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					files.add(niftyStrategies.straddle3_calender("CalenderStraddle3@AUTO-Strategy_NIFTY", AppConstant.STOCKSRIN_STRATEGY_DIR_DAILY_NIFTY_Strategy));
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					files.add(niftyStrategies.straddle2_calender("CalenderStraddle2@INTRADAY~AUTO-Strategy_NIFTY", AppConstant.STOCKSRIN_STRATEGY_DIR_DAILY_NIFTY_Strategy));
				} catch (Exception e) {
					e.printStackTrace();
				}

				log.info("******PositionalStrategyFileBuilderTask complated *****");
			}
		} catch (Exception e) {

			e.printStackTrace();
			SendEmail.sentMail("Auto Build Strategy Failed", e.getMessage(), "Strategies-BUilder");
		} finally {
			LockObject.realseWriteLock();
		}

		log.info("****** PositionalStrategyFileBuilderTask starting Completed *****");
	}

	private void deleteFile(String path) {
		try {
			InMemoryStrategyies.clear();
			List<String> lst = FileUtils.listFilesForFolder(path);
			for (String string : lst) {
				log.info("check : " + path + string);
				boolean isToday = FileUtils.isTodayFileExist(path + string);
				if (!isToday) {
					log.info("Deleting previous day strategy " + string);
					String filePath = path + string;
					FileUtils.delete(filePath);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}