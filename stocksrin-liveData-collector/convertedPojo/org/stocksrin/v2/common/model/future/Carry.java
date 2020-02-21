
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
    "bestBuy",
    "bestSell",
    "lastPrice"
})
public class Carry {

    @JsonProperty("bestBuy")
    private Integer bestBuy;
    @JsonProperty("bestSell")
    private Integer bestSell;
    @JsonProperty("lastPrice")
    private Integer lastPrice;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("bestBuy")
    public Integer getBestBuy() {
        return bestBuy;
    }

    @JsonProperty("bestBuy")
    public void setBestBuy(Integer bestBuy) {
        this.bestBuy = bestBuy;
    }

    public Carry withBestBuy(Integer bestBuy) {
        this.bestBuy = bestBuy;
        return this;
    }

    @JsonProperty("bestSell")
    public Integer getBestSell() {
        return bestSell;
    }

    @JsonProperty("bestSell")
    public void setBestSell(Integer bestSell) {
        this.bestSell = bestSell;
    }

    public Carry withBestSell(Integer bestSell) {
        this.bestSell = bestSell;
        return this;
    }

    @JsonProperty("lastPrice")
    public Integer getLastPrice() {
        return lastPrice;
    }

    @JsonProperty("lastPrice")
    public void setLastPrice(Integer lastPrice) {
        this.lastPrice = lastPrice;
    }

    public Carry withLastPrice(Integer lastPrice) {
        this.lastPrice = lastPrice;
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

    public Carry withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(bestBuy).append(bestSell).append(lastPrice).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Carry) == false) {
            return false;
        }
        Carry rhs = ((Carry) other);
        return new EqualsBuilder().append(bestBuy, rhs.bestBuy).append(bestSell, rhs.bestSell).append(lastPrice, rhs.lastPrice).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
