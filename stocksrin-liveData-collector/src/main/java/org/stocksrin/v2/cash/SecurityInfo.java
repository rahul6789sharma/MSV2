
package org.stocksrin.v2.cash;

import java.math.BigInteger;
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
@JsonPropertyOrder({ "boardStatus", "tradingStatus", "tradingSegment", "sessionNo", "slb", "classOfShare",
		"derivatives", "surveillance", "faceValue", "issuedCap" })
public class SecurityInfo {

	@JsonProperty("boardStatus")
	private String boardStatus;
	@JsonProperty("tradingStatus")
	private String tradingStatus;
	@JsonProperty("tradingSegment")
	private String tradingSegment;
	@JsonProperty("sessionNo")
	private String sessionNo;
	@JsonProperty("slb")
	private String slb;
	@JsonProperty("classOfShare")
	private String classOfShare;
	@JsonProperty("derivatives")
	private String derivatives;
	@JsonProperty("surveillance")
	private String surveillance;
	@JsonProperty("faceValue")
	private Integer faceValue;
	
	
	@JsonProperty("issuedCap")
	private BigInteger issuedCap;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("boardStatus")
	public String getBoardStatus() {
		return boardStatus;
	}

	@JsonProperty("boardStatus")
	public void setBoardStatus(String boardStatus) {
		this.boardStatus = boardStatus;
	}

	public SecurityInfo withBoardStatus(String boardStatus) {
		this.boardStatus = boardStatus;
		return this;
	}

	@JsonProperty("tradingStatus")
	public String getTradingStatus() {
		return tradingStatus;
	}

	@JsonProperty("tradingStatus")
	public void setTradingStatus(String tradingStatus) {
		this.tradingStatus = tradingStatus;
	}

	public SecurityInfo withTradingStatus(String tradingStatus) {
		this.tradingStatus = tradingStatus;
		return this;
	}

	@JsonProperty("tradingSegment")
	public String getTradingSegment() {
		return tradingSegment;
	}

	@JsonProperty("tradingSegment")
	public void setTradingSegment(String tradingSegment) {
		this.tradingSegment = tradingSegment;
	}

	public SecurityInfo withTradingSegment(String tradingSegment) {
		this.tradingSegment = tradingSegment;
		return this;
	}

	@JsonProperty("sessionNo")
	public String getSessionNo() {
		return sessionNo;
	}

	@JsonProperty("sessionNo")
	public void setSessionNo(String sessionNo) {
		this.sessionNo = sessionNo;
	}

	public SecurityInfo withSessionNo(String sessionNo) {
		this.sessionNo = sessionNo;
		return this;
	}

	@JsonProperty("slb")
	public String getSlb() {
		return slb;
	}

	@JsonProperty("slb")
	public void setSlb(String slb) {
		this.slb = slb;
	}

	public SecurityInfo withSlb(String slb) {
		this.slb = slb;
		return this;
	}

	@JsonProperty("classOfShare")
	public String getClassOfShare() {
		return classOfShare;
	}

	@JsonProperty("classOfShare")
	public void setClassOfShare(String classOfShare) {
		this.classOfShare = classOfShare;
	}

	public SecurityInfo withClassOfShare(String classOfShare) {
		this.classOfShare = classOfShare;
		return this;
	}

	@JsonProperty("derivatives")
	public String getDerivatives() {
		return derivatives;
	}

	@JsonProperty("derivatives")
	public void setDerivatives(String derivatives) {
		this.derivatives = derivatives;
	}

	public SecurityInfo withDerivatives(String derivatives) {
		this.derivatives = derivatives;
		return this;
	}

	@JsonProperty("surveillance")
	public String getSurveillance() {
		return surveillance;
	}

	@JsonProperty("surveillance")
	public void setSurveillance(String surveillance) {
		this.surveillance = surveillance;
	}

	public SecurityInfo withSurveillance(String surveillance) {
		this.surveillance = surveillance;
		return this;
	}

	@JsonProperty("faceValue")
	public Integer getFaceValue() {
		return faceValue;
	}

	@JsonProperty("faceValue")
	public void setFaceValue(Integer faceValue) {
		this.faceValue = faceValue;
	}

	public SecurityInfo withFaceValue(Integer faceValue) {
		this.faceValue = faceValue;
		return this;
	}

	@JsonProperty("issuedCap")
	public BigInteger getIssuedCap() {
		return issuedCap;
	}

	@JsonProperty("issuedCap")
	public void setIssuedCap(BigInteger issuedCap) {
		this.issuedCap = issuedCap;
	}

	public SecurityInfo withIssuedCap(BigInteger issuedCap) {
		this.issuedCap = issuedCap;
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

	public SecurityInfo withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(boardStatus).append(tradingStatus).append(tradingSegment).append(sessionNo)
				.append(slb).append(classOfShare).append(derivatives).append(surveillance).append(faceValue)
				.append(issuedCap).append(additionalProperties).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof SecurityInfo) == false) {
			return false;
		}
		SecurityInfo rhs = ((SecurityInfo) other);
		return new EqualsBuilder().append(boardStatus, rhs.boardStatus).append(tradingStatus, rhs.tradingStatus)
				.append(tradingSegment, rhs.tradingSegment).append(sessionNo, rhs.sessionNo).append(slb, rhs.slb)
				.append(classOfShare, rhs.classOfShare).append(derivatives, rhs.derivatives)
				.append(surveillance, rhs.surveillance).append(faceValue, rhs.faceValue)
				.append(issuedCap, rhs.issuedCap).append(additionalProperties, rhs.additionalProperties).isEquals();
	}

}
