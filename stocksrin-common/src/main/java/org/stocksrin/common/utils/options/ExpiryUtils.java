package org.stocksrin.common.utils.options;

import org.stocksrin.common.model.InstrumentType;
import org.stocksrin.common.model.OptionChainOIData;
import org.stocksrin.common.model.option.OptionModle;
import org.stocksrin.common.model.option.OptionModles;
import org.stocksrin.common.utils.DateUtils;
import org.stocksrin.common.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;

public class ExpiryUtils {

	
	public static String getNextExpiry(SortedSet<String> shortedExpiry) throws Exception {

		List<String> expiryList = new ArrayList<>(shortedExpiry);
		return expiryList.get(1);

	}

	public static boolean isMonthlyExpiry(List<String> expires) {
		String currentExpiryMonth = expires.get(0).replaceAll("[^A-Za-z]", "");
		return !expires.get(1).toUpperCase().contains(currentExpiryMonth);
	}

	public static String getCurrentMonthExpiry(List<String> expiries) {
		if (!expiries.isEmpty()) {
			String currentExpiryMonth = expiries.get(0).replaceAll("[^A-Za-z]", "");
			long count = expiries.stream().filter(i -> i.contains(currentExpiryMonth)).count();
			return expiries.get((int) count - 1);
		} else {
			return null;
		}
	}

	public static String getNextMonthlyExpiry(List<String> expires) {

		// String currentExpiryMonth = expires.get(0).replaceAll("[^A-Za-z]", "");
		String nextMonth = FileUtils.nextMonth();
		int index = 0;
		for (String string : expires) {
			if (string.toUpperCase().contains(nextMonth.toUpperCase())) {
				index++;
			}
		}
		return expires.get(index);
	}

	public static String getNextWeekExpiry(List<String> expires) {
		return expires.get(1);
	}

	public static boolean isTodayExpiry(SortedSet<String> allExpiry) throws Exception {
		List<String> expires = new ArrayList<>(allExpiry);
		String todayDate = DateUtils.dateToString(new Date(), "dd-MMM-yyyy");
		String currentExpiry = expires.get(0);

		if (todayDate.equalsIgnoreCase(currentExpiry)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isTodayExpiry(List<String> expires) throws Exception {
		String todayDate = DateUtils.dateToString(new Date(), "dd-MMM-yyyy");
		String currentExpiry = expires.get(0);

		if (todayDate.equalsIgnoreCase(currentExpiry)) {
			return true;
		} else {
			return false;
		}
	}



	// need to take fix atmStrike in morning
	private static void premiumDecay(OptionModles optionModles, double atmStrike, OptionChainOIData optionChainOIData,
			InstrumentType instrumentType) {
		List<OptionModle> lst = optionModles.getOptionModle();
		double callStrikeLimit = atmStrike + 500;
		double putStrikeLimit = atmStrike - 500;
		if (instrumentType.equals(InstrumentType.BNF)) {
			callStrikeLimit = atmStrike + 1000;
			putStrikeLimit = atmStrike - 1000;
		}

		int cecount = 0;
		double cePremiumAvg = 0.0;
		for (OptionModle optionModle : lst) {
			if (atmStrike <= optionModle.getStrike_price() && callStrikeLimit >= optionModle.getStrike_price()) {
				// System.out.println("call " + optionModle.getStrike_price());
				if (optionModle.getC_ltp() != null) {

					cePremiumAvg = cePremiumAvg + optionModle.getC_ltp();
					cecount++;
				}
			}
		}

		int pecount = 0;
		double pePremiumAvg = 0.0;
		for (OptionModle optionModle : lst) {
			if (atmStrike >= optionModle.getStrike_price() && putStrikeLimit <= optionModle.getStrike_price()) {
				// System.out.println("put " + optionModle.getStrike_price());
				if (optionModle.getP_ltp() != null) {

					pePremiumAvg = pePremiumAvg + optionModle.getP_ltp();
					pecount++;
				}
			}
		}
		optionChainOIData.setAvg_CE_OTM_premium((int) cePremiumAvg / cecount);
		optionChainOIData.setAvg_PE_OTM_premium((int) pePremiumAvg / pecount);
	}

	private static String columne = "date,expiry,symbole,spot,iv,total_CE_OI,total_PE_OI,total_CE_OI_Change,total_PE_OI_change,higest_CE_OI_1,higest_CE_OI_2,higest_PE_OI_1,higest_PE_OI_2,higest_CE_OI_1_change,higest_CE_OI_2_change,higest_PE_OI_1_change,higest_PE_OI_2_change,avg_CE_OTM_premium,avg_PE_OTM_premium";

	public static void weeklyDirCreate(String path, List<String> expires) {

		System.out.println("Creating Next Weekly Dir");
		FileUtils.makeDir(path + ExpiryUtils.getNextWeekExpiry(expires));
		String file = path + ExpiryUtils.getNextWeekExpiry(expires) + File.separator + "Weekly.csv";
		if (FileUtils.makeFile(file)) {
			FileUtils.appendData(columne, file);
		}
	}

	public static void monthlyDirCreate(String path, List<String> expires) {
		System.out.println("Creating Monthly");
		FileUtils.makeDir(path + ExpiryUtils.getNextMonthlyExpiry(expires));
		String file = path + ExpiryUtils.getNextMonthlyExpiry(expires) + File.separator + "Monthly.csv";
		if (FileUtils.makeFile(file)) {
			FileUtils.appendData(columne, file);
		}
	}

}
