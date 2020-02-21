package org.stocksrin.v2.common.model.future;

public class EntityABR {


	private Integer lotSize;
	private String symbol;
	private Double underlyingValue;
	private String expiryDate;
	private double futurePrice;

	private String companyName;
	private Integer arbatrage;
	private Double abrPercenatge;
	private Integer invAmount;
	private double diff;

	private double maxDiff;
	private double minDiff;
	private double maxArbPer;
	private double minArbPer;

	public EntityABR(String symbol, Integer lotSize) {
		super();
		this.lotSize = lotSize;
		this.symbol = symbol;
	}

	public Double getUnderlyingValue() {
		return underlyingValue;
	}

	public void setUnderlyingValue(Double underlyingValue) {
		this.underlyingValue = underlyingValue;
	}

	public double getFuturePrice() {
		return futurePrice;
	}

	public void setFuturePrice(double futurePrice) {
		this.futurePrice = futurePrice;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Integer getLotSize() {
		return lotSize;
	}

	public void setLotSize(Integer lotSize) {
		this.lotSize = lotSize;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getArbatrage() {
		return arbatrage;
	}

	public void setArbatrage(Integer arbatrage) {
		this.arbatrage = arbatrage;
	}

	public Double getAbrPercenatge() {
		return abrPercenatge;
	}

	public void setAbrPercenatge(Double abrPercenatge) {
		this.abrPercenatge = abrPercenatge;
	}

	public Integer getInvAmount() {
		return invAmount;
	}

	public void setInvAmount(Integer invAmount) {
		this.invAmount = invAmount;
	}

	public double getDiff() {
		return diff;
	}

	public void setDiff(double diff) {
		this.diff = diff;
	}

	public double getMaxDiff() {
		return maxDiff;
	}

	public void setMaxDiff(double maxDiff) {
		this.maxDiff = maxDiff;
	}

	public double getMinDiff() {
		return minDiff;
	}

	public void setMinDiff(double minDiff) {
		this.minDiff = minDiff;
	}

	public double getMaxArbPer() {
		return maxArbPer;
	}

	public void setMaxArbPer(double maxArbPer) {
		this.maxArbPer = maxArbPer;
	}

	public double getMinArbPer() {
		return minArbPer;
	}

	public void setMinArbPer(double minArbPer) {
		this.minArbPer = minArbPer;
	}

	@Override
	public String toString() {
		return "EntityABR [lotSize=" + lotSize + ", symbol=" + symbol + ", underlyingValue=" + underlyingValue + ", expiryDate=" + expiryDate + ", companyName=" + companyName + ", arbatrage="
				+ arbatrage + ", abrPercenatge=" + abrPercenatge + ", invAmount=" + invAmount + ", diff=" + diff + ", maxDiff=" + maxDiff + ", minDiff=" + minDiff + ", maxArbPer=" + maxArbPer
				+ ", minArbPer=" + minArbPer + "]";
	}
	
}
