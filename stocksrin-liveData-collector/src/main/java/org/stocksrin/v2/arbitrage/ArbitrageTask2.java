package org.stocksrin.v2.arbitrage;

import java.util.List;
import java.util.Set;
import java.util.TimerTask;

import org.springframework.stereotype.Service;
import org.stocksrin.common.utils.AppConstant;
import org.stocksrin.common.utils.CommonUtils;
import org.stocksrin.common.utils.DateUtils;
import org.stocksrin.common.utils.NSEHolidayUtils;
import org.stocksrin.v2.common.model.future.EntityABR;
import org.stocksrin.v2.data.Data;

@Service
public class ArbitrageTask2 extends TimerTask {

	private long timeInteval5min = 300000; // 5 min

	@Override
	public void run() {
		if (!DateUtils.isWeekEndDay() && !NSEHolidayUtils.isHoliday()) {
			loadStockData();
			Set<String> stocks = Data.currentMonthABR.keySet();
			while (CommonUtils.getEveningTime()) {

				for (String string : stocks) {
					//System.out.println("Symbole: " + string);
					FutureArbitrage2.futurePrice(string, 0); // current expiry
					// FutureArbitrage2.futurePrice(string, 1); // next expiry
				}

				try {
					Thread.sleep(timeInteval5min);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

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