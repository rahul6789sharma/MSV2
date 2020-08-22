package org.stocksrin.v2.backtest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.stocksrin.common.model.trade.Strategy;
import org.stocksrin.common.utils.AppConstant;
import org.stocksrin.v2.common.model.option.OptionModel;
import org.stocksrin.v2.common.option.utils.IntraDayOptionChainJosnReader;

public class BackTestManual {

	//static String name = "Spread@INTRADAY~AUTO-Strategy_NIFTY.csv";
	static String name = "Ratio3@INTRADAY~AUTO-Strategy_NIFTY.csv";
	//static String name = "Strangle@INTRADAY~AUTO-Strategy_NIFTY.csv";

	public static void main(String[] args) throws Exception {

		Strategy s = loadStrategyFromDir();
		String tradedDate = s.getTradeDate().split(" ")[0];
		String expiry = s.getStrategyModels().get(0).getExpiry();
		test(expiry, tradedDate, s, s.getTradeDate());
	}

	private static Strategy loadStrategyFromDir() throws Exception {
		Map<String, Strategy> sta = org.stocksrin.common.utils.StrategyUtil
				.getStrategy2(AppConstant.STOCKSRIN_STRATEGY_DIR_BackTesting_NIFTY);
		Set<String> keys = sta.keySet();
		System.out.println(keys);
		Strategy s = sta.get(name);
		return s;
	}

	private static void test(String expiry, String date, Strategy strategy, String time) throws Exception {
		System.out.println("Expiry : " + expiry);
		System.out.println("Date : " + date);
		System.out.println("Time : " + time);
		// String time = "30-Jan-2020 10:18:20";
		strategy.setTradedTime(date);
		List<OptionModel> dat = null;
		try {
			dat = IntraDayOptionChainJosnReader.getNiftyOptionChain(expiry, date, true);
		} catch (Exception e) {

		}
		if (dat == null) {
			System.err.println("No Data Aailable : ");
			return;
		}

		// OptionModel startingData = null;
		List<OptionModel> futureModel = new ArrayList<>();

		for (OptionModel optionModel : dat) {
			Date tradedTime = getDateTime(time);
			Date timeStamp = getDateTime(optionModel.getTimestamp());
			int diff = tradedTime.compareTo(timeStamp);
			if (diff == -1) {
				// startingData = optionModel;
				futureModel.add(optionModel);
			}
		}

		for (OptionModel optionModel : futureModel) {

			StrategyPriceUpdater.updatePrice(strategy, optionModel);
			StringBuilder result = BackTestResult.print(strategy);
			System.out.println(result.toString());

		}

	}

	private static Date getDateTime(String dateString) throws ParseException {

		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		Date date = df.parse(dateString);
		return date;

	}
}
