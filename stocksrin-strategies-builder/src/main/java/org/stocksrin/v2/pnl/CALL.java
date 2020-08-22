package org.stocksrin.v2.pnl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.stocksrin.common.model.option.OptionType;
import org.stocksrin.common.model.option.TradeType;

public class CALL {

	
	public static void main(String[] args) {

		List<TradeModle> trades = new ArrayList<>();
		TradeModle callTrade = new TradeModle(OptionType.CALL, TradeType.SHORT, 9500, 172.0, 75);
		TradeModle shortTrade = new TradeModle(OptionType.PUT, TradeType.SHORT, 8500, 200.1, 75);
		trades.add(callTrade);
		trades.add(shortTrade);

		Map<Double, Double> result = netPNL(trades, 9000.0, 50.0);

		Set<Double> kesy = result.keySet();
		for (Double integer : kesy) {
			System.out.println(integer + "->" + result.get(integer));
		}
	}

	public static Map<Double, Double> netPNL(List<TradeModle> trades, double midStrike, double strikediff) {
		List<Double> strikes = getStrikes(midStrike, strikediff);
		return netPNL(trades, strikes);

	}

	private static Map<Double, Double> netPNL(List<TradeModle> trades, List<Double> strikes) {
		Map<Double, Double> netPnL = new LinkedHashMap<>();
		for (TradeModle tradeModle : trades) {
			Map<Double, Double> result = callPnL(tradeModle, strikes);
			Set<Double> keys = result.keySet();
			for (Double integer : keys) {
				double pnl = result.get(integer) * tradeModle.getQnty();
				if (netPnL.get(integer) == null) {
					netPnL.put(integer, pnl);
				} else {
					double value = netPnL.get(integer);
					netPnL.put(integer, pnl + value);
				}
			}
		}
		return netPnL;
	}

	private static Map<Double, Double> callPnL(TradeModle tradeModle, List<Double> strikes) {

		Map<Double, Double> result = new LinkedHashMap<>();

		for (Double integer : strikes) {
			double int_value = intrensicValue(tradeModle.getStrike(), integer, tradeModle.getOptionType());
			double pnl = 0;
			if (TradeType.LONG.equals(tradeModle.getTradeType())) {
				pnl = int_value - tradeModle.getPremium();
			} else {
				pnl = tradeModle.getPremium() - int_value;
			}
			result.put(integer, pnl);
		}
		return result;
	}

	private static double intrensicValue(double strike, double spotAtExpiry, OptionType optionType) {
		if (OptionType.CALL.equals(optionType)) {

			return Math.max((spotAtExpiry - strike), 0);

		} else if (OptionType.PUT.equals(optionType)) {

			return Math.max((strike - spotAtExpiry), 0);

		} else {
			throw new RuntimeException("OptionType must be CALL or PUT");
		}
	}

	private static List<Double> getStrikes(double strike, double diff) {

		List<Double> lst = new ArrayList<>();

		double lowerLimit = strike - (20 * diff);

		for (int i = 0; i < 40; i++) {
			lowerLimit = lowerLimit + diff;
			lst.add(lowerLimit);
		}
		return lst;
	}
}