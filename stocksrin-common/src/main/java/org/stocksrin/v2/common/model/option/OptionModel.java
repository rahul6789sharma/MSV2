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
	protected String symbol;
	private String expiryDate;
	private String timestamp;
	private double underlyingValue;


	// new field
	private Integer maxPain;

	// expiry and its future
	private Map<String, Double> futureValue;

	private int dte;
	private String day;
	private List<Datum> datums = new ArrayList<>();

	public OptionModel() {
	}

	public Integer getMaxPain() {
		return maxPain;
	}

	public void setMaxPain(Integer maxPain) {
		this.maxPain = maxPain;
	}

	public OptionModel(String symbol) {
		this.symbol = symbol;
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

	public String getSymbol() {
		return symbol;
	}

	public Map<String, Double> getFutureValue() {
		return futureValue;
	}

	public void setFutureValue(Map<String, Double> futureValue) {
		this.futureValue = futureValue;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return "OptionModel [symbole=" + symbol + ", expiryDate=" + expiryDate + ", timestamp=" + timestamp
				+ ", underlyingValue=" + underlyingValue + ", dte=" + dte + ", day=" + day + ", datums=" + datums + "]";
	}

}
