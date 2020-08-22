
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
@JsonPropertyOrder({
    "series",
    "symbol",
    "isin",
    "status",
    "listingDate",
    "industry",
    "lastUpdateTime",
    "pdSectorPe",
    "pdSymbolPe",
    "pdSectorInd"
})
public class Metadata {

    @JsonProperty("series")
    private String series;
    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("isin")
    private String isin;
    @JsonProperty("status")
    private String status;
    @JsonProperty("listingDate")
    private String listingDate;
    @JsonProperty("industry")
    private String industry;
    @JsonProperty("lastUpdateTime")
    private String lastUpdateTime;
    @JsonProperty("pdSectorPe")
    private Double pdSectorPe;
    @JsonProperty("pdSymbolPe")
    private Double pdSymbolPe;
    @JsonProperty("pdSectorInd")
    private String pdSectorInd;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("series")
    public String getSeries() {
        return series;
    }

    @JsonProperty("series")
    public void setSeries(String series) {
        this.series = series;
    }

    public Metadata withSeries(String series) {
        this.series = series;
        return this;
    }

    @JsonProperty("symbol")
    public String getSymbol() {
        return symbol;
    }

    @JsonProperty("symbol")
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Metadata withSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    @JsonProperty("isin")
    public String getIsin() {
        return isin;
    }

    @JsonProperty("isin")
    public void setIsin(String isin) {
        this.isin = isin;
    }

    public Metadata withIsin(String isin) {
        this.isin = isin;
        return this;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    public Metadata withStatus(String status) {
        this.status = status;
        return this;
    }

    @JsonProperty("listingDate")
    public String getListingDate() {
        return listingDate;
    }

    @JsonProperty("listingDate")
    public void setListingDate(String listingDate) {
        this.listingDate = listingDate;
    }

    public Metadata withListingDate(String listingDate) {
        this.listingDate = listingDate;
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

    public Metadata withIndustry(String industry) {
        this.industry = industry;
        return this;
    }

    @JsonProperty("lastUpdateTime")
    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    @JsonProperty("lastUpdateTime")
    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Metadata withLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
        return this;
    }

    @JsonProperty("pdSectorPe")
    public Double getPdSectorPe() {
        return pdSectorPe;
    }

    @JsonProperty("pdSectorPe")
    public void setPdSectorPe(Double pdSectorPe) {
        this.pdSectorPe = pdSectorPe;
    }

    public Metadata withPdSectorPe(Double pdSectorPe) {
        this.pdSectorPe = pdSectorPe;
        return this;
    }

    @JsonProperty("pdSymbolPe")
    public Double getPdSymbolPe() {
        return pdSymbolPe;
    }

    @JsonProperty("pdSymbolPe")
    public void setPdSymbolPe(Double pdSymbolPe) {
        this.pdSymbolPe = pdSymbolPe;
    }

    public Metadata withPdSymbolPe(Double pdSymbolPe) {
        this.pdSymbolPe = pdSymbolPe;
        return this;
    }

    @JsonProperty("pdSectorInd")
    public String getPdSectorInd() {
        return pdSectorInd;
    }

    @JsonProperty("pdSectorInd")
    public void setPdSectorInd(String pdSectorInd) {
        this.pdSectorInd = pdSectorInd;
    }

    public Metadata withPdSectorInd(String pdSectorInd) {
        this.pdSectorInd = pdSectorInd;
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

    public Metadata withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(series).append(symbol).append(isin).append(status).append(listingDate).append(industry).append(lastUpdateTime).append(pdSectorPe).append(pdSymbolPe).append(pdSectorInd).append(additionalProperties).toHashCode();
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
        return new EqualsBuilder().append(series, rhs.series).append(symbol, rhs.symbol).append(isin, rhs.isin).append(status, rhs.status).append(listingDate, rhs.listingDate).append(industry, rhs.industry).append(lastUpdateTime, rhs.lastUpdateTime).append(pdSectorPe, rhs.pdSectorPe).append(pdSymbolPe, rhs.pdSymbolPe).append(pdSectorInd, rhs.pdSectorInd).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
