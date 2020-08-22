
package org.stocksrin.v2.cash;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "lastPrice", "change", "pChange", "previousClose", "open", "close", "vwap", "lowerCP", "upperCP",
		"pPriceBand", "basePrice", "intraDayHighLow", "weekHighLow" })
public class PriceInfo {

	@JsonProperty("lastPrice")
	private Double lastPrice;

	@JsonProperty("change")
	private Double change;

	@JsonProperty("pChange")
	private Double pChange;

	@JsonProperty("previousClose")
	private Double previousClose;

	@JsonProperty("open")
	private Double open;

	@JsonProperty("close")
	private Double close;

	@JsonProperty("vwap")
	private Double vwap;

	@JsonProperty("lowerCP")
	private String lowerCP;

	@JsonProperty("upperCP")
	private String upperCP;

	@JsonProperty("pPriceBand")
	private String pPriceBand;

	@JsonProperty("basePrice")
	private Integer basePrice;

	@JsonProperty("intraDayHighLow")
	private IntraDayHighLow intraDayHighLow;

	@JsonProperty("weekHighLow")
	private WeekHighLow weekHighLow;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("lastPrice")
	public Double getLastPrice() {
		return lastPrice;
	}

	@JsonProperty("lastPrice")
	public void setLastPrice(Double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public PriceInfo withLastPrice(Double lastPrice) {
		this.lastPrice = lastPrice;
		return this;
	}

	@JsonProperty("change")
	public Double getChange() {
		return change;
	}

	@JsonProperty("change")
	public void setChange(Double change) {
		this.change = change;
	}

	public PriceInfo withChange(Double change) {
		this.change = change;
		return this;
	}

	@JsonProperty("pChange")
	public Double getPChange() {
		return pChange;
	}

	@JsonProperty("pChange")
	public void setPChange(Double pChange) {
		this.pChange = pChange;
	}

	public PriceInfo withPChange(Double pChange) {
		this.pChange = pChange;
		return this;
	}

	@JsonProperty("previousClose")
	public Double getPreviousClose() {
		return previousClose;
	}

	@JsonProperty("previousClose")
	public void setPreviousClose(Double previousClose) {
		this.previousClose = previousClose;
	}

	public PriceInfo withPreviousClose(Double previousClose) {
		this.previousClose = previousClose;
		return this;
	}

	@JsonProperty("open")
	public Double getOpen() {
		return open;
	}

	@JsonProperty("open")
	public void setOpen(Double open) {
		this.open = open;
	}

	public PriceInfo withOpen(Double open) {
		this.open = open;
		return this;
	}

	@JsonProperty("close")
	public Double getClose() {
		return close;
	}

	@JsonProperty("close")
	public void setClose(Double close) {
		this.close = close;
	}

	public PriceInfo withClose(Double close) {
		this.close = close;
		return this;
	}

	@JsonProperty("vwap")
	public Double getVwap() {
		return vwap;
	}

	@JsonProperty("vwap")
	public void setVwap(Double vwap) {
		this.vwap = vwap;
	}

	public PriceInfo withVwap(Double vwap) {
		this.vwap = vwap;
		return this;
	}

	@JsonProperty("lowerCP")
	public String getLowerCP() {
		return lowerCP;
	}

	@JsonProperty("lowerCP")
	public void setLowerCP(String lowerCP) {
		this.lowerCP = lowerCP;
	}

	public PriceInfo withLowerCP(String lowerCP) {
		this.lowerCP = lowerCP;
		return this;
	}

	@JsonProperty("upperCP")
	public String getUpperCP() {
		return upperCP;
	}

	@JsonProperty("upperCP")
	public void setUpperCP(String upperCP) {
		this.upperCP = upperCP;
	}

	public PriceInfo withUpperCP(String upperCP) {
		this.upperCP = upperCP;
		return this;
	}

	@JsonProperty("pPriceBand")
	public String getPPriceBand() {
		return pPriceBand;
	}

	@JsonProperty("pPriceBand")
	public void setPPriceBand(String pPriceBand) {
		this.pPriceBand = pPriceBand;
	}

	public PriceInfo withPPriceBand(String pPriceBand) {
		this.pPriceBand = pPriceBand;
		return this;
	}

	@JsonProperty("basePrice")
	public Integer getBasePrice() {
		return basePrice;
	}

	@JsonProperty("basePrice")
	public void setBasePrice(Integer basePrice) {
		this.basePrice = basePrice;
	}

	public PriceInfo withBasePrice(Integer basePrice) {
		this.basePrice = basePrice;
		return this;
	}

	@JsonProperty("intraDayHighLow")
	public IntraDayHighLow getIntraDayHighLow() {
		return intraDayHighLow;
	}

	@JsonProperty("intraDayHighLow")
	public void setIntraDayHighLow(IntraDayHighLow intraDayHighLow) {
		this.intraDayHighLow = intraDayHighLow;
	}

	public PriceInfo withIntraDayHighLow(IntraDayHighLow intraDayHighLow) {
		this.intraDayHighLow = intraDayHighLow;
		return this;
	}

	@JsonProperty("weekHighLow")
	public WeekHighLow getWeekHighLow() {
		return weekHighLow;
	}

	@JsonProperty("weekHighLow")
	public void setWeekHighLow(WeekHighLow weekHighLow) {
		this.weekHighLow = weekHighLow;
	}

	public PriceInfo withWeekHighLow(WeekHighLow weekHighLow) {
		this.weekHighLow = weekHighLow;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public PriceInfo withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(lastPrice).append(change).append(pChange).append(previousClose).append(open)
				.append(close).append(vwap).append(lowerCP).append(upperCP).append(pPriceBand).append(basePrice)
				.append(intraDayHighLow).append(weekHighLow).append(additionalProperties).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof PriceInfo) == false) {
			return false;
		}
		PriceInfo rhs = ((PriceInfo) other);
		return new EqualsBuilder().append(lastPrice, rhs.lastPrice).append(change, rhs.change)
				.append(pChange, rhs.pChange).append(previousClose, rhs.previousClose).append(open, rhs.open)
				.append(close, rhs.close).append(vwap, rhs.vwap).append(lowerCP, rhs.lowerCP)
				.append(upperCP, rhs.upperCP).append(pPriceBand, rhs.pPriceBand).append(basePrice, rhs.basePrice)
				.append(intraDayHighLow, rhs.intraDayHighLow).append(weekHighLow, rhs.weekHighLow)
				.append(additionalProperties, rhs.additionalProperties).isEquals();
	}

}
