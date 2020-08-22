package org.stocksrin.v2.pnl;

import org.stocksrin.common.model.option.OptionType;
import org.stocksrin.common.model.option.TradeType;

public class TradeModle {

	private OptionType optionType; // call pr put
	private TradeType tradeType; // short or long

	private double strike;
	private double premium;
	private int qnty;

	public TradeModle(OptionType optionType, TradeType tradeType, double strike, double premium, int qnty) {
		super();
		this.optionType = optionType;
		this.tradeType = tradeType;
		this.strike = strike;
		this.premium = premium;
		this.qnty = qnty;
	}

	public OptionType getOptionType() {
		return optionType;
	}

	public void setOptionType(OptionType optionType) {
		this.optionType = optionType;
	}

	public TradeType getTradeType() {
		return tradeType;
	}

	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}

	public double getStrike() {
		return strike;
	}

	public void setStrike(double strike) {
		this.strike = strike;
	}

	public double getPremium() {
		return premium;
	}

	public void setPremium(double premium) {
		this.premium = premium;
	}

	public int getQnty() {
		return qnty;
	}

	public void setQnty(int qnty) {
		this.qnty = qnty;
	}

}
