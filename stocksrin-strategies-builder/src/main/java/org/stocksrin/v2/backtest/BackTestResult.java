package org.stocksrin.v2.backtest;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.stocksrin.common.model.option.OptionType;
import org.stocksrin.common.model.trade.Strategy;
import org.stocksrin.common.model.trade.StrategyModel;
import org.stocksrin.common.utils.DateUtils;

public class BackTestResult {

	private static DecimalFormat df = new DecimalFormat("#00.00");
	private static DecimalFormat signFormate = new DecimalFormat("+#,##000;-#");
	private static String line = "----------------------------------------------------------------------------------------------";

	public static StringBuilder print(Strategy strategy) throws Exception {
		String tradedDay = null;
		String todayDate = null;
		String todayDay = null;
		String expiryDay = null;
		try {
			if (strategy.getTradeDate() != null) {
				expiryDay = DateUtils.getDayFromDate(strategy.getStrategyModels().get(0).getExpiry(), "dd-MMM-yyyy");
				tradedDay = DateUtils.getDayFromDate(strategy.getTradedTime(), "dd-MMM-yyyy");
				Date dataupdatedDate = DateUtils.stringToDate(strategy.getDataUpdatedAt().split(" ")[0], "dd-MMM-yyyy");
				todayDate = DateUtils.dateToString(dataupdatedDate, "dd-MMM-yyyy");
				todayDay = DateUtils.getDayFromDate(todayDate, "dd-MMM-yyyy");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		List<StrategyModel> strategyModels = strategy.getStrategyModels();

		String diff = df.format(strategy.getUnderlying_ltp() - strategy.getTradeSpotPrice());

		StringBuilder result = new StringBuilder();
		result.append("\n");
		result.append(line);
		result.append("\n");
		result.append("Trade Date: " + strategy.getTradedTime() + " (" + tradedDay + "), Traded Price:  "
				+ strategy.getTradeSpotPrice() + "  Expiry : " + expiryDay+ "\n");
		result.append("Status  At: " + strategy.getDataUpdatedAt() + " (" + todayDay + "), Current Price: "
				+ strategy.getUnderlying_ltp() + " [" + diff + "] Diff from Trade");
		result.append("\n");
		result.append(line);
		result.append("\n");
		result.append("Type      Expiry      Strike   Qty   AvgPrice   ltp    change    P&L    tradeIV" + "\n");
		result.append(line + "\n");

		double totalPremiumRecived = 0;
		double currentPremiumRecived = 0;
		double totalPL = 0;
		for (StrategyModel strategyModel : strategyModels) {

			String type = "";
			OptionType optionType = strategyModel.getType();
			if (optionType.equals(OptionType.CALL)) {
				type = "CALL";
			} else if (optionType.equals(OptionType.PUT)) {
				type = "PUT ";
			}

			double change = strategyModel.getLtp() - strategyModel.getAvgPrice();
			double pl = (strategyModel.getLtp() - strategyModel.getAvgPrice()) * strategyModel.getQuantity();
			totalPL = totalPL + pl;
			totalPremiumRecived = totalPremiumRecived + strategyModel.getAvgPrice();
			currentPremiumRecived = currentPremiumRecived + strategyModel.getLtp();

			result.append(type + "   " + addZero(strategyModel.getExpiry()) + "   " + ((int) strategyModel.getStrike())
					+ "   " + signFormate.format(strategyModel.getQuantity()) + "   "
					+ foramteTradedPrice(df.format(strategyModel.getAvgPrice())) + "   "
					+ foramteTradedPrice(df.format(strategyModel.getLtp())) + "    " + signFormate.format(change)
					+ "    " + foramtePL(pl) + "     " + strategyModel.getTraded_IV());
			result.append("\n");

		}
		result.append(line + "\n");
		result.append("Points :                              [" + (int) totalPremiumRecived + "]      ["
				+ (int) currentPremiumRecived + "]    [" + (int) (currentPremiumRecived - totalPremiumRecived) + "]");
		result.append("\n" + line + "\n");
		result.append("Total PL : [" + df.format(totalPL) + "],    At Spot: [" + strategy.getUnderlying_ltp()
				+ "] moved : [" + (diff) + "] - " + strategy.getDataUpdatedAt() + "\n");
		result.append("Min PL " + " :  [" + df.format(strategy.getTotalPLMin()) + "],    At Spot: ["
				+ strategy.getTotalPLMinSpot() + "] moved : ["
				+ df.format(strategy.getTotalPLMinSpot() - strategy.getTradeSpotPrice()) + "] - "
				+ strategy.getTotalPLMinTime() + "\n");
		result.append("Max PL " + " :  [" + df.format(strategy.getTotalPLMax()) + "],    At Spot: ["
				+ strategy.getTotalPLMaxSpot() + "] moved : ["
				+ df.format(strategy.getTotalPLMaxSpot() - strategy.getTradeSpotPrice()) + "] - "
				+ strategy.getTotalPLMaxTime() + "\n");
		result.append(line + "\n");

		return result;

	}

	private static String foramtePL(Double price) {
		DecimalFormat signFormate = new DecimalFormat("+#,##0000;-#");
		return signFormate.format(price);
	}

	private static String foramteTradedPrice(String price) {
		if (price.length() == 6) {
			return price;
		} else {
			return " " + price;
		}
	}

	// add blank space in date is single digit
	private static String addZero(String date) {
		if (date.length() == 9) {
			return date;
		} else {
			return " " + date;
		}
	}
}
