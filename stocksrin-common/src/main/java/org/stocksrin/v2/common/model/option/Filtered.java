
package org.stocksrin.v2.common.model.option;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
@JsonPropertyOrder({ "data", "CE", "PE" })
public class Filtered {

	@JsonProperty("data")
	private List<Datum_> data = new ArrayList<Datum_>();

	@JsonProperty("CE")
	private CE cE;

	@JsonProperty("PE")
	private PE pE;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("data")
	public List<Datum_> getData() {
		return data;
	}

	@JsonProperty("data")
	public void setData(List<Datum_> data) {
		this.data = data;
	}

	public Filtered withData(List<Datum_> data) {
		this.data = data;
		return this;
	}

	@JsonProperty("CE")
	public CE getCE() {
		return cE;
	}

	@JsonProperty("CE")
	public void setCE(CE cE) {
		this.cE = cE;
	}

	public Filtered withCE(CE cE) {
		this.cE = cE;
		return this;
	}

	@JsonProperty("PE")
	public PE getPE() {
		return pE;
	}

	@JsonProperty("PE")
	public void setPE(PE pE) {
		this.pE = pE;
	}

	public Filtered withPE(PE pE) {
		this.pE = pE;
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

	public Filtered withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(data).append(cE).append(pE).append(additionalProperties).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Filtered) == false) {
			return false;
		}
		Filtered rhs = ((Filtered) other);
		return new EqualsBuilder().append(data, rhs.data).append(cE, rhs.cE).append(pE, rhs.pE).append(additionalProperties, rhs.additionalProperties).isEquals();
	}

}
