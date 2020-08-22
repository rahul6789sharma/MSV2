package org.stocksrin.v2.download;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.stocksrin.common.utils.AppConstant;
import org.stocksrin.common.utils.CommonUtils;
import org.stocksrin.common.utils.DateUtils;
import org.stocksrin.common.utils.FileUtils;
import org.stocksrin.common.utils.NSEHolidayUtils;
import org.stocksrin.common.utils.options.ExpiryUtils;
import org.stocksrin.v2.common.model.option.OptionModel;
import org.stocksrin.v2.data.Data;

@Service
public class OptionChainDownloader2 extends TimerTask {

	private long timeInteval10min = 600000; // 10 min
	private long timeInteval5min = 300000; // 5 min
	private static final Logger log = LoggerFactory.getLogger(OptionChainDownloader2.class);

	@Override
	public void run() {
		// log.info("******* OptionChainDownloader Started **********");
		if (!DateUtils.isWeekEndDay() && !NSEHolidayUtils.isHoliday()) {

			new Thread(() -> downloadFile(timeInteval10min, AppConstant.STOCKSRIN_OPTION_DATA_DIR_NIFTY_INTRDAY2_10MIN,
					"NIFTY")).start();
			new Thread(() -> downloadFile(timeInteval10min,
					AppConstant.STOCKSRIN_OPTION_DATA_DIR_BANKNIFTY_INTRDAY2_10MIN, "BANKNIFTY")).start();

			new Thread(() -> downloadFile(timeInteval5min, AppConstant.STOCKSRIN_OPTION_DATA_DIR_NIFTY_INTRDAY2_5MIN,
					"NIFTY")).start();
			new Thread(() -> downloadFile(timeInteval5min,
					AppConstant.STOCKSRIN_OPTION_DATA_DIR_BANKNIFTY_INTRDAY2_5MIN, "BANKNIFTY")).start();

		}
	}

	private void downloadFile(long sleeTimeInteval, String path, String symbole) {
		int count = 0;
		while (CommonUtils.getEveningTime()) {
			System.out.println("Downlodaing OC " + symbole + "  count  " + count);
			try {
				Map<String, OptionModel> data = null;
				if (!Data.shortedExpiry.isEmpty()) {
					if (symbole.equals("NIFTY")) {
						data = Data.getNiftyData();
					} else if (symbole.equals("BANKNIFTY")) {
						data = Data.getBNFData();
					}

					String currentExpiry = Data.shortedExpiry.get(0);
					String nextExpiry = Data.shortedExpiry.get(1);
					try {
						String monthlyExpiry = ExpiryUtils.getCurrentMonthExpiry(Data.shortedExpiry);

						if (!monthlyExpiry.equalsIgnoreCase(currentExpiry)
								&& !monthlyExpiry.equalsIgnoreCase(nextExpiry)) {
							OptionModel optionModles = data.get(monthlyExpiry);
							downloadData(optionModles, path, monthlyExpiry, count);

						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					OptionModel optionModles = data.get(currentExpiry);
					downloadData(optionModles, path, currentExpiry, count);

					OptionModel optionModlesNext = data.get(nextExpiry);
					downloadData(optionModlesNext, path, nextExpiry, count);

					count++;
					Thread.sleep(sleeTimeInteval);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	
	private OptionModel downloadData(OptionModel optionModles, String path, String expiry, int count) {
		try {
			System.out.println("downloading ");
			if (optionModles != null) {
				String date = optionModles.getTimestamp().split(" ")[0];
				boolean makedirstatus = FileUtils.makeDir(path + expiry);
				String todayDir = path + expiry + File.separator + date;
				boolean makedirstatus2 = FileUtils.makeDir(todayDir);

				if (makedirstatus) {
					log.info("New Expiry Start " + expiry);
				}
				if (makedirstatus2) {
					log.info("New Day Start " + expiry);
				}

				// OptionModles optionModles = niftyClient.getOptionModel(firstExpiry);
				// String filename = date + "_" + count;
				String optionFileDir = path + expiry + File.separator + date + File.separator + count + ".json";

				boolean isFileExist = FileUtils.isFileExits(optionFileDir);
				log.info(optionFileDir + ", isFileExist : " + isFileExist);

				if (!isFileExist) {
					FileUtils.writeDataAsJson(optionModles, optionFileDir);
					log.info("Downloaded " + optionFileDir);
				} else {
					log.info("already data downloaded , " + optionFileDir);
				}
				return optionModles;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return optionModles;

	}

	public static void main(String[] args) {
		List<String> s = new ArrayList<>();

		s.add("07-May-2020");
		s.add("14-May-2020");
		s.add("21-May-2020");
		s.add("28-May-2020");

		String m = ExpiryUtils.getCurrentMonthExpiry(s);
		System.out.println(m);
	}

	// private String getMo
}
