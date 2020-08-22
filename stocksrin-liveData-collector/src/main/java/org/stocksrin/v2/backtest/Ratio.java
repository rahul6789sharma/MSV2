package org.stocksrin.v2.backtest;

import java.util.ArrayList;
import java.util.List;

import org.stocksrin.v2.common.model.option.Datum;
import org.stocksrin.v2.common.model.option.OptionModel;
import org.stocksrin.v2.common.option.utils.IntraDayOptionChainJosnReader;

public class Ratio {

	private static int lot = 75;

	public static void main(String[] args) throws Exception {
		Integer sortCEStrike = 12300;
		Integer longCEStrike = 12350;

		Integer sortPEStrike = 12200;
		Integer longPEStrike = 12150;

		List<BackTestModel> lst = cal("30-Jan-2020", "30-Jan-2020", sortCEStrike, longCEStrike, sortPEStrike,
				longPEStrike, lot);
		double openingPrice = lst.get(0).getUnderlyingPice();
		print(lst);

	}
	static String line = "-------------------------------------------------------------------";

	private static void print(List<BackTestModel> lst) {

		double ceShortEntryPrice = 0;
		double ceLongEntryPrice = 0;

		double peShortEntryPrice = 0;
		double peLongEntryPrice = 0;
		double Entirytotal = 0;
		double underlyingEntiryPrice=0;

		int i = 0;

		for (BackTestModel backTestModel : lst) {

			StringBuilder result = new StringBuilder();
			result.append("Type    Expiry    Strike    Qty   AvgPrice    ltp    change    P&L " + "\n");
			
			result.append(line + "\n");
			double ceShortPrice = -(backTestModel.getSortCE().getPrice() * backTestModel.getSortCE().getLot());
			double ceSLongPrice = -(backTestModel.getLongCE().getPrice() * backTestModel.getLongCE().getLot());

			double peShortPrice = -(backTestModel.getSortPE().getPrice() * backTestModel.getSortPE().getLot());
			double peSLongPrice = -(backTestModel.getLongPE().getPrice() * backTestModel.getLongPE().getLot());

			double total = ceShortPrice + ceSLongPrice + peShortPrice + peSLongPrice;
			if (i == 0) {
				ceShortEntryPrice = backTestModel.getSortCE().getPrice();
				ceLongEntryPrice = backTestModel.getLongCE().getPrice();
				peShortEntryPrice = backTestModel.getSortPE().getPrice();
				peLongEntryPrice = backTestModel.getLongPE().getPrice();
				Entirytotal = total;
				underlyingEntiryPrice=backTestModel.getUnderlyingPice();
			}
			int ceShortChnage = - (int) (ceShortEntryPrice - backTestModel.getSortCE().getPrice());
			int ceShortPNL= ceShortChnage * backTestModel.getSortCE().getLot() ;
			
			
			int ceLongChnage = - (int) (ceLongEntryPrice - backTestModel.getLongCE().getPrice());
			int ceLongPNL= ceLongChnage * backTestModel.getLongCE().getLot() ;
			
			
			int peShortChnage = - (int) (peShortEntryPrice - backTestModel.getSortPE().getPrice());
			int peShortPNL= peShortChnage * backTestModel.getSortPE().getLot() ;
			
			
			int peLongChnage = - (int) (peLongEntryPrice - backTestModel.getLongPE().getPrice());
			int peLongPNL= peLongChnage * backTestModel.getLongPE().getLot() ;
			
			result.append("CE " + "               " + backTestModel.getSortCE().getStrike() + "    "
					+ backTestModel.getSortCE().getLot() + "    " + ceShortEntryPrice + "       "
					+ backTestModel.getSortCE().getPrice() + "     " + ceShortChnage + "     " + ceShortPNL);

			result.append("\n");
			
			result.append("CE " + "               " + backTestModel.getLongCE().getStrike() + "    "
					+ backTestModel.getLongCE().getLot() + "    " + ceLongEntryPrice + "       "
					+ backTestModel.getLongCE().getPrice() + "     " + ceLongChnage + "     " + ceLongPNL);

			result.append("\n");
			
			result.append("PE " + "               " + backTestModel.getLongPE().getStrike() + "    "
					+ backTestModel.getLongPE().getLot() + "    " + peLongEntryPrice + "       "
					+ backTestModel.getLongPE().getPrice() + "     " + peLongChnage + "     " + peLongPNL);

			result.append("\n");
			
			result.append("PE " + "               " + backTestModel.getSortPE().getStrike() + "    "
					+ backTestModel.getSortPE().getLot() + "    " + peShortEntryPrice + "       "
					+ backTestModel.getSortPE().getPrice() + "     " + peShortChnage + "     " + peShortPNL);

			result.append("\n");
			result.append(line + "\n");
			int finalToal=ceShortPNL+ceLongPNL+peLongPNL+peShortPNL;
			result.append(finalToal + "  underlying Change [ " + (int)(backTestModel.getUnderlyingPice()- underlyingEntiryPrice)+  " ] \n");
			result.append(line + "\n");
			
			System.out.println("*********************************************************");
			i++;
			System.out.println(result);
		}

	}

	public static List<BackTestModel> cal(String expiry, String date, Integer sortCEStrike, Integer longCEStrike,
			Integer sortPEStrike, Integer longPEStrike, int lot) throws Exception {

		List<BackTestModel> lst = new ArrayList<>();

		List<OptionModel> dat = IntraDayOptionChainJosnReader.getNiftyOptionChain(expiry, date, true);
		for (OptionModel optionModel : dat) {
			List<Datum> d = optionModel.getDatums();

			BackTestModel backTestModel = new BackTestModel();
			backTestModel.setTime(optionModel.getTimestamp());
			backTestModel.setUnderlyingPice(optionModel.getUnderlyingValue());

			for (Datum item : d) {

				if (item.getCE().getStrikePrice().equals(sortCEStrike)) {

					Entity entity = new Entity(sortCEStrike, item.getCE().getLastPrice(), -3 * lot);
					backTestModel.setSortCE(entity);
				}

				if (item.getCE().getStrikePrice().equals(longCEStrike)) {

					Entity longCE = new Entity(longCEStrike, item.getCE().getLastPrice(), lot);
					backTestModel.setLongCE(longCE);
				}

				if (item.getPE().getStrikePrice().equals(sortPEStrike)) {

					Entity entity = new Entity(sortPEStrike, item.getPE().getLastPrice(), -3 * lot);
					backTestModel.setSortPE(entity);
				}

				if (item.getPE().getStrikePrice().equals(longPEStrike)) {

					Entity longPE = new Entity(longPEStrike, item.getPE().getLastPrice(), lot);
					backTestModel.setLongPE(longPE);
				}

			}
			lst.add(backTestModel);

		}

		return lst;
	}
}
