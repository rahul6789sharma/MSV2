package org.stocksrin.v2.backtest;

public class Entity {

	Integer strike;
	double price;
	int lot;

	
	public Entity(Integer strike, double price, int lot) {
		super();
		this.strike = strike;
		this.price = price;
		this.lot = lot;
	}

	public int getLot() {
		return lot;
	}

	public void setLot(int lot) {
		this.lot = lot;
	}

	public Integer getStrike() {
		return strike;
	}

	public void setStrike(Integer strike) {
		this.strike = strike;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}