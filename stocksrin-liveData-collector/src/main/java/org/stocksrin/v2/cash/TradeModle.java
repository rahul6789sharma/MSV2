package org.stocksrin.v2.cash;

public class TradeModle {

	private String symbol;

	private PriceInfo priceInfo;

	double stopLoss;
	double target;

	int investment;

	int profit;
	int loss;

	// after target/SL hit
	boolean active;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public PriceInfo getPriceInfo() {
		return priceInfo;
	}

	public void setPriceInfo(PriceInfo priceInfo) {
		this.priceInfo = priceInfo;
	}

	public double getStopLoss() {
		return stopLoss;
	}

	public void setStopLoss(double stopLoss) {
		this.stopLoss = stopLoss;
	}

	public double getTarget() {
		return target;
	}

	public void setTarget(double target) {
		this.target = target;
	}

	public int getInvestment() {
		return investment;
	}

	public void setInvestment(int investment) {
		this.investment = investment;
	}

	public int getProfit() {
		return profit;
	}

	public void setProfit(int profit) {
		this.profit = profit;
	}

	public int getLoss() {
		return loss;
	}

	public void setLoss(int loss) {
		this.loss = loss;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "TradeModle [symbol=" + symbol + "]";
	}

}
