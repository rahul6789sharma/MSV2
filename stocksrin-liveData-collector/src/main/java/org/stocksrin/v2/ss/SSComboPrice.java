package org.stocksrin.v2.ss;

import java.util.ArrayList;
import java.util.List;

import org.stocksrin.v2.common.model.option.Datum;
import org.stocksrin.v2.common.model.option.OptionModel;
import org.stocksrin.v2.common.model.option.SSEntity;
import org.stocksrin.v2.common.option.utils.OptionUtils;
import org.stocksrin.v2.data.Data;

public class SSComboPrice {

	public static void main(String[] args) throws Exception {

	}

	public static void putData() throws Exception {
		/*
		 * OptionChainPriceRetrivalService obj = new OptionChainPriceRetrivalService();
		 * obj.task("NIFTY");
		 */
		// Data.s

		if (!Data.shortedExpiry.isEmpty()) {
			OptionModel data = Data.getNiftyData().get(Data.shortedExpiry.get(0));
			Integer atmStrike = OptionUtils.getATMStrikeV2(data, 50);
			System.out.println("Atm " + atmStrike);
			List<Integer> ceStrikes = ceStrikes(atmStrike);

			List<Integer> peStrikes = peStrikes(atmStrike);

			// List<SSEntity> ssEntitys = new ArrayList<>();

			for (Integer ceS : ceStrikes) {

				for (Integer peS : peStrikes) {

					SSEntity ssEntity = new SSEntity();
					ssEntity.setCeStrike(ceS);
					ssEntity.setPeStrike(peS);
					ssEntity.setTimeStamp(data.getTimestamp());
					ssEntity.setUnderlyingPice(data.getUnderlyingValue());
					ssEntity.setExpiry(data.getExpiryDate());

					double total = getCEPrice(ssEntity, data, ceS, peS);

					double d = Math.abs(total - (peS - ceS));
					if (d > 10) {

						double strikeDiff = Math.abs(ssEntity.getPeStrike() - ssEntity.getCeStrike());
						double premiumDiff = Math.abs(ssEntity.getPePrice() + ssEntity.getCePrice());
						// System.out.println(strikeDiff);
						// System.out.println(premiumDiff);
						if (premiumDiff > strikeDiff) {
							double delta = Math.abs(ssEntity.getPePrice() - ssEntity.getCePrice());
							if (delta < 100) {
								Data.ssEntitys.add(ssEntity);
							}

						}
					}
				}
				// System.out.println("******************************");
			}
		}

		/*
		 * for (SSEntity ssEntity : ssEntitys) {
		 * System.out.println(ssEntity.getCeStrike() + " -> " + ssEntity.getCePrice());
		 * System.out.println(ssEntity.getPeStrike() + " -> " + ssEntity.getPePrice());
		 * 
		 * System.out.println("Diff " + " -> " + (ssEntity.getPeStrike() -
		 * ssEntity.getCeStrike()));
		 * 
		 * double strikeDiff = Math.abs(ssEntity.getPeStrike() -
		 * ssEntity.getCeStrike()); double premiumDiff = Math.abs(ssEntity.getPePrice()
		 * + ssEntity.getCePrice()); System.out.println("Profit " + " -> " +
		 * Math.abs(strikeDiff - premiumDiff));
		 * System.out.println("******************************"); }
		 */
	}

	public static double getCEPrice(SSEntity ssEntity, OptionModel optionModel, Integer ceStrike, Integer peStrike)
			throws Exception {
		double cePrice = 0;
		double pePrice = 0;

		List<Datum> datums = optionModel.getDatums();
		for (Datum d : datums) {
			if (d.getCE().getStrikePrice().equals(ceStrike)) {
				cePrice = d.getCE().getLastPrice();
				// System.out.println("Ce price " + cePrice);
				ssEntity.setCePrice(cePrice);
			}

			if (d.getPE().getStrikePrice().equals(peStrike)) {
				pePrice = d.getPE().getLastPrice();
				// System.out.println("Pe price " + pePrice);
				ssEntity.setPePrice(pePrice);
			}

		}
		ssEntity.setTotal(cePrice + pePrice);
		return cePrice + pePrice;
	}

	private static List<Integer> ceStrikes(Integer atmStrike) {

		Integer distance = 300;

		List<Integer> ceStrikes = new ArrayList<>();
		int tmp2 = atmStrike - distance;

		for (int i = 0; i < 6; i++) {
			if (tmp2 < (atmStrike - 50)) {
				ceStrikes.add(tmp2);
				tmp2 = tmp2 + 50;
			}
		}
		return ceStrikes;
	}

	private static List<Integer> peStrikes(Integer atmStrike) {

		List<Integer> peStrikes = new ArrayList<>();
		int tmp = atmStrike + 50;

		for (int i = 0; i < 6; i++) {
			tmp = tmp + 50;
			peStrikes.add(tmp);

		}

		return peStrikes;

	}
}
