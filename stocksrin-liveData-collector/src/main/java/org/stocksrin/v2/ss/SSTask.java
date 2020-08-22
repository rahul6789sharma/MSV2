package org.stocksrin.v2.ss;

import java.util.TimerTask;

import org.springframework.stereotype.Service;
import org.stocksrin.common.utils.CommonUtils;
import org.stocksrin.common.utils.DateUtils;
import org.stocksrin.common.utils.NSEHolidayUtils;
import org.stocksrin.email.SendEmail;
import org.stocksrin.v2.data.Data;

@Service
public class SSTask extends TimerTask {

	private long timeInteval5min = 180000; // 5 min

	@Override
	public void run() {
		startTask(timeInteval5min);
	}

	private void startTask(long sleeTimeInteval) {

		if (!DateUtils.isWeekEndDay() && !NSEHolidayUtils.isHoliday()) {

			while (CommonUtils.getEveningTime()) {
				try {
					Data.ssEntitys.clear();
					SSComboPrice.putData();
					Thread.sleep(sleeTimeInteval);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			if (NSEHolidayUtils.isHoliday()) {
				SendEmail.sentMail("Market is closed Today", "Take rest", "Live Data Collector");
			}
		}
	}
}