
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
@JsonPropertyOrder({
    "settlementPrice",
    "dailyvolatility",
    "annualisedVolatility",
    "impliedVolatility",
    "clientWisePositionLimits",
    "marketWidePositionLimits"
})
public class OtherInfo {

    @JsonProperty("settlementPrice")
    private Integer settlementPrice;
    @JsonProperty("dailyvolatility")
    private Double dailyvolatility;
    @JsonProperty("annualisedVolatility")
    private Double annualisedVolatility;
    @JsonProperty("impliedVolatility")
    private Integer impliedVolatility;
    @JsonProperty("clientWisePositionLimits")
    private Integer clientWisePositionLimits;
    @JsonProperty("marketWidePositionLimits")
    private Integer marketWidePositionLimits;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("settlementPrice")
    public Integer getSettlementPrice() {
        return settlementPrice;
    }

    @JsonProperty("settlementPrice")
    public void setSettlementPrice(Integer settlementPrice) {
        this.settlementPrice = settlementPrice;
    }

    public OtherInfo withSettlementPrice(Integer settlementPrice) {
        this.settlementPrice = settlementPrice;
        return this;
    }

    @JsonProperty("dailyvolatility")
    public Double getDailyvolatility() {
        return dailyvolatility;
    }

    @JsonProperty("dailyvolatility")
    public void setDailyvolatility(Double dailyvolatility) {
        this.dailyvolatility = dailyvolatility;
    }

    public OtherInfo withDailyvolatility(Double dailyvolatility) {
        this.dailyvolatility = dailyvolatility;
        return this;
    }

    @JsonProperty("annualisedVolatility")
    public Double getAnnualisedVolatility() {
        return annualisedVolatility;
    }

    @JsonProperty("annualisedVolatility")
    public void setAnnualisedVolatility(Double annualisedVolatility) {
        this.annualisedVolatility = annualisedVolatility;
    }

    public OtherInfo withAnnualisedVolatility(Double annualisedVolatility) {
        this.annualisedVolatility = annualisedVolatility;
        return this;
    }

    @JsonProperty("impliedVolatility")
    public Integer getImpliedVolatility() {
        return impliedVolatility;
    }

    @JsonProperty("impliedVolatility")
    public void setImpliedVolatility(Integer impliedVolatility) {
        this.impliedVolatility = impliedVolatility;
    }

    public OtherInfo withImpliedVolatility(Integer impliedVolatility) {
        this.impliedVolatility = impliedVolatility;
        return this;
    }

    @JsonProperty("clientWisePositionLimits")
    public Integer getClientWisePositionLimits() {
        return clientWisePositionLimits;
    }

    @JsonProperty("clientWisePositionLimits")
    public void setClientWisePositionLimits(Integer clientWisePositionLimits) {
        this.clientWisePositionLimits = clientWisePositionLimits;
    }

    public OtherInfo withClientWisePositionLimits(Integer clientWisePositionLimits) {
        this.clientWisePositionLimits = clientWisePositionLimits;
        return this;
    }

    @JsonProperty("marketWidePositionLimits")
    public Integer getMarketWidePositionLimits() {
        return marketWidePositionLimits;
    }

    @JsonProperty("marketWidePositionLimits")
    public void setMarketWidePositionLimits(Integer marketWidePositionLimits) {
        this.marketWidePositionLimits = marketWidePositionLimits;
    }

    public OtherInfo withMarketWidePositionLimits(Integer marketWidePositionLimits) {
        this.marketWidePositionLimits = marketWidePositionLimits;
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

    public OtherInfo withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(settlementPrice).append(dailyvolatility).append(annualisedVolatility).append(impliedVolatility).append(clientWisePositionLimits).append(marketWidePositionLimits).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OtherInfo) == false) {
            return false;
        }
        OtherInfo rhs = ((OtherInfo) other);
        return new EqualsBuilder().append(settlementPrice, rhs.settlementPrice).append(dailyvolatility, rhs.dailyvolatility).append(annualisedVolatility, rhs.annualisedVolatility).append(impliedVolatility, rhs.impliedVolatility).append(clientWisePositionLimits, rhs.clientWisePositionLimits).append(marketWidePositionLimits, rhs.marketWidePositionLimits).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
