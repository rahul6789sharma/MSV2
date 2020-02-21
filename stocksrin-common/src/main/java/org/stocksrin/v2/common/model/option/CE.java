
package org.stocksrin.v2.common.model.option;

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
@JsonPropertyOrder({ "strikePrice", "expiryDate", "underlying", "identifier", "openInterest", "changeinOpenInterest", "pchangeinOpenInterest", "totalTradedVolume", "impliedVolatility", "lastPrice",
		"change", "pChange", "totalBuyQuantity", "totalSellQuantity", "bidQty", "bidprice", "askQty", "askPrice", "underlyingValue" })
public class CE {

	@JsonProperty("strikePrice")
	private Integer strikePrice;
	@JsonProperty("expiryDate")
	private String expiryDate;
	@JsonProperty("underlying")
	private String underlying;
	@JsonProperty("identifier")
	private String identifier;
	@JsonProperty("openInterest")
	private Integer openInterest;
	@JsonProperty("changeinOpenInterest")
	private Integer changeinOpenInterest;
	@JsonProperty("pchangeinOpenInterest")
	private Double pchangeinOpenInterest;
	@JsonProperty("totalTradedVolume")
	private Integer totalTradedVolume;
	@JsonProperty("impliedVolatility")
	private Double impliedVolatility;
	@JsonProperty("lastPrice")
	private Double lastPrice;
	@JsonProperty("change")
	private Double change;
	@JsonProperty("pChange")
	private Integer pChange;
	@JsonProperty("totalBuyQuantity")
	private Integer totalBuyQuantity;
	@JsonProperty("totalSellQuantity")
	private Integer totalSellQuantity;
	@JsonProperty("bidQty")
	private Integer bidQty;
	@JsonProperty("bidprice")
	private Double bidprice;
	@JsonProperty("askQty")
	private Integer askQty;
	@JsonProperty("askPrice")
	private Double askPrice;
	@JsonProperty("underlyingValue")
	private Double underlyingValue;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("strikePrice")
	public Integer getStrikePrice() {
		return strikePrice;
	}

	@JsonProperty("strikePrice")
	public void setStrikePrice(Integer strikePrice) {
		this.strikePrice = strikePrice;
	}

	public CE withStrikePrice(Integer strikePrice) {
		this.strikePrice = strikePrice;
		return this;
	}

	@JsonProperty("expiryDate")
	public String getExpiryDate() {
		return expiryDate;
	}

	@JsonProperty("expiryDate")
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public CE withExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
		return this;
	}

	@JsonProperty("underlying")
	public String getUnderlying() {
		return underlying;
	}

	@JsonProperty("underlying")
	public void setUnderlying(String underlying) {
		this.underlying = underlying;
	}

	public CE withUnderlying(String underlying) {
		this.underlying = underlying;
		return this;
	}

	@JsonProperty("identifier")
	public String getIdentifier() {
		return identifier;
	}

	@JsonProperty("identifier")
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public CE withIdentifier(String identifier) {
		this.identifier = identifier;
		return this;
	}

	@JsonProperty("openInterest")
	public Integer getOpenInterest() {
		return openInterest;
	}

	@JsonProperty("openInterest")
	public void setOpenInterest(Integer openInterest) {
		this.openInterest = openInterest;
	}

	public CE withOpenInterest(Integer openInterest) {
		this.openInterest = openInterest;
		return this;
	}

	@JsonProperty("changeinOpenInterest")
	public Integer getChangeinOpenInterest() {
		return changeinOpenInterest;
	}

	@JsonProperty("changeinOpenInterest")
	public void setChangeinOpenInterest(Integer changeinOpenInterest) {
		this.changeinOpenInterest = changeinOpenInterest;
	}

	public CE withChangeinOpenInterest(Integer changeinOpenInterest) {
		this.changeinOpenInterest = changeinOpenInterest;
		return this;
	}

	@JsonProperty("pchangeinOpenInterest")
	public Double getPchangeinOpenInterest() {
		return pchangeinOpenInterest;
	}

	@JsonProperty("pchangeinOpenInterest")
	public void setPchangeinOpenInterest(Double pchangeinOpenInterest) {
		this.pchangeinOpenInterest = pchangeinOpenInterest;
	}

	public CE withPchangeinOpenInterest(Double pchangeinOpenInterest) {
		this.pchangeinOpenInterest = pchangeinOpenInterest;
		return this;
	}

	@JsonProperty("totalTradedVolume")
	public Integer getTotalTradedVolume() {
		return totalTradedVolume;
	}

	@JsonProperty("totalTradedVolume")
	public void setTotalTradedVolume(Integer totalTradedVolume) {
		this.totalTradedVolume = totalTradedVolume;
	}

	public CE withTotalTradedVolume(Integer totalTradedVolume) {
		this.totalTradedVolume = totalTradedVolume;
		return this;
	}

	@JsonProperty("impliedVolatility")
	public Double getImpliedVolatility() {
		return impliedVolatility;
	}

	@JsonProperty("impliedVolatility")
	public void setImpliedVolatility(Double impliedVolatility) {
		this.impliedVolatility = impliedVolatility;
	}

	public CE withImpliedVolatility(Double impliedVolatility) {
		this.impliedVolatility = impliedVolatility;
		return this;
	}

	@JsonProperty("lastPrice")
	public Double getLastPrice() {
		return lastPrice;
	}

	@JsonProperty("lastPrice")
	public void setLastPrice(Double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public CE withLastPrice(Double lastPrice) {
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

	public CE withChange(Double change) {
		this.change = change;
		return this;
	}

	@JsonProperty("pChange")
	public Integer getPChange() {
		return pChange;
	}

	@JsonProperty("pChange")
	public void setPChange(Integer pChange) {
		this.pChange = pChange;
	}

	public CE withPChange(Integer pChange) {
		this.pChange = pChange;
		return this;
	}

	@JsonProperty("totalBuyQuantity")
	public Integer getTotalBuyQuantity() {
		return totalBuyQuantity;
	}

	@JsonProperty("totalBuyQuantity")
	public void setTotalBuyQuantity(Integer totalBuyQuantity) {
		this.totalBuyQuantity = totalBuyQuantity;
	}

	public CE withTotalBuyQuantity(Integer totalBuyQuantity) {
		this.totalBuyQuantity = totalBuyQuantity;
		return this;
	}

	@JsonProperty("totalSellQuantity")
	public Integer getTotalSellQuantity() {
		return totalSellQuantity;
	}

	@JsonProperty("totalSellQuantity")
	public void setTotalSellQuantity(Integer totalSellQuantity) {
		this.totalSellQuantity = totalSellQuantity;
	}

	public CE withTotalSellQuantity(Integer totalSellQuantity) {
		this.totalSellQuantity = totalSellQuantity;
		return this;
	}

	@JsonProperty("bidQty")
	public Integer getBidQty() {
		return bidQty;
	}

	@JsonProperty("bidQty")
	public void setBidQty(Integer bidQty) {
		this.bidQty = bidQty;
	}

	public CE withBidQty(Integer bidQty) {
		this.bidQty = bidQty;
		return this;
	}

	@JsonProperty("bidprice")
	public Double getBidprice() {
		return bidprice;
	}

	@JsonProperty("bidprice")
	public void setBidprice(Double bidprice) {
		this.bidprice = bidprice;
	}

	public CE withBidprice(Double bidprice) {
		this.bidprice = bidprice;
		return this;
	}

	@JsonProperty("askQty")
	public Integer getAskQty() {
		return askQty;
	}

	@JsonProperty("askQty")
	public void setAskQty(Integer askQty) {
		this.askQty = askQty;
	}

	public CE withAskQty(Integer askQty) {
		this.askQty = askQty;
		return this;
	}

	@JsonProperty("askPrice")
	public Double getAskPrice() {
		return askPrice;
	}

	@JsonProperty("askPrice")
	public void setAskPrice(Double askPrice) {
		this.askPrice = askPrice;
	}

	public CE withAskPrice(Double askPrice) {
		this.askPrice = askPrice;
		return this;
	}

	@JsonProperty("underlyingValue")
	public Double getUnderlyingValue() {
		return underlyingValue;
	}

	@JsonProperty("underlyingValue")
	public void setUnderlyingValue(Double underlyingValue) {
		this.underlyingValue = underlyingValue;
	}

	public CE withUnderlyingValue(Double underlyingValue) {
		this.underlyingValue = underlyingValue;
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

	public CE withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(strikePrice).append(expiryDate).append(underlying).append(identifier).append(openInterest).append(changeinOpenInterest).append(pchangeinOpenInterest)
				.append(totalTradedVolume).append(impliedVolatility).append(lastPrice).append(change).append(pChange).append(totalBuyQuantity).append(totalSellQuantity).append(bidQty).append(bidprice)
				.append(askQty).append(askPrice).append(underlyingValue).append(additionalProperties).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof CE) == false) {
			return false;
		}
		CE rhs = ((CE) other);
		return new EqualsBuilder().append(strikePrice, rhs.strikePrice).append(expiryDate, rhs.expiryDate).append(underlying, rhs.underlying).append(identifier, rhs.identifier)
				.append(openInterest, rhs.openInterest).append(changeinOpenInterest, rhs.changeinOpenInterest).append(pchangeinOpenInterest, rhs.pchangeinOpenInterest)
				.append(totalTradedVolume, rhs.totalTradedVolume).append(impliedVolatility, rhs.impliedVolatility).append(lastPrice, rhs.lastPrice).append(change, rhs.change)
				.append(pChange, rhs.pChange).append(totalBuyQuantity, rhs.totalBuyQuantity).append(totalSellQuantity, rhs.totalSellQuantity).append(bidQty, rhs.bidQty).append(bidprice, rhs.bidprice)
				.append(askQty, rhs.askQty).append(askPrice, rhs.askPrice).append(underlyingValue, rhs.underlyingValue).append(additionalProperties, rhs.additionalProperties).isEquals();
	}

}
