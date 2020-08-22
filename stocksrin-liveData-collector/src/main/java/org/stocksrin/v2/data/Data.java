package org.stocksrin.v2.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.stocksrin.common.model.option.OptionType;
import org.stocksrin.v2.common.model.future.ArbtriageEntity;
import org.stocksrin.v2.common.model.future.EntityABR;
import org.stocksrin.v2.common.model.future.Stock;
import org.stocksrin.v2.common.model.option.CE;
import org.stocksrin.v2.common.model.option.Datum;
import org.stocksrin.v2.common.model.option.IntraDayOptionModel;
import org.stocksrin.v2.common.model.option.OptionModel;
import org.stocksrin.v2.common.model.option.PE;
import org.stocksrin.v2.common.model.option.SSEntity;

public class Data {

	public static Map<String, EntityABR> currentMonthABR = new ConcurrentHashMap<>();
	public static Map<String, EntityABR> nextMonthABR = new ConcurrentHashMap<>();

	public static Map<ArbtriageEntity, Stock> result = new ConcurrentHashMap<>();

	public static List<String> shortedExpiry = new ArrayList<>();

	// all expiry data
	private static Map<String, OptionModel> niftyData = new ConcurrentHashMap<>();
	public static List<IntraDayOptionModel> niftyIntraDay = new ArrayList<>();

	// all expiry data
	private static Map<String, OptionModel> bnfData = new ConcurrentHashMap<>();
	// public static List<OptionModel> bnfIntraDay = new ArrayList<>();

	//
	public static List<SSEntity> ssEntitys = new ArrayList<>();

	public static synchronized Map<String, OptionModel> getNiftyData() {
		return niftyData;
	}

	public static synchronized Map<String, OptionModel> getBNFData() {
		return bnfData;
	}

	public static synchronized OptionModel getNiftyData(String expiry) {
		return niftyData.get(expiry);
	}

	public static synchronized OptionModel getBNFData(String expiry) {
		return bnfData.get(expiry);
	}

	public static synchronized void updateNiftyData(Map<String, OptionModel> data) {
		niftyData = data;
	}

	public static synchronized void updateBNFData(Map<String, OptionModel> data) {
		bnfData = data;
	}

	public static synchronized void updateExpiry(List<String> data) {
		shortedExpiry = data;
	}

	// intraDay Data
	public static double getLtp(Map<String, OptionModel> Indexdata, Integer strike, OptionType optionType,
			String expiry) {
		OptionModel data = Indexdata.get(expiry);
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

	public static double getNFSpot() {
		if (!shortedExpiry.isEmpty()) {
			OptionModel data = niftyData.get(shortedExpiry.get(0));
			if (data != null) {
				return data.getUnderlyingValue();
			}
		}
		return 0;
	}

	public static double getBNFSpot() {
		if (!shortedExpiry.isEmpty()) {
			OptionModel data = bnfData.get(shortedExpiry.get(0));
			if (data != null) {
				return data.getUnderlyingValue();
			}
		}
		return 0;
	}

	public static String getNiftyLastUpdatedTimestamp() {
		if (!shortedExpiry.isEmpty()) {
			OptionModel data = niftyData.get(shortedExpiry.get(0));
			if (data != null) {
				return data.getTimestamp();
			}
		}
		return "NaN";
	}

	public static String getBNFLastUpdatedTimestamp() {
		if (!shortedExpiry.isEmpty()) {
			OptionModel data = bnfData.get(shortedExpiry.get(0));
			if (data != null) {
				return data.getTimestamp();
			}
		}
		return "NaN";
	}

	public static void clean() {
		shortedExpiry.clear();
		niftyData.clear();
		niftyIntraDay.clear();
		bnfData.clear();
	}

	public List<SSEntity> getSsEntitys() {
		return ssEntitys;
	}

	public void setSsEntitys(List<SSEntity> ssEntitys) {
		this.ssEntitys = ssEntitys;
	}

}