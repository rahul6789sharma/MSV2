
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
@JsonPropertyOrder({ "info", "metadata", "securityInfo", "priceInfo", "preOpenMarket" })
public class Stocks {

	@JsonProperty("info")
	private Info info;
	@JsonProperty("metadata")
	private Metadata metadata;
	@JsonProperty("securityInfo")
	private SecurityInfo securityInfo;
	@JsonProperty("priceInfo")
	private PriceInfo priceInfo;
	@JsonProperty("preOpenMarket")
	private PreOpenMarket preOpenMarket;
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

	public Stocks withInfo(Info info) {
		this.info = info;
		return this;
	}

	@JsonProperty("metadata")
	public Metadata getMetadata() {
		return metadata;
	}

	@JsonProperty("metadata")
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public Stocks withMetadata(Metadata metadata) {
		this.metadata = metadata;
		return this;
	}

	@JsonProperty("securityInfo")
	public SecurityInfo getSecurityInfo() {
		return securityInfo;
	}

	@JsonProperty("securityInfo")
	public void setSecurityInfo(SecurityInfo securityInfo) {
		this.securityInfo = securityInfo;
	}

	public Stocks withSecurityInfo(SecurityInfo securityInfo) {
		this.securityInfo = securityInfo;
		return this;
	}

	@JsonProperty("priceInfo")
	public PriceInfo getPriceInfo() {
		return priceInfo;
	}

	@JsonProperty("priceInfo")
	public void setPriceInfo(PriceInfo priceInfo) {
		this.priceInfo = priceInfo;
	}

	public Stocks withPriceInfo(PriceInfo priceInfo) {
		this.priceInfo = priceInfo;
		return this;
	}

	@JsonProperty("preOpenMarket")
	public PreOpenMarket getPreOpenMarket() {
		return preOpenMarket;
	}

	@JsonProperty("preOpenMarket")
	public void setPreOpenMarket(PreOpenMarket preOpenMarket) {
		this.preOpenMarket = preOpenMarket;
	}

	public Stocks withPreOpenMarket(PreOpenMarket preOpenMarket) {
		this.preOpenMarket = preOpenMarket;
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

	public Stocks withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(info).append(metadata).append(securityInfo).append(priceInfo)
				.append(preOpenMarket).append(additionalProperties).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Stocks) == false) {
			return false;
		}
		Stocks rhs = ((Stocks) other);
		return new EqualsBuilder().append(info, rhs.info).append(metadata, rhs.metadata)
				.append(securityInfo, rhs.securityInfo).append(priceInfo, rhs.priceInfo)
				.append(preOpenMarket, rhs.preOpenMarket).append(additionalProperties, rhs.additionalProperties)
				.isEquals();
	}

}
