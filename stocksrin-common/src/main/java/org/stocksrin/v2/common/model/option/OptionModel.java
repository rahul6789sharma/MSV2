package org.stocksrin.v2.common.model.option;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// for every expire one option model will be created 
public class OptionModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String symbole;
	private String expiryDate;
	private String timestamp;
	private double underlyingValue;

	// expiry and its future
	private Map<String, Double> futureValue;

	private int dte;
	private String day;
	private List<Datum> datums = new ArrayList<>();

	public OptionModel() {
	}

	public OptionModel(String symbole) {
		this.symbole = symbole;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public int getDte() {
		return dte;
	}

	public void setDte(int dte) {
		this.dte = dte;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public double getUnderlyingValue() {
		return underlyingValue;
	}

	public void setUnderlyingValue(double underlyingValue) {
		this.underlyingValue = underlyingValue;
	}

	public List<Datum> getDatums() {
		return datums;
	}

	public void setDatums(List<Datum> datums) {
		this.datums = datums;
	}

	public String getSymbole() {
		return symbole;
	}

	public Map<String, Double> getFutureValue() {
		return futureValue;
	}

	public void setFutureValue(Map<String, Double> futureValue) {
		this.futureValue = futureValue;
	}

	public void setSymbole(String symbole) {
		this.symbole = symbole;
	}

	@Override
	public String toString() {
		return "OptionModel [symbole=" + symbole + ", expiryDate=" + expiryDate + ", timestamp=" + timestamp
				+ ", underlyingValue=" + underlyingValue + ", dte=" + dte + ", day=" + day + ", datums=" + datums + "]";
	}

}
