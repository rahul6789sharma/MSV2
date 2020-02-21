package org.stocksrin.v2.price.retrival.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
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

			new Thread(() -> downloadFile(timeInteval10min, AppConstant.STOCKSRIN_OPTION_DATA_DIR_NIFTY_INTRDAY2_10MIN, "NIFTY")).start();
			new Thread(() -> downloadFile(timeInteval10min, AppConstant.STOCKSRIN_OPTION_DATA_DIR_BANKNIFTY_INTRDAY2_10MIN, "BANKNIFTY")).start();

			new Thread(() -> downloadFile(timeInteval5min, AppConstant.STOCKSRIN_OPTION_DATA_DIR_NIFTY_INTRDAY2_5MIN, "NIFTY")).start();
			new Thread(() -> downloadFile(timeInteval5min, AppConstant.STOCKSRIN_OPTION_DATA_DIR_BANKNIFTY_INTRDAY2_5MIN, "BANKNIFTY")).start();

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

	/*
	 * private void downloadBANKNifty(long sleeTimeInteval) { int count = 1; while
	 * (CommonUtils.getEveningTime()) {
	 * 
	 * try { if (!Data.shortedExpiry.isEmpty()) { String currentExpiry =
	 * Data.shortedExpiry.get(0); String nextExpiry = Data.shortedExpiry.get(1);
	 * OptionModel optionModles = Data.getBNFData().get(currentExpiry);
	 * downloadData(optionModles,
	 * AppConstant.STOCKSRIN_OPTION_DATA_DIR_BANKNIFTY_INTRDAY2, currentExpiry,
	 * count); OptionModel optionModlesNext = Data.getBNFData().get(nextExpiry);
	 * downloadData(optionModlesNext,
	 * AppConstant.STOCKSRIN_OPTION_DATA_DIR_BANKNIFTY_INTRDAY2, nextExpiry, count);
	 * count++; Thread.sleep(sleeTimeInteval); }
	 * 
	 * } catch (InterruptedException e) { e.printStackTrace(); }
	 * 
	 * } }
	 * 
	 * private void downloadNifty(long sleeTimeInteval) { int count = 1; while
	 * (CommonUtils.getEveningTime()) {
	 * 
	 * try { if (!Data.shortedExpiry.isEmpty()) { String currentExpiry =
	 * Data.shortedExpiry.get(0); String nextExpiry = Data.shortedExpiry.get(1);
	 * OptionModel optionModles = Data.getNiftyData().get(currentExpiry);
	 * downloadData(optionModles,
	 * AppConstant.STOCKSRIN_OPTION_DATA_DIR_NIFTY_INTRDAY2, currentExpiry, count);
	 * OptionModel optionModlesNext = Data.getNiftyData().get(nextExpiry);
	 * downloadData(optionModlesNext,
	 * AppConstant.STOCKSRIN_OPTION_DATA_DIR_NIFTY_INTRDAY2, nextExpiry, count);
	 * count++; Thread.sleep(sleeTimeInteval); }
	 * 
	 * } catch (InterruptedException e) { e.printStackTrace(); }
	 * 
	 * } }
	 */

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
		String date = new SimpleDateFormat("dd_MMM_yyyy").format(new Date());
		System.out.println(date);
	}
}
