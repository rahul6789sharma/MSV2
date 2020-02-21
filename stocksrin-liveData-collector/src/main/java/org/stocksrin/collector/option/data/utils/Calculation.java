package org.stocksrin.collector.option.data.utils;

import java.util.ArrayList;
import java.util.List;

import org.stocksrin.common.model.option.MaxPain;
import org.stocksrin.common.model.option.MaxPains;
import org.stocksrin.v2.common.model.option.Datum;
import org.stocksrin.v2.common.model.option.OptionModel;

public class Calculation {
	private Calculation() {

	}

	public static MaxPains calMaxPain(OptionModel oi, Integer strickDiff) {

		MaxPains maxPains = new MaxPains();
		List<MaxPain> maxPainList = new ArrayList<>();
		List<Datum> datums = oi.getDatums();
		for (int i = 0; i < datums.size(); i++) {
			Integer strikePrice = datums.get(i).getCE().getStrikePrice();

			Integer ceOI = datums.get(i).getCE().getOpenInterest();
			Integer peOI = datums.get(i).getPE().getOpenInterest();

			MaxPain maxPain = new MaxPain(strikePrice, ceOI, peOI);
			Double total;

			Double callCuresult = 0.0;
			for (int j = 0; j < i; j++) {
				Integer a1 = datums.get(j).getCE().getOpenInterest();
				if (a1 != null) {
					callCuresult = callCuresult + (a1 * ((strickDiff * i) - (strickDiff * j)));
					maxPain.setCumulativeCe(callCuresult);
				}
			}

			Double putCuresult = 0.0;
			for (int j = i; j < datums.size(); j++) {
				Integer a1 = datums.get(j).getPE().getOpenInterest();
				if (a1 != null) {
					putCuresult = putCuresult + (a1 * ((strickDiff * j) - (strickDiff * i)));
					maxPain.setCumulativePe(putCuresult);
				}

			}

			total = putCuresult + callCuresult;
			maxPain.setTotal(total);
			maxPain.setStrickPrice(strikePrice);
			//maxPainList.add(maxPain);
			System.out.println(maxPain);

		}

		//maxPains.setDataSet(maxPainList);
		/*Double maxPainStrick = findMaxPain(maxPainList);
		maxPains.setMaxPainStrick(maxPainStrick);
		maxPains.setExpiry(expiry);*/
		return maxPains;

	}

	/*public static Integer findMaxPain(List<MaxPain> maxPainList) {
		Double smallest = maxPainList.get(0).getTotal();
		Integer strickPrice = 0;
		for (MaxPain maxPain : maxPainList) {

			if (smallest > maxPain.getTotal()) {
				smallest = maxPain.getTotal();
				strickPrice = maxPain.getStrickPrice();
			}
		}
		return strickPrice;
	}*/
}