package org.stocksrin.v2.backtest;

public class StrikePrice {

	String expiry;
	String date;
	String time;
	
	Integer ceStrike;
	double cePrice;
	
	Integer peStrike;
	double pePrice;
	
	double underlyingPice;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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

	@Override
	public String toString() {
		return "StrikePrice [expiry=" + expiry + ", date=" + date + ", time=" + time + ", ceStrike=" + ceStrike
				+ ", cePrice=" + cePrice + ", peStrike=" + peStrike + ", pePrice=" + pePrice + ", underlyingPice="
				+ underlyingPice + "]";
	}

}