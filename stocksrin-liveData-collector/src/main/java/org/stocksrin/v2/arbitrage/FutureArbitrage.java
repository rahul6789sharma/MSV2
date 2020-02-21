package org.stocksrin.v2.arbitrage;

import java.util.List;

import org.stocksrin.common.utils.AppConstant;
import org.stocksrin.common.utils.CommonUtils;
import org.stocksrin.common.utils.DateUtils;
import org.stocksrin.v2.common.model.future.ArbtriageEntity;
import org.stocksrin.v2.common.model.future.Future;
import org.stocksrin.v2.common.model.future.Metadata;
import org.stocksrin.v2.common.model.future.Stock;
import org.stocksrin.v2.data.Data;
import org.stocksrin.v2.price.retrival.service.FutureRetrivalService;

public class FutureArbitrage {

	public static void main(String[] args) {

		String fnolist = AppConstant.STOCKSRIN_OPTION_DATA_DIR_STOCKS_LIST_2;
		List<String> stocsk = CommonUtils.getFNOList(fnolist);
		for (String string : stocsk) {
			// System.out.println(string);
			String[] item = string.split(",");
			if (! ( item[1].trim().equalsIgnoreCase("SYMBOL") || item[1].trim().equals("BANKNIFTY") || item[1].trim().equals("NIFTY") || item[1].trim().equals("NIFTYIT"))) {
				System.out.println(item[1]);
				System.out.println(item[2]);
				get(item[1].trim(), Integer.parseInt(item[2].trim()));
			}

		}
	}

	private static void test() {

		String fnolist = AppConstant.STOCKSRIN_OPTION_DATA_DIR_STOCKS_LIST;
		List<String> stocsk = CommonUtils.getFNOList(fnolist);
		while (CommonUtils.getEveningTime()) {

			for (String string : stocsk) {
				// System.out.println(string);
				String stockname = string.split("	")[0].trim(); // dont change space String lot =
				String lot = string.split("	")[1].trim(); // System.out.println(stockname); //
				// System.out.println(lot);

				get(stockname, Integer.parseInt(lot));
			}
		}
	}

	public static void get(String symbole, int lotsize) {

		try {
			Future future = FutureRetrivalService.task(symbole);
			if (future != null) {
				List<Stock> stocks = future.getStocks();

				for (Stock stock : stocks) {
					Metadata m = stock.getMetadata();
					// System.out.println(m);
					/*
					 * System.out.println(stock.getUnderlyingValue());
					 * System.out.println(m.getLastPrice());
					 */
					Double diff = m.getLastPrice() - stock.getUnderlyingValue();
					// System.out.println("diff" + diff);
					Double underlyingPrice = stock.getUnderlyingValue();
					// future price needs to be more
					if (m.getLastPrice() > underlyingPrice) {
						//System.out.println("******** " + diff);
						//System.out.println("underlyingPrice :" + underlyingPrice);
						//System.out.println("future : " + m.getLastPrice());
						double arbitrage = diff * lotsize;
						double investment = underlyingPrice * lotsize;
						double targetPercentage = 0.005;
						// System.out.println(m.getExpiryDate());
						// System.out.println(DateUtils.getNextMonth(1));
						if (m.getExpiryDate().contains(DateUtils.getNextMonth(1))) {
							targetPercentage = .005;
						} else if (m.getExpiryDate().contains(DateUtils.getNextMonth(2))) {
							targetPercentage = .03;
						}
						 double arbitrageShouldExists = investment * targetPercentage;
						 //System.out.println("% target " + targetPercentage * 100);
						/* System.out.println("target : " + per);
						 System.out.println("Exitis : " + arbitrage);
						 System.out.println("target pers " + per);*/
						// Double percentage = (arbitrage * 100) / investment;
						 //System.out.println("% Exits " + percentage);
						 //System.out.println(per);
						 //System.out.println(arbitrage);
						if (arbitrageShouldExists < arbitrage) {
							/*System.out.println(m.getExpiryDate());
							System.out.println(symbole);
							System.out.println("Money required " + investment);
							System.out.println("arbitrage " + arbitrage);*/
							Double percentage = (arbitrage * 100) / investment;
							//System.out.println("% " + percentage);

							m.setLotSize(lotsize);
							m.setSymbol(future.getInfo().getSymbol());
							m.setCompanyName(future.getInfo().getCompanyName());
							m.setAbrPercenatge(percentage);
							m.setArbatrage((int) arbitrage);
							m.setInvAmount((int) investment);
							ArbtriageEntity arbtriageEntity = new ArbtriageEntity(future.getInfo().getSymbol(), m.getExpiryDate());
							if (!Data.result.containsKey(arbtriageEntity)) {
								stock.setMarketDeptOrderBook(null);
								Data.result.put(arbtriageEntity, stock);
							} else {
								// Stock obj = Data.result.get(arbtriageEntity);

							}

						} else {
							ArbtriageEntity arbtriageEntity = new ArbtriageEntity(future.getInfo().getSymbol(), m.getExpiryDate());
							if (Data.result.containsKey(arbtriageEntity)) {
								Data.result.remove(arbtriageEntity);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// result.forEach((i) -> System.out.println(i));
	}
}
