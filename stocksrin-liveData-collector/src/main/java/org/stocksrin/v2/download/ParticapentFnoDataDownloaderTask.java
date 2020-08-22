package org.stocksrin.v2.download;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stocksrin.common.utils.AppConstant;
import org.stocksrin.common.utils.DateUtils;
import org.stocksrin.common.utils.FileUtils;
import org.stocksrin.common.utils.NSEHolidayUtils;
import org.stocksrin.email.SendEmail;

public class ParticapentFnoDataDownloaderTask extends TimerTask {

	// need to trst on holy day 0kb files being downloanded when market is not
	// open
	private static final Logger log = LoggerFactory.getLogger(ParticapentFnoDataDownloaderTask.class);

	@Override
	public void run() {
		try {
			if (!DateUtils.isWeekEndDay() && !NSEHolidayUtils.isHoliday()) {
				String file = download();
				// Util.collectAllDateForDay(file);
				log.info("ParticapentFnoDataDownloaded : " + file);
				SendEmail.sentMail("FNO OI All Particapent Data SUCCESS", "SUCCESS file " + file, "Data-Downloader");
			}
		} catch (Exception e) {
			SendEmail.sentMail("CRITICAL! FO OI All Particapent Data", "ERROR " + e.getMessage(), "Data-Downloader");
			e.printStackTrace();
		}
	}

	// return file dir
	private static String download() throws Exception {

		String date = DateUtils.dateToString(new Date(), "ddMMyyyy");
		String fileName = "fao_participant_oi_" + date + ".csv";
		String url = AppConstant.NSE_FO_OI_ALLPARTICAPENT + fileName;
		// String
		// url="https://www.nseindia.com/content/nsccl/fao_participant_oi_07092018.csv";
		log.info("url : " + url);
		String date2 = DateUtils.dateToString(new Date(), "MMMyyyy");
		String dir = AppConstant.FO_OI_DIR + date2;

		boolean status = FileUtils.makeDir(dir);

		if (status) {
			log.info("New Dir is create for Month " + dir);
		}

		String file = dir + File.separator + fileName;

		if (!FileUtils.isFileExits(file)) {
			FileUtils.downloadFile(url, file);
			return file;

		} else {
			log.info("Already downloaded " + file);
		}
		return file;
	}

	private static String download(String date) throws Exception {

		String fileName = "fao_participant_oi_" + date + ".csv";
		String url = AppConstant.NSE_FO_OI_ALLPARTICAPENT + fileName;
		// String
		// url="https://www.nseindia.com/content/nsccl/fao_participant_oi_07092018.csv";
		log.info("url : " + url);
		String date2 = DateUtils.dateToString(new Date(), "MMMyyyy");
		String dir = AppConstant.FO_OI_DIR + date2;

		boolean status = FileUtils.makeDir(dir);

		if (status) {
			log.info("New Dir is create for Month " + dir);
		}

		String file = dir + File.separator + fileName;

		if (!FileUtils.isFileExits(file)) {

			FileUtils.downloadFile(url, file);

			return file;

		} else {
			log.info("Already downloaded " + file);
		}

		return file;
	}

	public static void main(String[] args) throws Exception {
		List<String> notDownloaded = new ArrayList<>();
		String last = "042020";
		for (Integer i = 1; i <= 9; i++) {
			String da = "0" + i.toString();
			System.out.println(da + last);
			try {
				download(da + last);
			} catch (Exception e) {
				notDownloaded.add(da + last);
				e.printStackTrace();
			}

		}
		for (Integer i = 10; i <= 31; i++) {
			String da = i.toString();
			System.out.println(da + last);
			try {
				download(da + last);
			} catch (Exception e) {
				notDownloaded.add(da + last);
				e.printStackTrace();
			}
		}
		notDownloaded.forEach(i -> System.out.println(i));
	}

	public static String getDayFromDate(Date date) throws Exception {
		SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy");
		return format1.format(date);
	}

}
