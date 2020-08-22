
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
@JsonPropertyOrder({
    "preopen",
    "ato",
    "IEP",
    "totalTradedVolume",
    "finalPrice",
    "finalQuantity",
    "lastUpdateTime",
    "totalBuyQuantity",
    "totalSellQuantity",
    "atoBuyQty",
    "atoSellQty"
})
public class PreOpenMarket {

    @JsonProperty("preopen")
    private List<Preopen> preopen = new ArrayList<Preopen>();
    @JsonProperty("ato")
    private Ato ato;
    @JsonProperty("IEP")
    private Integer iEP;
    @JsonProperty("totalTradedVolume")
    private Integer totalTradedVolume;
    @JsonProperty("finalPrice")
    private Integer finalPrice;
    @JsonProperty("finalQuantity")
    private Integer finalQuantity;
    @JsonProperty("lastUpdateTime")
    private String lastUpdateTime;
    @JsonProperty("totalBuyQuantity")
    private Integer totalBuyQuantity;
    @JsonProperty("totalSellQuantity")
    private Integer totalSellQuantity;
    @JsonProperty("atoBuyQty")
    private Integer atoBuyQty;
    @JsonProperty("atoSellQty")
    private Integer atoSellQty;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("preopen")
    public List<Preopen> getPreopen() {
        return preopen;
    }

    @JsonProperty("preopen")
    public void setPreopen(List<Preopen> preopen) {
        this.preopen = preopen;
    }

    public PreOpenMarket withPreopen(List<Preopen> preopen) {
        this.preopen = preopen;
        return this;
    }

    @JsonProperty("ato")
    public Ato getAto() {
        return ato;
    }

    @JsonProperty("ato")
    public void setAto(Ato ato) {
        this.ato = ato;
    }

    public PreOpenMarket withAto(Ato ato) {
        this.ato = ato;
        return this;
    }

    @JsonProperty("IEP")
    public Integer getIEP() {
        return iEP;
    }

    @JsonProperty("IEP")
    public void setIEP(Integer iEP) {
        this.iEP = iEP;
    }

    public PreOpenMarket withIEP(Integer iEP) {
        this.iEP = iEP;
        return this;
    }

    @JsonProperty("totalTradedVolume")
    public Integer getTotalTradedVolume() {
        return totalTradedVolume;
    }

    @JsonProperty("totalTradedVolume")
    public void setTotalTradedVolume(Integer totalTradedVolume) {
        this.totalTradedVolume = totalTradedVolume;
    }

    public PreOpenMarket withTotalTradedVolume(Integer totalTradedVolume) {
        this.totalTradedVolume = totalTradedVolume;
        return this;
    }

    @JsonProperty("finalPrice")
    public Integer getFinalPrice() {
        return finalPrice;
    }

    @JsonProperty("finalPrice")
    public void setFinalPrice(Integer finalPrice) {
        this.finalPrice = finalPrice;
    }

    public PreOpenMarket withFinalPrice(Integer finalPrice) {
        this.finalPrice = finalPrice;
        return this;
    }

    @JsonProperty("finalQuantity")
    public Integer getFinalQuantity() {
        return finalQuantity;
    }

    @JsonProperty("finalQuantity")
    public void setFinalQuantity(Integer finalQuantity) {
        this.finalQuantity = finalQuantity;
    }

    public PreOpenMarket withFinalQuantity(Integer finalQuantity) {
        this.finalQuantity = finalQuantity;
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

    public PreOpenMarket withLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
        return this;
    }

    @JsonProperty("totalBuyQuantity")
    public Integer getTotalBuyQuantity() {
        return totalBuyQuantity;
    }

    @JsonProperty("totalBuyQuantity")
    public void setTotalBuyQuantity(Integer totalBuyQuantity) {
        this.totalBuyQuantity = totalBuyQuantity;
    }

    public PreOpenMarket withTotalBuyQuantity(Integer totalBuyQuantity) {
        this.totalBuyQuantity = totalBuyQuantity;
        return this;
    }

    @JsonProperty("totalSellQuantity")
    public Integer getTotalSellQuantity() {
        return totalSellQuantity;
    }

    @JsonProperty("totalSellQuantity")
    public void setTotalSellQuantity(Integer totalSellQuantity) {
        this.totalSellQuantity = totalSellQuantity;
    }

    public PreOpenMarket withTotalSellQuantity(Integer totalSellQuantity) {
        this.totalSellQuantity = totalSellQuantity;
        return this;
    }

    @JsonProperty("atoBuyQty")
    public Integer getAtoBuyQty() {
        return atoBuyQty;
    }

    @JsonProperty("atoBuyQty")
    public void setAtoBuyQty(Integer atoBuyQty) {
        this.atoBuyQty = atoBuyQty;
    }

    public PreOpenMarket withAtoBuyQty(Integer atoBuyQty) {
        this.atoBuyQty = atoBuyQty;
        return this;
    }

    @JsonProperty("atoSellQty")
    public Integer getAtoSellQty() {
        return atoSellQty;
    }

    @JsonProperty("atoSellQty")
    public void setAtoSellQty(Integer atoSellQty) {
        this.atoSellQty = atoSellQty;
    }

    public PreOpenMarket withAtoSellQty(Integer atoSellQty) {
        this.atoSellQty = atoSellQty;
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

    public PreOpenMarket withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(preopen).append(ato).append(iEP).append(totalTradedVolume).append(finalPrice).append(finalQuantity).append(lastUpdateTime).append(totalBuyQuantity).append(totalSellQuantity).append(atoBuyQty).append(atoSellQty).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PreOpenMarket) == false) {
            return false;
        }
        PreOpenMarket rhs = ((PreOpenMarket) other);
        return new EqualsBuilder().append(preopen, rhs.preopen).append(ato, rhs.ato).append(iEP, rhs.iEP).append(totalTradedVolume, rhs.totalTradedVolume).append(finalPrice, rhs.finalPrice).append(finalQuantity, rhs.finalQuantity).append(lastUpdateTime, rhs.lastUpdateTime).append(totalBuyQuantity, rhs.totalBuyQuantity).append(totalSellQuantity, rhs.totalSellQuantity).append(atoBuyQty, rhs.atoBuyQty).append(atoSellQty, rhs.atoSellQty).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
