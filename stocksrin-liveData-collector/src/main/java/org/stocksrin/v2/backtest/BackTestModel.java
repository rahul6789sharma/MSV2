package org.stocksrin.v2.backtest;

import java.util.ArrayList;
import java.util.List;

public class BackTestModel {

	String expiry;
	String date;
	String time;
	double underlyingPice;

	Entity longCE;
	Entity sortCE;

	Entity longPE;
	Entity sortPE;

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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getUnderlyingPice() {
		return underlyingPice;
	}

	public void setUnderlyingPice(double underlyingPice) {
		this.underlyingPice = underlyingPice;
	}

	public Entity getLongCE() {
		return longCE;
	}

	public void setLongCE(Entity longCE) {
		this.longCE = longCE;
	}

	public Entity getSortCE() {
		return sortCE;
	}

	public void setSortCE(Entity sortCE) {
		this.sortCE = sortCE;
	}

	public Entity getLongPE() {
		return longPE;
	}

	public void setLongPE(Entity longPE) {
		this.longPE = longPE;
	}

	public Entity getSortPE() {
		return sortPE;
	}

	public void setSortPE(Entity sortPE) {
		this.sortPE = sortPE;
	}

}
