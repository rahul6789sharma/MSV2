package org.stocksrin.v2.backtest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.stocksrin.common.model.trade.Strategy;
import org.stocksrin.common.utils.AppConstant;
import org.stocksrin.common.utils.FileUtils;
import org.stocksrin.strategies.utils.result.StrategyPrinterConsole;
import org.stocksrin.v2.common.model.option.OptionModel;
import org.stocksrin.v2.common.option.utils.IntraDayOptionChainJosnReader;
import org.stocksrin.v2.strategies.builder.NiftyStrategies;
import org.stocksrin.v2.strategies.builder.StrategyType;

public class AutomatedBackTest {

	public static void main(String[] args) throws Exception {

		test();
	}

	private static void test() throws Exception {

		List<String> expiries = FileUtils.listdir(AppConstant.STOCKSRIN_OPTION_DATA_DIR_NIFTY_INTRDAY2_10MIN);
		for (String string : expiries) {
			//test(string, string);
			System.out.println("*************************");
			//Date daysAgo = new DateTime(getDate(string)).minusDays(1).toDate();
			test(string, getDayFromDate(new DateTime(getDate(string)).minusDays(1).toDate()));
			test(string, getDayFromDate(new DateTime(getDate(string)).minusDays(2).toDate()));
			test(string, getDayFromDate(new DateTime(getDate(string)).minusDays(3).toDate()));
			test(string, getDayFromDate(new DateTime(getDate(string)).minusDays(4).toDate()));
			Thread.sleep(1000);
		}
	}

	private static void test(String expiry, String date) throws Exception {

		System.out.println("Expiry " + expiry);
		List<OptionModel> dat = null;
		try {
			dat = IntraDayOptionChainJosnReader.getNiftyOptionChain(expiry, date, true);
		} catch (Exception e) {

		}
		if (dat == null) {
			return;
		}

		OptionModel startingData = null;
		for (OptionModel optionModel : dat) {
			startingData = optionModel;
			break;
		}

		//Strategy strategy = NiftyStrategies.callRatio(startingData, expiry, StrategyType.INTRADAY, 0);
		Strategy strategy = NiftyStrategies.strangle(startingData, expiry, StrategyType.INTRADAY, 500);
		strategy.setTradeDate(expiry);
		System.out.println(strategy);
		// System.out.println(strategy);
		// String dir = AppConstant.STOCKSRIN_STRATEGY_DIR_BackTesting_NIFTY;
		// CommonUtils.createStrategyFile(strategy, dir, "test.csv");

		for (OptionModel optionModel : dat) {
			StrategyPriceUpdater.updatePrice(strategy, optionModel);

		}
		writeStrategyFile(strategy);
	}

	public String ratio(String fileName, String dir, int distance) {
		return null;
	}

	public static void writeStrategyFile(Strategy strategy) throws Exception {

		StringBuilder result = StrategyPrinterConsole.print(strategy);
		System.out.println(result.toString());

	}

	public static Date getDate(String dateString) throws ParseException {

		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = df.parse(dateString);
		return date;
	}
	
	public static String getDayFromDate(Date date) throws Exception {
		SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy");
		return format1.format(date);
	}

	public static void getDateTime(String dateString) throws ParseException {

		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		Date date = df.parse(dateString);
		System.out.println(date);
	}
}
