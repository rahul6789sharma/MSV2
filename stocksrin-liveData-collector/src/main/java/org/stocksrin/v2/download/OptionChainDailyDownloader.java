package org.stocksrin.v2.download;

import java.io.File;
import java.util.Map;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.stocksrin.common.utils.AppConstant;
import org.stocksrin.common.utils.DateUtils;
import org.stocksrin.common.utils.FileUtils;
import org.stocksrin.common.utils.NSEHolidayUtils;
import org.stocksrin.email.SendEmail;
import org.stocksrin.v2.common.model.option.OptionModel;
import org.stocksrin.v2.data.Data;
import org.stocksrin.v2.price.retrival.service.OptionChainPriceRetrivalService;

@Service
public class OptionChainDailyDownloader extends TimerTask {

	private static final Logger log = LoggerFactory.getLogger(OptionChainDailyDownloader.class);

	@Override
	public void run() {
		// log.info("******* OptionChainDownloader Started **********");
		if (!DateUtils.isWeekEndDay() && !NSEHolidayUtils.isHoliday()) {
			new Thread(() -> downloadFile(AppConstant.STOCKSRIN_OPTION_CHAIN_NIFTY_EOD, "NIFTY")).start();

			new Thread(() -> downloadFile(AppConstant.STOCKSRIN_OPTION_CHAIN_BNF_EOD, "BANKNIFTY")).start();
		}
	}

	private void downloadFile(String path, String symbole) {
		System.out.println(Data.shortedExpiry);
		try {
			Map<String, OptionModel> data = null;
			if (!Data.shortedExpiry.isEmpty()) {
				if (symbole.equals("NIFTY")) {
					data = Data.getNiftyData();
				} else if (symbole.equals("BANKNIFTY")) {
					data = Data.getBNFData();
				}
				for (String expiry : Data.shortedExpiry) {
					// download only current expiry
					if(expiry.contains("2020")) {
						OptionModel optionModles = data.get(expiry);
						downloadData(optionModles, path, expiry);
					}
					

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private OptionModel downloadData(OptionModel optionModles, String path, String expiry) {
		try {
			if (optionModles != null) {
				String date = optionModles.getTimestamp().split(" ")[0];
				boolean makedirstatus = FileUtils.makeDir(path + expiry);
				// String todayDir = path + expiry + File.separator + date;
				// boolean makedirstatus2 = FileUtils.makeDir(todayDir);

				if (makedirstatus) {
					log.info("New Expiry Start " + expiry);
				}

				// OptionModles optionModles = niftyClient.getOptionModel(firstExpiry);
				// String filename = date + "_" + count;
				String optionFileDir = path + expiry + File.separator + date + ".json";

				boolean isFileExist = FileUtils.isFileExits(optionFileDir);
				log.info(optionFileDir + ", isFileExist : " + isFileExist);

				if (!isFileExist) {
					FileUtils.writeDataAsJson(optionModles, optionFileDir);
					log.info("Downloaded " + optionFileDir);
				} else {
					log.info("already data downloaded , " + optionFileDir);
				}

				return optionModles;
			} else {
				/*System.err.println("ERROR, Path :"  +path);
				System.err.println("Expiry, expiry :"  +expiry);
				SendEmail.sentMail("Option Not Chain Downloaded null data", "Expiry : " + expiry + "\n Path : " + path,
						"Live Data MS");*/
			}
		} catch (Exception e) {
			SendEmail.sentMail("Option Not Chain Downloaded", "Expiry : " + expiry + "\n Path : " + path,
					"Live Data MS");
			e.printStackTrace();
		}
		return optionModles;

	}

	public static void main(String[] args) {

		OptionChainPriceRetrivalService obj = new OptionChainPriceRetrivalService(true);
		obj.update("NIFTY");
		obj.update("BANKNIFTY");

		OptionChainDailyDownloader d = new OptionChainDailyDownloader();
		d.run();

	}
}