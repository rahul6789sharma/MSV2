
package org.stocksrin.v2.cash;

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
@JsonPropertyOrder({ "symbol", "companyName", "industry", "activeSeries", "debtSeries", "tempSuspendedSeries",
		"isFNOSec", "isCASec", "isSLBSec", "isDebtSec", "isSuspended", "isETFSec", "isTop10", "identifier" })
public class Info {

	@JsonProperty("symbol")
	private String symbol;
	@JsonProperty("companyName")
	private String companyName;
	@JsonProperty("industry")
	private String industry;
	@JsonProperty("activeSeries")
	private List<String> activeSeries = new ArrayList<String>();
	@JsonProperty("debtSeries")
	private List<String> debtSeries = new ArrayList<String>();
	@JsonProperty("tempSuspendedSeries")
	private List<String> tempSuspendedSeries = new ArrayList<String>();
	@JsonProperty("isFNOSec")
	private Boolean isFNOSec;
	@JsonProperty("isCASec")
	private Boolean isCASec;
	@JsonProperty("isSLBSec")
	private Boolean isSLBSec;
	@JsonProperty("isDebtSec")
	private Boolean isDebtSec;
	@JsonProperty("isSuspended")
	private Boolean isSuspended;
	@JsonProperty("isETFSec")
	private Boolean isETFSec;
	@JsonProperty("isTop10")
	private Boolean isTop10;
	@JsonProperty("identifier")
	private String identifier;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("symbol")
	public String getSymbol() {
		return symbol;
	}

	@JsonProperty("symbol")
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Info withSymbol(String symbol) {
		this.symbol = symbol;
		return this;
	}

	@JsonProperty("companyName")
	public String getCompanyName() {
		return companyName;
	}

	@JsonProperty("companyName")
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Info withCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}

	@JsonProperty("industry")
	public String getIndustry() {
		return industry;
	}

	@JsonProperty("industry")
	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public Info withIndustry(String industry) {
		this.industry = industry;
		return this;
	}

	@JsonProperty("activeSeries")
	public List<String> getActiveSeries() {
		return activeSeries;
	}

	@JsonProperty("activeSeries")
	public void setActiveSeries(List<String> activeSeries) {
		this.activeSeries = activeSeries;
	}

	public Info withActiveSeries(List<String> activeSeries) {
		this.activeSeries = activeSeries;
		return this;
	}

	@JsonProperty("debtSeries")
	public List<String> getDebtSeries() {
		return debtSeries;
	}

	@JsonProperty("debtSeries")
	public void setDebtSeries(List<String> debtSeries) {
		this.debtSeries = debtSeries;
	}

	public Info withDebtSeries(List<String> debtSeries) {
		this.debtSeries = debtSeries;
		return this;
	}

	@JsonProperty("tempSuspendedSeries")
	public List<String> getTempSuspendedSeries() {
		return tempSuspendedSeries;
	}

	@JsonProperty("tempSuspendedSeries")
	public void setTempSuspendedSeries(List<String> tempSuspendedSeries) {
		this.tempSuspendedSeries = tempSuspendedSeries;
	}

	public Info withTempSuspendedSeries(List<String> tempSuspendedSeries) {
		this.tempSuspendedSeries = tempSuspendedSeries;
		return this;
	}

	@JsonProperty("isFNOSec")
	public Boolean getIsFNOSec() {
		return isFNOSec;
	}

	@JsonProperty("isFNOSec")
	public void setIsFNOSec(Boolean isFNOSec) {
		this.isFNOSec = isFNOSec;
	}

	public Info withIsFNOSec(Boolean isFNOSec) {
		this.isFNOSec = isFNOSec;
		return this;
	}

	@JsonProperty("isCASec")
	public Boolean getIsCASec() {
		return isCASec;
	}

	@JsonProperty("isCASec")
	public void setIsCASec(Boolean isCASec) {
		this.isCASec = isCASec;
	}

	public Info withIsCASec(Boolean isCASec) {
		this.isCASec = isCASec;
		return this;
	}

	@JsonProperty("isSLBSec")
	public Boolean getIsSLBSec() {
		return isSLBSec;
	}

	@JsonProperty("isSLBSec")
	public void setIsSLBSec(Boolean isSLBSec) {
		this.isSLBSec = isSLBSec;
	}

	public Info withIsSLBSec(Boolean isSLBSec) {
		this.isSLBSec = isSLBSec;
		return this;
	}

	@JsonProperty("isDebtSec")
	public Boolean getIsDebtSec() {
		return isDebtSec;
	}

	@JsonProperty("isDebtSec")
	public void setIsDebtSec(Boolean isDebtSec) {
		this.isDebtSec = isDebtSec;
	}

	public Info withIsDebtSec(Boolean isDebtSec) {
		this.isDebtSec = isDebtSec;
		return this;
	}

	@JsonProperty("isSuspended")
	public Boolean getIsSuspended() {
		return isSuspended;
	}

	@JsonProperty("isSuspended")
	public void setIsSuspended(Boolean isSuspended) {
		this.isSuspended = isSuspended;
	}

	public Info withIsSuspended(Boolean isSuspended) {
		this.isSuspended = isSuspended;
		return this;
	}

	@JsonProperty("isETFSec")
	public Boolean getIsETFSec() {
		return isETFSec;
	}

	@JsonProperty("isETFSec")
	public void setIsETFSec(Boolean isETFSec) {
		this.isETFSec = isETFSec;
	}

	public Info withIsETFSec(Boolean isETFSec) {
		this.isETFSec = isETFSec;
		return this;
	}

	@JsonProperty("isTop10")
	public Boolean getIsTop10() {
		return isTop10;
	}

	@JsonProperty("isTop10")
	public void setIsTop10(Boolean isTop10) {
		this.isTop10 = isTop10;
	}

	public Info withIsTop10(Boolean isTop10) {
		this.isTop10 = isTop10;
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

	public Info withIdentifier(String identifier) {
		this.identifier = identifier;
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

	public Info withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(symbol).append(companyName).append(industry).append(activeSeries)
				.append(debtSeries).append(tempSuspendedSeries).append(isFNOSec).append(isCASec).append(isSLBSec)
				.append(isDebtSec).append(isSuspended).append(isETFSec).append(isTop10).append(identifier)
				.append(additionalProperties).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Info) == false) {
			return false;
		}
		Info rhs = ((Info) other);
		return new EqualsBuilder().append(symbol, rhs.symbol).append(companyName, rhs.companyName)
				.append(industry, rhs.industry).append(activeSeries, rhs.activeSeries)
				.append(debtSeries, rhs.debtSeries).append(tempSuspendedSeries, rhs.tempSuspendedSeries)
				.append(isFNOSec, rhs.isFNOSec).append(isCASec, rhs.isCASec).append(isSLBSec, rhs.isSLBSec)
				.append(isDebtSec, rhs.isDebtSec).append(isSuspended, rhs.isSuspended).append(isETFSec, rhs.isETFSec)
				.append(isTop10, rhs.isTop10).append(identifier, rhs.identifier)
				.append(additionalProperties, rhs.additionalProperties).isEquals();
	}

}
