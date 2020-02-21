package org.stocksrin.v2.common.option.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.stocksrin.v2.common.model.option.Datum;
import org.stocksrin.v2.common.model.option.OptionModel;

public class OptionUtils {

	// strikediff=50 for nifty
	// strikediff =100 for bnf
	public static Integer getATMStrikeV2(OptionModel optionModles, int strikediff) {
		Integer atmStrike = 0;
		try {
			double spot = optionModles.getUnderlyingValue();
			int round = round((int) spot, strikediff);
			atmStrike = getATM(optionModles, round, strikediff);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return atmStrike;
	}

	// function to round the number
	private static int round(int n, int multiple) {
		// Smaller multiple
		int a = (n / multiple) * multiple;

		// Larger multiple
		int b = a + multiple;

		// Return of closest of two
		return (n - a > b - n) ? b : a;
	}

	private static Integer getATM(OptionModel optionModles, int round, int symboleDiff) {
		try {
			int one = round - (symboleDiff * 2);
			int two = round - symboleDiff;
			int four = round + symboleDiff;
			int five = round + (symboleDiff * 2);

			Integer oneStrike = null;
			Integer twoStrike = null;
			Integer threeStrike = null;
			Integer foureStrike = null;
			Integer fiveStrike = null;

			List<Datum> lst = optionModles.getDatums();
			List<Double> premiumDiff = new ArrayList<>();
			for (Datum optionModle : lst) {
				if (optionModle.getStrikePrice() != null) {
					double value = optionModle.getStrikePrice();
					if (value == one) {
						if (optionModle.getCE().getLastPrice() != null && optionModle.getPE().getLastPrice() != null) {
							double diff = Math.abs(optionModle.getCE().getLastPrice() - optionModle.getPE().getLastPrice());
							premiumDiff.add(diff);
							oneStrike = optionModle.getStrikePrice();
						}

					} else if (value == two) {

						if (optionModle.getCE().getLastPrice() != null && optionModle.getPE().getLastPrice() != null) {
							double diff = Math.abs(optionModle.getCE().getLastPrice() - optionModle.getPE().getLastPrice());
							premiumDiff.add(diff);
							twoStrike = optionModle.getStrikePrice();
						}

					} else if (value == round) {

						if (optionModle.getCE().getLastPrice() != null && optionModle.getPE().getLastPrice() != null) {
							double diff = Math.abs(optionModle.getCE().getLastPrice() - optionModle.getPE().getLastPrice());
							premiumDiff.add(diff);
							threeStrike = optionModle.getStrikePrice();
						}

					} else if (value == four) {

						if (optionModle.getCE().getLastPrice() != null && optionModle.getPE().getLastPrice() != null) {
							double diff = Math.abs(optionModle.getCE().getLastPrice() - optionModle.getPE().getLastPrice());
							premiumDiff.add(diff);
							foureStrike = optionModle.getStrikePrice();
						}

					} else if (value == five) {

						if (optionModle.getCE().getLastPrice() != null && optionModle.getPE().getLastPrice() != null) {
							double diff = Math.abs(optionModle.getCE().getLastPrice() - optionModle.getPE().getLastPrice());
							premiumDiff.add(diff);
							fiveStrike = optionModle.getStrikePrice();
						}
						break;
					}
				}
			}
			if (!premiumDiff.isEmpty()) {
				List<Double> origianl = new ArrayList<>(premiumDiff);
				Collections.sort(premiumDiff);
				int index = origianl.indexOf(premiumDiff.get(0));
				if (index == 0) {
					return oneStrike;
				} else if (index == 1) {
					return twoStrike;
				} else if (index == 2) {
					return threeStrike;
				} else if (index == 3) {
					return foureStrike;
				} else if (index == 4) {
					return fiveStrike;
				}
			}
		} catch (Exception e) {
			System.out.println("Erro");
			e.printStackTrace();
		}
		return null;
	}

}
