package org.stocksrin.v2.backtest;

import java.util.ArrayList;
import java.util.List;

import org.stocksrin.v2.common.model.option.Datum;
import org.stocksrin.v2.common.model.option.OptionModel;
import org.stocksrin.v2.common.option.utils.IntraDayOptionChainJosnReader;

public class SSPriceDisorder {

	public static void main(String[] args) throws Exception {
		Integer atmStrike = 12000;
		Integer distance = 200;

		List<Integer> ceStrikes = new ArrayList<>();
		int tmp2 = atmStrike - distance;

		for (int i = 0; i < 6; i++) {
			if (tmp2 < (atmStrike - 50)) {
				ceStrikes.add(tmp2);
				tmp2 = tmp2 + 50;
			}

		}
		System.out.println(ceStrikes);

		List<Integer> peStrikes = new ArrayList<>();
		int tmp = atmStrike + 150;

		for (int i = 0; i < 4; i++) {
			tmp = tmp + 50;
			peStrikes.add(tmp);

		}
		System.out.println(peStrikes);

		List<OptionModel> dat = IntraDayOptionChainJosnReader.getNiftyOptionChain("06-Feb-2020", "05-Feb-2020", true);

		for (OptionModel optionModel : dat) {
			for (Integer ceS : ceStrikes) {

				for (Integer peS : peStrikes) {

					double total = getCEPrice(optionModel, "06-Feb-2020", "05-Feb-2020", ceS, peS);

					double d = Math.abs(total - (peS - ceS));
					if (d > 10) {
						System.out.println("Time " + optionModel.getTimestamp());
						System.out.println("CE " + ceS);
						System.out.println("PE " + peS);
						System.out.println(peS - ceS);
						System.out.println("Total " + total);
						System.out.println("Point Proft -> " + d);
						System.out.println("------------------------------");
					}

				}
				System.out.println("******************************");
			}
		}

		// List<StrikePrice> lst = cal("06-Feb-2020", "05-Feb-2020", 300, atmStrike);

	}

	public static double getCEPrice(OptionModel optionModel, String expiry, String date, Integer ceStrike,
			Integer peStrike) throws Exception {
		double cePrice = 0;
		double pePrice = 0;

		List<Datum> datums = optionModel.getDatums();
		for (Datum d : datums) {
			if (d.getCE().getStrikePrice().equals(ceStrike)) {
				cePrice = d.getCE().getLastPrice();
			}

			if (d.getPE().getStrikePrice().equals(peStrike)) {
				pePrice = d.getPE().getLastPrice();
			}

		}
		return cePrice + pePrice;
	}

	public static List<StrikePrice> cal(String expiry, String date, int diff, Integer atm) throws Exception {

		List<StrikePrice> lst = new ArrayList<>();

		List<OptionModel> dat = IntraDayOptionChainJosnReader.getNiftyOptionChain(expiry, date, true);
		// Integer atmStrike = OptionUtils.getATMStrikeV2(dat.get(0), 50);
		Integer atmStrike = atm;
		Integer lowerStrike = atmStrike - diff;
		Integer upperStrike = atmStrike + diff;

		for (OptionModel optionModel : dat) {
			List<Datum> datums = optionModel.getDatums();
			for (Datum d : datums) {
				double totalcepe = 0.0;
				if (d.getCE().getStrikePrice().equals(lowerStrike)) {

					System.out.println(d.getCE().getStrikePrice() + " - >" + d.getCE().getLastPrice());
					totalcepe = totalcepe + d.getCE().getLastPrice();
					double pevalue = 0.0;

					while (pevalue == 0.0) {
						pevalue = getPEvalue(datums, upperStrike);
					}
					totalcepe = totalcepe + pevalue;

				}

				if (totalcepe != 0.0) {

					int temp = (int) totalcepe;
					Integer idealDiff = Math.abs(lowerStrike - upperStrike);
					Integer actualdiff = Math.abs(temp - idealDiff);
					System.out.println(idealDiff);
					System.out.println(temp);
					System.out.println(actualdiff);
					System.out.println("***********");
				}

				// System.out.println("************");
			}

		}

		return lst;
	}

	private static double getPEvalue(List<Datum> datums, Integer strike) {
		for (Datum d : datums) {
			if (d.getCE().getStrikePrice().equals(strike)) {
				System.out.println(d.getPE().getStrikePrice() + " - >" + d.getPE().getLastPrice());
				return d.getPE().getLastPrice();

			}
		}
		return 0;
	}

}
