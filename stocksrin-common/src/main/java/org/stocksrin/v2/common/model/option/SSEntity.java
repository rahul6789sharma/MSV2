package org.stocksrin.v2.common.model.option;

import java.io.Serializable;

public class SSEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String expiry;
	String date;
	Integer ceStrike;
	double cePrice;
	Integer peStrike;
	double pePrice;
	double underlyingPice;
	String timeStamp;
	double total;

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getCeStrike() {
		return ceStrike;
	}

	public void setCeStrike(Integer ceStrike) {
		this.ceStrike = ceStrike;
	}

	public double getCePrice() {
		return cePrice;
	}

	public void setCePrice(double cePrice) {
		this.cePrice = cePrice;
	}

	public Integer getPeStrike() {
		return peStrike;
	}

	public void setPeStrike(Integer peStrike) {
		this.peStrike = peStrike;
	}

	public double getPePrice() {
		return pePrice;
	}

	public void setPePrice(double pePrice) {
		this.pePrice = pePrice;
	}

	public double getUnderlyingPice() {
		return underlyingPice;
	}

	public void setUnderlyingPice(double underlyingPice) {
		this.underlyingPice = underlyingPice;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "SSEntity [expiry=" + expiry + ", date=" + date + ", ceStrike=" + ceStrike + ", cePrice=" + cePrice
				+ ", peStrike=" + peStrike + ", pePrice=" + pePrice + ", underlyingPice=" + underlyingPice
				+ ", timeStamp=" + timeStamp + ", total=" + total + "]";
	}

}