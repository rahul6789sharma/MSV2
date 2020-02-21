
package org.stocksrin.v2.common.model.future;

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
@JsonPropertyOrder({ "instrumentType", "expiryDate", "optionType", "strikePrice", "identifier", "openPrice", "highPrice", "lowPrice", "closePrice", "prevClose", "lastPrice", "change", "pChange",
		"numberOfContractsTraded", "totalTurnover" })
public class Metadata {

	@JsonProperty("instrumentType")
	private String instrumentType;

	@JsonProperty("expiryDate")
	private String expiryDate;

	@JsonProperty("optionType")
	private String optionType;

	@JsonProperty("strikePrice")
	private Integer strikePrice;

	@JsonProperty("identifier")
	private String identifier;

	@JsonProperty("openPrice")
	private Integer openPrice;

	@JsonProperty("highPrice")
	private Integer highPrice;

	@JsonProperty("lowPrice")
	private Integer lowPrice;

	@JsonProperty("closePrice")
	private Integer closePrice;

	@JsonProperty("prevClose")
	private Integer prevClose;

	@JsonProperty("lastPrice")
	private Double lastPrice;

	@JsonProperty("change")
	private Integer change;

	@JsonProperty("pChange")
	private Integer pChange;

	@JsonProperty("numberOfContractsTraded")
	private Integer numberOfContractsTraded;

	@JsonProperty("totalTurnover")
	private Integer totalTurnover;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonIgnore
	private Integer lotSize;

	@JsonIgnore
	private String symbol;

	@JsonIgnore
	private String companyName;

	@JsonIgnore
	private Integer arbatrage;

	@JsonIgnore
	private Double abrPercenatge;

	@JsonIgnore
	private Integer invAmount;

	@JsonProperty("instrumentType")
	public String getInstrumentType() {
		return instrumentType;
	}

	@JsonProperty("instrumentType")
	public void setInstrumentType(String instrumentType) {
		this.instrumentType = instrumentType;
	}

	public Metadata withInstrumentType(String instrumentType) {
		this.instrumentType = instrumentType;
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

	public Metadata withExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
		return this;
	}

	@JsonProperty("optionType")
	public String getOptionType() {
		return optionType;
	}

	@JsonProperty("optionType")
	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}

	public Metadata withOptionType(String optionType) {
		this.optionType = optionType;
		return this;
	}

	@JsonProperty("strikePrice")
	public Integer getStrikePrice() {
		return strikePrice;
	}

	@JsonProperty("strikePrice")
	public void setStrikePrice(Integer strikePrice) {
		this.strikePrice = strikePrice;
	}

	public Metadata withStrikePrice(Integer strikePrice) {
		this.strikePrice = strikePrice;
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

	public Metadata withIdentifier(String identifier) {
		this.identifier = identifier;
		return this;
	}

	@JsonProperty("openPrice")
	public Integer getOpenPrice() {
		return openPrice;
	}

	@JsonProperty("openPrice")
	public void setOpenPrice(Integer openPrice) {
		this.openPrice = openPrice;
	}

	public Metadata withOpenPrice(Integer openPrice) {
		this.openPrice = openPrice;
		return this;
	}

	@JsonProperty("highPrice")
	public Integer getHighPrice() {
		return highPrice;
	}

	@JsonProperty("highPrice")
	public void setHighPrice(Integer highPrice) {
		this.highPrice = highPrice;
	}

	public Metadata withHighPrice(Integer highPrice) {
		this.highPrice = highPrice;
		return this;
	}

	@JsonProperty("lowPrice")
	public Integer getLowPrice() {
		return lowPrice;
	}

	@JsonProperty("lowPrice")
	public void setLowPrice(Integer lowPrice) {
		this.lowPrice = lowPrice;
	}

	public Metadata withLowPrice(Integer lowPrice) {
		this.lowPrice = lowPrice;
		return this;
	}

	@JsonProperty("closePrice")
	public Integer getClosePrice() {
		return closePrice;
	}

	@JsonProperty("closePrice")
	public void setClosePrice(Integer closePrice) {
		this.closePrice = closePrice;
	}

	public Metadata withClosePrice(Integer closePrice) {
		this.closePrice = closePrice;
		return this;
	}

	@JsonProperty("prevClose")
	public Integer getPrevClose() {
		return prevClose;
	}

	@JsonProperty("prevClose")
	public void setPrevClose(Integer prevClose) {
		this.prevClose = prevClose;
	}

	public Metadata withPrevClose(Integer prevClose) {
		this.prevClose = prevClose;
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

	public Metadata withLastPrice(Double lastPrice) {
		this.lastPrice = lastPrice;
		return this;
	}

	@JsonProperty("change")
	public Integer getChange() {
		return change;
	}

	@JsonProperty("change")
	public void setChange(Integer change) {
		this.change = change;
	}

	public Metadata withChange(Integer change) {
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

	public Metadata withPChange(Integer pChange) {
		this.pChange = pChange;
		return this;
	}

	@JsonProperty("numberOfContractsTraded")
	public Integer getNumberOfContractsTraded() {
		return numberOfContractsTraded;
	}

	@JsonProperty("numberOfContractsTraded")
	public void setNumberOfContractsTraded(Integer numberOfContractsTraded) {
		this.numberOfContractsTraded = numberOfContractsTraded;
	}

	public Metadata withNumberOfContractsTraded(Integer numberOfContractsTraded) {
		this.numberOfContractsTraded = numberOfContractsTraded;
		return this;
	}

	@JsonProperty("totalTurnover")
	public Integer getTotalTurnover() {
		return totalTurnover;
	}

	@JsonProperty("totalTurnover")
	public void setTotalTurnover(Integer totalTurnover) {
		this.totalTurnover = totalTurnover;
	}

	public Metadata withTotalTurnover(Integer totalTurnover) {
		this.totalTurnover = totalTurnover;
		return this;
	}

	@JsonProperty("lotSize")
	public Integer getLotSize() {
		return lotSize;
	}

	@JsonProperty("lotSize")
	public void setLotSize(Integer lotSize) {
		this.lotSize = lotSize;
	}

	@JsonProperty("symbol")
	public String getSymbol() {
		return symbol;
	}

	@JsonProperty("symbol")
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@JsonProperty("companyName")
	public String getCompanyName() {
		return companyName;
	}

	@JsonProperty("companyName")
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@JsonProperty("arbatrage")
	public Integer getArbatrage() {
		return arbatrage;
	}

	@JsonProperty("arbatrage")
	public void setArbatrage(Integer arbatrage) {
		this.arbatrage = arbatrage;
	}

	@JsonProperty("abrPercenatge")
	public Double getAbrPercenatge() {
		return abrPercenatge;
	}

	@JsonProperty("abrPercenatge")
	public void setAbrPercenatge(Double abrPercenatge) {
		this.abrPercenatge = abrPercenatge;
	}

	@JsonProperty("invAmount")
	public Integer getInvAmount() {
		return invAmount;
	}

	@JsonProperty("invAmount")
	public void setInvAmount(Integer invAmount) {
		this.invAmount = invAmount;
	}

	@Override
	public String toString() {
		return "Metadata [instrumentType=" + instrumentType + ", expiryDate=" + expiryDate + ", optionType=" + optionType + ", strikePrice=" + strikePrice + ", identifier=" + identifier
				+ ", openPrice=" + openPrice + ", highPrice=" + highPrice + ", lowPrice=" + lowPrice + ", closePrice=" + closePrice + ", prevClose=" + prevClose + ", lastPrice=" + lastPrice
				+ ", change=" + change + ", pChange=" + pChange + ", numberOfContractsTraded=" + numberOfContractsTraded + ", totalTurnover=" + totalTurnover + ", additionalProperties="
				+ additionalProperties + "]";
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public Metadata withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(instrumentType).append(expiryDate).append(optionType).append(strikePrice).append(identifier).append(openPrice).append(highPrice).append(lowPrice)
				.append(closePrice).append(prevClose).append(lastPrice).append(change).append(pChange).append(numberOfContractsTraded).append(totalTurnover).append(additionalProperties).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Metadata) == false) {
			return false;
		}
		Metadata rhs = ((Metadata) other);
		return new EqualsBuilder().append(instrumentType, rhs.instrumentType).append(expiryDate, rhs.expiryDate).append(optionType, rhs.optionType).append(strikePrice, rhs.strikePrice)
				.append(identifier, rhs.identifier).append(openPrice, rhs.openPrice).append(highPrice, rhs.highPrice).append(lowPrice, rhs.lowPrice).append(closePrice, rhs.closePrice)
				.append(prevClose, rhs.prevClose).append(lastPrice, rhs.lastPrice).append(change, rhs.change).append(pChange, rhs.pChange).append(numberOfContractsTraded, rhs.numberOfContractsTraded)
				.append(totalTurnover, rhs.totalTurnover).append(additionalProperties, rhs.additionalProperties).isEquals();
	}

}
