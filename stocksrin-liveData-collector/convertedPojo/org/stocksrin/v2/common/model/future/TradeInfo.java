
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
    "tradedVolume",
    "value",
    "vmap",
    "premiumTurnover",
    "openInterest",
    "changeinOpenInterest",
    "pchangeinOpenInterest",
    "marketLot"
})
public class TradeInfo {

    @JsonProperty("tradedVolume")
    private Integer tradedVolume;
    @JsonProperty("value")
    private Integer value;
    @JsonProperty("vmap")
    private Integer vmap;
    @JsonProperty("premiumTurnover")
    private Integer premiumTurnover;
    @JsonProperty("openInterest")
    private Integer openInterest;
    @JsonProperty("changeinOpenInterest")
    private Integer changeinOpenInterest;
    @JsonProperty("pchangeinOpenInterest")
    private Integer pchangeinOpenInterest;
    @JsonProperty("marketLot")
    private Integer marketLot;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("tradedVolume")
    public Integer getTradedVolume() {
        return tradedVolume;
    }

    @JsonProperty("tradedVolume")
    public void setTradedVolume(Integer tradedVolume) {
        this.tradedVolume = tradedVolume;
    }

    public TradeInfo withTradedVolume(Integer tradedVolume) {
        this.tradedVolume = tradedVolume;
        return this;
    }

    @JsonProperty("value")
    public Integer getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(Integer value) {
        this.value = value;
    }

    public TradeInfo withValue(Integer value) {
        this.value = value;
        return this;
    }

    @JsonProperty("vmap")
    public Integer getVmap() {
        return vmap;
    }

    @JsonProperty("vmap")
    public void setVmap(Integer vmap) {
        this.vmap = vmap;
    }

    public TradeInfo withVmap(Integer vmap) {
        this.vmap = vmap;
        return this;
    }

    @JsonProperty("premiumTurnover")
    public Integer getPremiumTurnover() {
        return premiumTurnover;
    }

    @JsonProperty("premiumTurnover")
    public void setPremiumTurnover(Integer premiumTurnover) {
        this.premiumTurnover = premiumTurnover;
    }

    public TradeInfo withPremiumTurnover(Integer premiumTurnover) {
        this.premiumTurnover = premiumTurnover;
        return this;
    }

    @JsonProperty("openInterest")
    public Integer getOpenInterest() {
        return openInterest;
    }

    @JsonProperty("openInterest")
    public void setOpenInterest(Integer openInterest) {
        this.openInterest = openInterest;
    }

    public TradeInfo withOpenInterest(Integer openInterest) {
        this.openInterest = openInterest;
        return this;
    }

    @JsonProperty("changeinOpenInterest")
    public Integer getChangeinOpenInterest() {
        return changeinOpenInterest;
    }

    @JsonProperty("changeinOpenInterest")
    public void setChangeinOpenInterest(Integer changeinOpenInterest) {
        this.changeinOpenInterest = changeinOpenInterest;
    }

    public TradeInfo withChangeinOpenInterest(Integer changeinOpenInterest) {
        this.changeinOpenInterest = changeinOpenInterest;
        return this;
    }

    @JsonProperty("pchangeinOpenInterest")
    public Integer getPchangeinOpenInterest() {
        return pchangeinOpenInterest;
    }

    @JsonProperty("pchangeinOpenInterest")
    public void setPchangeinOpenInterest(Integer pchangeinOpenInterest) {
        this.pchangeinOpenInterest = pchangeinOpenInterest;
    }

    public TradeInfo withPchangeinOpenInterest(Integer pchangeinOpenInterest) {
        this.pchangeinOpenInterest = pchangeinOpenInterest;
        return this;
    }

    @JsonProperty("marketLot")
    public Integer getMarketLot() {
        return marketLot;
    }

    @JsonProperty("marketLot")
    public void setMarketLot(Integer marketLot) {
        this.marketLot = marketLot;
    }

    public TradeInfo withMarketLot(Integer marketLot) {
        this.marketLot = marketLot;
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

    public TradeInfo withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(tradedVolume).append(value).append(vmap).append(premiumTurnover).append(openInterest).append(changeinOpenInterest).append(pchangeinOpenInterest).append(marketLot).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TradeInfo) == false) {
            return false;
        }
        TradeInfo rhs = ((TradeInfo) other);
        return new EqualsBuilder().append(tradedVolume, rhs.tradedVolume).append(value, rhs.value).append(vmap, rhs.vmap).append(premiumTurnover, rhs.premiumTurnover).append(openInterest, rhs.openInterest).append(changeinOpenInterest, rhs.changeinOpenInterest).append(pchangeinOpenInterest, rhs.pchangeinOpenInterest).append(marketLot, rhs.marketLot).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
