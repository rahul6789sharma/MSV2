
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
@JsonPropertyOrder({ "min", "max", "value" })
public class IntraDayHighLow {

	@JsonProperty("min")
	private Double min;
	
	@JsonProperty("max")
	private Double max;
	
	@JsonProperty("value")
	private Double value;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("min")
	public Double getMin() {
		return min;
	}

	@JsonProperty("min")
	public void setMin(Double min) {
		this.min = min;
	}

	public IntraDayHighLow withMin(Double min) {
		this.min = min;
		return this;
	}

	@JsonProperty("max")
	public Double getMax() {
		return max;
	}

	@JsonProperty("max")
	public void setMax(Double max) {
		this.max = max;
	}

	public IntraDayHighLow withMax(Double max) {
		this.max = max;
		return this;
	}

	@JsonProperty("value")
	public Double getValue() {
		return value;
	}

	@JsonProperty("value")
	public void setValue(Double value) {
		this.value = value;
	}

	public IntraDayHighLow withValue(Double value) {
		this.value = value;
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

	public IntraDayHighLow withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(min).append(max).append(value).append(additionalProperties).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof IntraDayHighLow) == false) {
			return false;
		}
		IntraDayHighLow rhs = ((IntraDayHighLow) other);
		return new EqualsBuilder().append(min, rhs.min).append(max, rhs.max).append(value, rhs.value)
				.append(additionalProperties, rhs.additionalProperties).isEquals();
	}

}
