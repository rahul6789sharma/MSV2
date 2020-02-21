
package org.stocksrin.v2.common.model.option;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "records", "filtered" })
public class NSEOptionChainData {

	@JsonProperty("records")
	private Records records;
	
	@JsonProperty("filtered")
	private Filtered filtered;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("records")
	public Records getRecords() {
		return records;
	}

	@JsonProperty("records")
	public void setRecords(Records records) {
		this.records = records;
	}

	public NSEOptionChainData withRecords(Records records) {
		this.records = records;
		return this;
	}

	@JsonProperty("filtered")
	public Filtered getFiltered() {
		return filtered;
	}

	@JsonProperty("filtered")
	public void setFiltered(Filtered filtered) {
		this.filtered = filtered;
	}

	public NSEOptionChainData withFiltered(Filtered filtered) {
		this.filtered = filtered;
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

	public NSEOptionChainData withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(records).append(filtered).append(additionalProperties).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof NSEOptionChainData) == false) {
			return false;
		}
		NSEOptionChainData rhs = ((NSEOptionChainData) other);
		return new EqualsBuilder().append(records, rhs.records).append(filtered, rhs.filtered).append(additionalProperties, rhs.additionalProperties).isEquals();
	}

}
