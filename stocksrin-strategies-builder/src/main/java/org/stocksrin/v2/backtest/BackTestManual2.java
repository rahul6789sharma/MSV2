package org.stocksrin.v2.backtest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.stocksrin.common.model.trade.Strategy;
import org.stocksrin.v2.common.model.option.OptionModel;
import org.stocksrin.v2.common.option.utils.IntraDayOptionChainJosnReader;
import org.stocksrin.v2.strategies.builder.NiftyStrategies;
import org.stocksrin.v2.strategies.builder.StrategyType;

public class BackTestManual2 {

	public static void main(String[] args) throws Exception {

		String expiry = "09-Apr-2020";
		String date = "08-Apr-2020";
		String time = "10:00:00";
		test(expiry, date, time);
	}

	private static void test(String expiry, String date, String time) throws Exception {
		System.out.println("Expiry : " + expiry);
		System.out.println("Date   : " + date);
		System.out.println("Time   : " + time);

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

		OptionModel startingData = null;
		for (OptionModel optionModel : dat) {
			Date tradedTime = getDateTime(date + " " + time);

			// System.out.println(tradedTime);
			Date timeStamp = getDateTime(optionModel.getTimestamp());

			// System.out.println("Time Stamp: " + timeStamp);
			int diff = tradedTime.compareTo(timeStamp);
			// System.out.println(diff);
			if (diff == -1) {
				// startingData = optionModel;
				futureModel.add(optionModel);
			} else {
				startingData = optionModel;
			}
		}

		Strategy strategy = NiftyStrategies.putRatio(startingData, expiry, 3, StrategyType.INTRADAY, 50);
		strategy.setTradedTime(startingData.getTimestamp());
		strategy.setTradeDate(date);

		for (OptionModel optionModel : futureModel) {

			StrategyPriceUpdater.updatePrice(strategy, optionModel);
			print(strategy);

		}

	}

	public static void print(Strategy strategy) throws Exception {

		StringBuilder result = BackTestResult.print(strategy);
		System.out.println(result.toString());

	}

	public static Date getDateTime(String dateString) throws ParseException {

		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		Date date = df.parse(dateString);
		return date;

	}
}
