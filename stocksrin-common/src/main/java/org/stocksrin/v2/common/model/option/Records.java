
package org.stocksrin.v2.common.model.option;

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
@JsonPropertyOrder({ "expiryDates", "data", "timestamp", "underlyingValue", "strikePrices" })
public class Records {

	@JsonProperty("expiryDates")
	private List<String> expiryDates = new ArrayList<String>();
	
	@JsonProperty("data")
	private List<Datum> data = new ArrayList<Datum>();
	
	@JsonProperty("timestamp")
	private String timestamp;
	
	@JsonProperty("underlyingValue")
	private Double underlyingValue;
	
	@JsonProperty("strikePrices")
	private List<Integer> strikePrices = new ArrayList<Integer>();
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("expiryDates")
	public List<String> getExpiryDates() {
		return expiryDates;
	}

	@JsonProperty("expiryDates")
	public void setExpiryDates(List<String> expiryDates) {
		this.expiryDates = expiryDates;
	}

	public Records withExpiryDates(List<String> expiryDates) {
		this.expiryDates = expiryDates;
		return this;
	}

	@JsonProperty("data")
	public List<Datum> getData() {
		return data;
	}

	@JsonProperty("data")
	public void setData(List<Datum> data) {
		this.data = data;
	}

	public Records withData(List<Datum> data) {
		this.data = data;
		return this;
	}

	@JsonProperty("timestamp")
	public String getTimestamp() {
		return timestamp;
	}

	@JsonProperty("timestamp")
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Records withTimestamp(String timestamp) {
		this.timestamp = timestamp;
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

	public Records withUnderlyingValue(Double underlyingValue) {
		this.underlyingValue = underlyingValue;
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

	public Records withStrikePrices(List<Integer> strikePrices) {
		this.strikePrices = strikePrices;
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

	public Records withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(expiryDates).append(data).append(timestamp).append(underlyingValue).append(strikePrices).append(additionalProperties).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Records) == false) {
			return false;
		}
		Records rhs = ((Records) other);
		return new EqualsBuilder().append(expiryDates, rhs.expiryDates).append(data, rhs.data).append(timestamp, rhs.timestamp).append(underlyingValue, rhs.underlyingValue)
				.append(strikePrices, rhs.strikePrices).append(additionalProperties, rhs.additionalProperties).isEquals();
	}

}
