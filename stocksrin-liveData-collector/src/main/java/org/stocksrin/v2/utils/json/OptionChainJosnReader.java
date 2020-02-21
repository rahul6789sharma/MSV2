package org.stocksrin.v2.utils.json;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.stocksrin.common.utils.AppConstant;
import org.stocksrin.common.utils.FileUtils;
import org.stocksrin.v2.common.model.option.Datum;
import org.stocksrin.v2.common.model.option.OptionModel;
import org.stocksrin.v2.common.option.utils.OptionUtils;

public class OptionChainJosnReader {

	public static void main(String[] args) throws Exception {
		getNiftyOptionChain("30-Jan-2020", "30-Jan-2020");
	}

	public static List<OptionModel> getNiftyOptionChain(String expiry, String date) throws Exception {

		List<OptionModel> result = new ArrayList<>();

		String path = AppConstant.STOCKSRIN_OPTION_DATA_DIR_NIFTY_INTRDAY2_10MIN + expiry + File.separator + date;
		List<String> files = FileUtils.listFilesForFolder(path);
		SortedSet<Integer> sorted = new TreeSet<>();
		for (String fileName : files) {
			Integer i=Integer.parseInt(fileName.split(".json")[0]);
			sorted.add(i);
		}		
		if (files == null) {
			return null;
		}
		for (Integer fileName : sorted) {
			OptionModel o = FileUtils.fromJson3(path + File.separator + fileName.toString()+".json");
			Integer atmStrike = OptionUtils.getATMStrikeV2(o, 50);
			Integer lowerStrike = atmStrike - 500;
			Integer upper = atmStrike + 500;
			List<Datum> datums = o.getDatums();
			List<Datum> datumsResult = new ArrayList<>();
			for (Datum d : datums) {
				if (d.getStrikePrice() > lowerStrike && d.getStrikePrice() < upper) {
					datumsResult.add(d);
				}
			}
			o.getDatums().clear();
			o.getDatums().addAll(datumsResult);
			result.add(o);
		}
		return result;
	}
}