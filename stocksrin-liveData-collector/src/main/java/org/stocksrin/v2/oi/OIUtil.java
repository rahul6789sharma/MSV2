package org.stocksrin.v2.oi;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.stocksrin.common.utils.AppConstant;
import org.stocksrin.common.utils.CommonUtils;
import org.stocksrin.common.utils.DateUtils;
import org.stocksrin.common.utils.FileUtils;
import org.stocksrin.v2.common.model.oi.OIData;
import org.stocksrin.v2.common.model.oi.ParticapentOIData;
import org.stocksrin.v2.common.model.oi.ParticipantOIModle;
import org.stocksrin.v2.common.model.oi.ParticipantType;

public class OIUtil {

	public static void main(String[] args) {
		List<ParticapentOIData> result = load();
		result.forEach(i -> System.out.println(i));
	}

	public static List<ParticapentOIData> load() {
		List<ParticapentOIData> result = new ArrayList<>();
		try {

			String previuso2 = DateUtils.getPreviousMonth(-2, "MMMyyyy");

			String previuso = DateUtils.getPreviousMonth(-1, "MMMyyyy");
			String month = DateUtils.dateToString(new Date(), "MMMyyyy");

			String currentMonthDir = AppConstant.FO_OI_DIR + month;
			String previusoMonthDir = AppConstant.FO_OI_DIR + previuso;

			String previusoMonthDir2 = AppConstant.FO_OI_DIR + previuso2;

			if (!OIData.data.containsKey(previuso2)) {
				List<String> lst3 = FileUtils.listFilesForFolder(previusoMonthDir2);
				loadData(previuso2, lst3);
			}

			if (!OIData.data.containsKey(previuso)) {
				List<String> lst2 = FileUtils.listFilesForFolder(previusoMonthDir);
				loadData(previuso, lst2);
			}

			if (!OIData.data.containsKey(month)) {
				List<String> lst = FileUtils.listFilesForFolder(currentMonthDir);
				loadData(month, lst);
			} else {
				List<ParticapentOIData> lst2 = OIData.data.get(month);
				List<String> lst = FileUtils.listFilesForFolder(currentMonthDir);
				if (lst2.size() != lst.size()) {
					loadData(month, lst);
				}

			}

			result.addAll(OIData.data.get(previuso2));
			result.addAll(OIData.data.get(previuso));
			result.addAll(OIData.data.get(month));

			if (OIData.data.keySet().size() > 3) {
				String lastMonth = DateUtils.getPreviousMonth(-3, "MMMyyyy");
				OIData.data.remove(lastMonth);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void loadData(String month, List<String> files) throws Exception {
		String dir = AppConstant.FO_OI_DIR + month;
		List<ParticapentOIData> lst = new ArrayList<>();
		for (String file : files) {
			lst.add(collectAllDateForDay(dir + File.separator + file));
		}
		OIData.data.put(month, lst);

	}

	public static ParticapentOIData collectAllDateForDay(String file) throws Exception {
		String date3 = getDatefromFileName(file);
		ParticapentOIData result = new ParticapentOIData();
		List<ParticipantOIModle> participantOIModles = new ArrayList<>(4);
		List<String[]> data = CommonUtils.getCSVData(file);
		for (String[] strings : data) {
			ParticipantOIModle participantOIModle = getData(strings, date3);
			if (participantOIModle != null) {
				participantOIModles.add(participantOIModle);
			}
		}
		result.setDate(date3);
		result.setData(participantOIModles);
		return result;
	}

	public static ParticipantOIModle getData(String[] data, String date) throws Exception {

		if (data.length != 15) {
			throw new Exception(
					"ERROR! ParticapentFnoOITask csv broke, data fields count should be 15,  but now it is: "
							+ data.length);
		}

		if (data[0].equalsIgnoreCase("Client Type")) {
			if (!data[5].equalsIgnoreCase("Option Index Call Long")
					&& !data[6].equalsIgnoreCase("Option Index Put Long")
					&& !data[7].equalsIgnoreCase("Option Index Call Short")
					&& !data[8].equalsIgnoreCase("Option Index Put Short")) {
				throw new Exception("ERROR! ParticapentFnoOITask csv Broke ");
			}
		} else if (!data[0].equalsIgnoreCase("TOTAL")) {
			return getParticipantOIModle(date, ParticipantType.valueOf(data[0].toUpperCase()), data);

		}
		return null;

	}

	private static String getDatefromFileName(String fileName) throws Exception {

		String[] a = fileName.split("fao_participant_oi_");

		String date = a[1].substring(0, 8);
		String month = date.substring(2, 4);

		String m = DateUtils.getMonth(month);
		// 20May2018
		return date.substring(0, 2) + m + date.substring(4, date.length());

	}

	private static ParticipantOIModle getParticipantOIModle(String date, ParticipantType participantType, String[] data)
			throws Exception {
		ParticipantOIModle participantOIModle = new ParticipantOIModle(date, participantType);
		try {

			participantOIModle.setFutureIndexLong(Integer.parseInt(data[1]));
			participantOIModle.setFutureIndexShort(Integer.parseInt(data[2]));

			participantOIModle.setFutureStockLong(Integer.parseInt(data[3]));
			participantOIModle.setFutureStockShort(Integer.parseInt(data[4]));

			participantOIModle.setOptionIndexCallLong(Integer.parseInt(data[5]));
			participantOIModle.setOptionIndexPutLong(Integer.parseInt(data[6]));
			participantOIModle.setOptionIndexCallShort(Integer.parseInt(data[7]));
			participantOIModle.setOptionIndexPutShort(Integer.parseInt(data[8]));

			participantOIModle.setOptionStockCallLong(Integer.parseInt(data[9]));
			participantOIModle.setOptionStockPutLong(Integer.parseInt(data[10]));
			participantOIModle.setOptionStockCallShort(Integer.parseInt(data[11]));
			participantOIModle.setOptionStockPutShort(Integer.parseInt(data[12]));

			participantOIModle.setTotalLongContracts(Integer.parseInt(data[13]));
			participantOIModle.setTotalShortContracts(Integer.parseInt(data[14]));
		} catch (Exception e) {
			throw new Exception("ERROR! ParticapentFnoOITask csv Broke! can not parse csv" + e.getMessage());
		}
		return participantOIModle;
	}
}
