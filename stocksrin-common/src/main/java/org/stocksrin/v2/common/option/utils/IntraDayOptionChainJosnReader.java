package org.stocksrin.v2.common.option.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.stocksrin.common.model.option.OptionType;
import org.stocksrin.common.utils.AppConstant;
import org.stocksrin.common.utils.FileUtils;
import org.stocksrin.v2.common.model.option.CE;
import org.stocksrin.v2.common.model.option.Datum;
import org.stocksrin.v2.common.model.option.OptionModel;
import org.stocksrin.v2.common.model.option.PE;

public class IntraDayOptionChainJosnReader {

	public static void main(String[] args) throws Exception {
		List<OptionModel> lst = getNiftyOptionChain("30-Jan-2020", "30-Jan-2020", true);
		for (OptionModel optionModel : lst) {

			// System.out.println(optionModel.getUnderlyingValue());
			// System.out.println(optionModel.getTimestamp());
		}
	}

	public static List<OptionModel> getNiftyOptionChain(String expiry, String date, boolean isloadFull)
			throws Exception {

		List<OptionModel> result = new ArrayList<>();

		String path = AppConstant.STOCKSRIN_OPTION_DATA_DIR_NIFTY_INTRDAY2_10MIN + expiry + File.separator + date;
		List<String> files = FileUtils.listFilesForFolder(path);
		if (files == null) {
			return null;
		}
		SortedSet<Integer> sorted = new TreeSet<>();
		for (String fileName : files) {
			Integer i = Integer.parseInt(fileName.split(".json")[0]);
			sorted.add(i);
		}

		for (Integer fileName : sorted) {
			OptionModel o = FileUtils.fromJson3(path + File.separator + fileName.toString() + ".json");
			Integer atmStrike = OptionUtils.getATMStrikeV2(o, 50);
			Integer lowerStrike = atmStrike - 700;
			Integer upper = atmStrike + 700;
			List<Datum> datums = o.getDatums();
			List<Datum> datumsResult = new ArrayList<>();
			if (isloadFull) {
				for (Datum d : datums) {
					datumsResult.add(d);
				}
			} else {
				for (Datum d : datums) {
					if (atmStrike >= d.getStrikePrice()) {
						if (d.getPE() != null && d.getPE().getLastPrice() > 5) {
							datumsResult.add(d);
						}

					} else {
						if (d.getCE() != null && d.getCE().getLastPrice() > 5) {
							datumsResult.add(d);
						}
					}
					/*
					 * if (d.getStrikePrice() > lowerStrike && d.getStrikePrice() < upper) {
					 * datumsResult.add(d); }
					 */
				}
			}

			o.getDatums().clear();
			o.getDatums().addAll(datumsResult);
			result.add(o);
		}
		return result;
	}

	public static double getOptionLtp(OptionModel data, Integer strike, OptionType optionType, String expiry) {
		double ltp = 0.0;
		if (data != null && expiry.equals(data.getExpiryDate())) {
			List<Datum> chain = data.getDatums();
			if (optionType.equals(OptionType.PUT)) {

				for (Datum datum : chain) {
					PE pe = datum.getPE();
					if (pe != null && pe.getStrikePrice().equals(strike)) {
						ltp = pe.getLastPrice();
					}
				}
			} else if (optionType.equals(OptionType.CALL)) {

				for (Datum datum : chain) {
					CE ce = datum.getCE();
					if (ce != null && ce.getStrikePrice().equals(strike)) {
						ltp = ce.getLastPrice();
					}
				}
			}
		}
		return ltp;
	}

	public static double getSpot(OptionModel data) {
		return data.getUnderlyingValue();
	}

	public static String getNiftyLastUpdatedTimestamp(OptionModel data) {
		return data.getTimestamp();
	}
}