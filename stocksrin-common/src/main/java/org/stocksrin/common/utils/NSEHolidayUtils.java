package org.stocksrin.common.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NSEHolidayUtils {
	private static List<String> holiday = new ArrayList<>();

	static {

		holiday.add("21-Feb-2020");
		holiday.add("10-Mar-2020");
		holiday.add("02-Apr-2020");
		holiday.add("06-Apr-2020");
		holiday.add("10-Apr-2020");
		holiday.add("14-Apr-2020");
		holiday.add("01-May-2020");
		holiday.add("25-May-2020");
		holiday.add("02-Oct-2020");
		holiday.add("16-Nov-2020");
		holiday.add("30-Nov-2020");
		holiday.add("25-Dec-2020");

	}

	public static boolean isHoliday() {
		if (AppConstant.TEST) {
			return false;
		}
		String formate = "dd-MMM-yyyy";
		try {
			String date = DateUtils.dateToString(new Date(), formate);
			return holiday.contains(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(isHoliday());
	}
}
