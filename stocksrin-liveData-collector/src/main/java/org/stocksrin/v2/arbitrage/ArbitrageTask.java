package org.stocksrin.v2.arbitrage;

import java.util.List;
import java.util.TimerTask;

import org.springframework.stereotype.Service;
import org.stocksrin.common.utils.AppConstant;
import org.stocksrin.common.utils.CommonUtils;
import org.stocksrin.common.utils.DateUtils;
import org.stocksrin.common.utils.NSEHolidayUtils;

@Service
public class ArbitrageTask extends TimerTask {

	private long timeInteval5min = 300000; // 5 min

	@Override
	public void run() {
		if (!DateUtils.isWeekEndDay() && !NSEHolidayUtils.isHoliday()) {
			String fnolist = AppConstant.STOCKSRIN_OPTION_DATA_DIR_STOCKS_LIST_2;
			List<String> stocsk = CommonUtils.getFNOList(fnolist);

			while (CommonUtils.getEveningTime()) {
				for (String string : stocsk) {
					try {
						String[] item = string.split(",");
						if (!(item[1].trim().equalsIgnoreCase("SYMBOL") || item[1].trim().equals("BANKNIFTY") || item[1].trim().equals("NIFTY") || item[1].trim().equals("NIFTYIT"))) {
							/*System.out.println(item[1]);
							System.out.println(item[2]);*/
							FutureArbitrage.get(item[1].trim(), Integer.parseInt(item[2].trim()));
						}
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
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
}
