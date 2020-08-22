package org.stocksrin.v2.backtest;

import java.util.ArrayList;
import java.util.List;

import org.stocksrin.v2.common.model.option.Datum;
import org.stocksrin.v2.common.model.option.OptionModel;
import org.stocksrin.v2.common.option.utils.IntraDayOptionChainJosnReader;

public class SSPrice {

	static double  openingPrice;

	public static void main(String[] args) throws Exception {
		Integer ceStrike = 12100;
		Integer peStrike = 12200;
		List<StrikePrice> lst = cal("06-Feb-2020", "06-Feb-2020", ceStrike, peStrike);
		openingPrice = lst.get(0).getUnderlyingPice();
		
		for (StrikePrice item : lst) {
			double total = item.getCePrice() + item.getPePrice();
			double diff= openingPrice-item.getUnderlyingPice();
			System.out.println("( " + item.getCePrice() + " - " + item.getPePrice() + ")  " + total + "  -> "
					+ item.getUnderlyingPice() + " [ "  +((int) diff ) + " ]");
		}
	}

	public static List<StrikePrice> cal(String expiry, String date, Integer ceStrike, Integer peStrike)
			throws Exception {

		List<StrikePrice> lst = new ArrayList<>();

		List<OptionModel> dat = IntraDayOptionChainJosnReader.getNiftyOptionChain(expiry, date, true);
		for (OptionModel optionModel : dat) {
			List<Datum> d = optionModel.getDatums();
			StrikePrice strikePrice = new StrikePrice();
			strikePrice.setTime(optionModel.getTimestamp());
			strikePrice.setUnderlyingPice(optionModel.getUnderlyingValue());
			for (Datum item : d) {
				if (item.getCE().getStrikePrice().equals(ceStrike)) {
					strikePrice.setCeStrike(item.getCE().getStrikePrice());
					strikePrice.setCePrice(item.getCE().getLastPrice());
				}

				if (item.getPE().getStrikePrice().equals(peStrike)) {
					strikePrice.setPeStrike(item.getPE().getStrikePrice());
					strikePrice.setPePrice(item.getPE().getLastPrice());
				}
			}
			lst.add(strikePrice);

		}

		return lst;
	}
}

