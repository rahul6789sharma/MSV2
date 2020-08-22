
package org.stocksrin.v2.common.model.future;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
@JsonPropertyOrder({ "info", "underlyingValue", "vfq", "fut_timestamp", "opt_timestamp", "stocks", "strikePrices", "expiryDates" })
public class Future {

	@JsonProperty("info")
	private Info info;
	
	@JsonProperty("underlyingValue")
	private Double underlyingValue;
	
	@JsonProperty("vfq")
	private Integer vfq;
	
	@JsonProperty("fut_timestamp")
	private String futTimestamp;
	
	@JsonProperty("opt_timestamp")
	private String optTimestamp;
	
	@JsonProperty("stocks")
	private List<Stock> stocks = new ArrayList<Stock>();
	
	@JsonProperty("strikePrices")
	private List<Integer> strikePrices = new ArrayList<Integer>();
	
	@JsonProperty("expiryDates")
	private List<String> expiryDates = new ArrayList<String>();
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("info")
	public Info getInfo() {
		return info;
	}

	@JsonProperty("info")
	public void setInfo(Info info) {
		this.info = info;
	}

	public Future withInfo(Info info) {
		this.info = info;
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

	public Future withUnderlyingValue(Double underlyingValue) {
		this.underlyingValue = underlyingValue;
		return this;
	}

	@JsonProperty("vfq")
	public Integer getVfq() {
		return vfq;
	}

	@JsonProperty("vfq")
	public void setVfq(Integer vfq) {
		this.vfq = vfq;
	}

	public Future withVfq(Integer vfq) {
		this.vfq = vfq;
		return this;
	}

	@JsonProperty("fut_timestamp")
	public String getFutTimestamp() {
		return futTimestamp;
	}

	@JsonProperty("fut_timestamp")
	public void setFutTimestamp(String futTimestamp) {
		this.futTimestamp = futTimestamp;
	}

	public Future withFutTimestamp(String futTimestamp) {
		this.futTimestamp = futTimestamp;
		return this;
	}

	@JsonProperty("opt_timestamp")
	public String getOptTimestamp() {
		return optTimestamp;
	}

	@JsonProperty("opt_timestamp")
	public void setOptTimestamp(String optTimestamp) {
		this.optTimestamp = optTimestamp;
	}

	public Future withOptTimestamp(String optTimestamp) {
		this.optTimestamp = optTimestamp;
		return this;
	}

	@JsonProperty("stocks")
	public List<Stock> getStocks() {
		return stocks;
	}

	@JsonProperty("stocks")
	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	public Future withStocks(List<Stock> stocks) {
		this.stocks = stocks;
		return this;
	}

	@JsonProperty("strikePrices")
	public List<Integer> getStrikePrices() {
		return strikePrices;
	}

	@JsonProperty("strikePrices")
	public void setStrikePrices(List<Integer> strikePrices) {
		this.strikePrices = strikePrices;
	}

	public Future withStrikePrices(List<Integer> strikePrices) {
		this.strikePrices = strikePrices;
		return this;
	}

	@JsonProperty("expiryDates")
	public List<String> getExpiryDates() {
		return expiryDates;
	}

	@JsonProperty("expiryDates")
	public void setExpiryDates(List<String> expiryDates) {
		this.expiryDates = expiryDates;
	}

	public Future withExpiryDates(List<String> expiryDates) {
		this.expiryDates = expiryDates;
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

	public Future withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(info).append(underlyingValue).append(vfq).append(futTimestamp).append(optTimestamp).append(stocks).append(strikePrices).append(expiryDates)
				.append(additionalProperties).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Future) == false) {
			return false;
		}
		Future rhs = ((Future) other);
		return new EqualsBuilder().append(info, rhs.info).append(underlyingValue, rhs.underlyingValue).append(vfq, rhs.vfq).append(futTimestamp, rhs.futTimestamp)
				.append(optTimestamp, rhs.optTimestamp).append(stocks, rhs.stocks).append(strikePrices, rhs.strikePrices).append(expiryDates, rhs.expiryDates)
				.append(additionalProperties, rhs.additionalProperties).isEquals();
	}

	
}
