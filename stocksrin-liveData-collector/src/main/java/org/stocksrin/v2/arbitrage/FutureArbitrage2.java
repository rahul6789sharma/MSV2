package org.stocksrin.v2.arbitrage;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.stocksrin.common.utils.AppConstant;
import org.stocksrin.common.utils.CommonUtils;
import org.stocksrin.common.utils.DateUtils;
import org.stocksrin.v2.common.model.future.EntityABR;
import org.stocksrin.v2.common.model.future.Future;
import org.stocksrin.v2.common.model.future.Metadata;
import org.stocksrin.v2.common.model.future.Stock;
import org.stocksrin.v2.data.Data;
import org.stocksrin.v2.price.retrival.service.FutureRetrivalService;

public class FutureArbitrage2 {

	public static void main(String[] args) {
		loadStockData();
		Set<String> stocks = Data.currentMonthABR.keySet();
		for (String string : stocks) {
			System.out.println("Symbole: " + string);
			futurePrice(string, 0); // current expiry
			futurePrice(string, 1); // next expiry
		}
	}

	public static void futurePrice(String symbole, int expiry) {

		Future future = FutureRetrivalService.task(symbole);

		if (future != null) {

			List<Stock> stocks = future.getStocks();
			for (Stock stock : stocks) {
				Metadata metadata = stock.getMetadata();
				Double diff = metadata.getLastPrice() - stock.getUnderlyingValue();
				Double underlyingPrice = stock.getUnderlyingValue();
				// future price needs to be more
				if (metadata.getLastPrice() > underlyingPrice) {
					double targetAbrPercentage = 0.005;
					if (metadata.getExpiryDate().contains(DateUtils.getNextMonth(0))) {
						System.out.println("diff: " + diff);
						targetAbrPercentage = 0.01;
						System.out.println("current month Expiry " + metadata.getExpiryDate());
						EntityABR entity = Data.currentMonthABR.get(symbole);
						updateData(future, stock, entity, targetAbrPercentage);
					} else if (metadata.getExpiryDate().contains(DateUtils.getNextMonth(1))) {
						System.out.println("diff: " + diff);
						targetAbrPercentage = 0.02;
						System.out.println("Next month Expiry " + metadata.getExpiryDate());
						EntityABR entity = Data.nextMonthABR.get(symbole);
						updateData(future, stock, entity, targetAbrPercentage);
					} else {
						return;
					}

				}
			}
		}
		System.out.println("***************************");
	}

	private static void updateData(Future future, Stock stock, EntityABR entity, double targetAbrPercentage) {
		Metadata metadata = stock.getMetadata();
		Double diff = metadata.getLastPrice() - stock.getUnderlyingValue();
		entity.setDiff(diff);
		entity.setFuturePrice(metadata.getLastPrice());
		double arbExists = diff * entity.getLotSize();
		entity.setArbatrage((int) arbExists);
		entity.setUnderlyingValue(stock.getUnderlyingValue());
		entity.setExpiryDate(metadata.getExpiryDate());
		entity.setCompanyName(future.getInfo().getCompanyName());

		double invAmount = stock.getUnderlyingValue() * entity.getLotSize();
		entity.setInvAmount((int) invAmount);

		double abrPercenatge = (arbExists * 100) / invAmount;
		entity.setAbrPercenatge(abrPercenatge);

		if (entity.getMaxArbPer() < abrPercenatge) {
			entity.setMaxArbPer(abrPercenatge);
		}

		if (entity.getMinArbPer() > abrPercenatge) {
			entity.setMinArbPer(abrPercenatge);
		}

		if (entity.getMaxDiff() < diff) {
			entity.setMaxDiff(diff);
		}

		if (entity.getMinDiff() > diff) {
			entity.setMinDiff(diff);
		}

		System.out.println(entity);
	}

	private static void loadStockData() {
		String fnolist = AppConstant.STOCKSRIN_OPTION_DATA_DIR_STOCKS_LIST_2;
		List<String> stocsk = CommonUtils.getFNOList(fnolist);

		for (String string : stocsk) {
			String[] item = string.split(",");
			if (!(item[1].trim().equalsIgnoreCase("SYMBOL") || item[1].trim().equals("BANKNIFTY") || item[1].trim().equals("NIFTY") || item[1].trim().equals("NIFTYIT"))) {
				Data.currentMonthABR.put(item[1].trim(), new EntityABR(item[1].trim(), Integer.parseInt(item[2].trim())));
				Data.nextMonthABR.put(item[1].trim(), new EntityABR(item[1].trim(), Integer.parseInt(item[2].trim())));
			}
		}
	}
}

class Entity {

}
