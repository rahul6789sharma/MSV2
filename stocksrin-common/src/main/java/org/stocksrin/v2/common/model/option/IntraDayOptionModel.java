package org.stocksrin.v2.common.model.option;

import java.util.LinkedHashMap;
import java.util.Map;

public class IntraDayOptionModel extends OptionModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IntraDayOptionModel(String symbole) {
		super.symbole = symbole;
	}

	private String intradayData;
	// three highest combined OI
	// strike and combined OI
	private Map<Integer, Integer> maxOIs = new LinkedHashMap<>();

	private Map<Integer, Integer> maxCEOI = new LinkedHashMap<>();

	private Map<Integer, Integer> maxPEOI = new LinkedHashMap<>();

	private Map<Integer, Integer> maxChangeCEOI = new LinkedHashMap<>();

	private Map<Integer, Integer> maxChangePEOI = new LinkedHashMap<>();

	public String getIntradayData() {
		return intradayData;
	}

	public void setIntradayData(String intradayData) {
		this.intradayData = intradayData;
	}

	public Map<Integer, Integer> getMaxOIs() {
		return maxOIs;
	}

	public void setMaxOIs(Map<Integer, Integer> maxOIs) {
		this.maxOIs = maxOIs;
	}

	public Map<Integer, Integer> getMaxCEOI() {
		return maxCEOI;
	}

	public void setMaxCEOI(Map<Integer, Integer> maxCEOI) {
		this.maxCEOI = maxCEOI;
	}

	public Map<Integer, Integer> getMaxPEOI() {
		return maxPEOI;
	}

	public void setMaxPEOI(Map<Integer, Integer> maxPEOI) {
		this.maxPEOI = maxPEOI;
	}

	public Map<Integer, Integer> getMaxChangeCEOI() {
		return maxChangeCEOI;
	}

	public void setMaxChangeCEOI(Map<Integer, Integer> maxChangeCEOI) {
		this.maxChangeCEOI = maxChangeCEOI;
	}

	public Map<Integer, Integer> getMaxChangePEOI() {
		return maxChangePEOI;
	}

	public void setMaxChangePEOI(Map<Integer, Integer> maxChangePEOI) {
		this.maxChangePEOI = maxChangePEOI;
	}

}
