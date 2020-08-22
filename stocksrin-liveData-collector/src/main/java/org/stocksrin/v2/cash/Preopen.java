
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
    "price",
    "buyQty",
    "sellQty",
    "iep"
})
public class Preopen {

    @JsonProperty("price")
    private Double price;
    @JsonProperty("buyQty")
    private Integer buyQty;
    @JsonProperty("sellQty")
    private Integer sellQty;
    @JsonProperty("iep")
    private Boolean iep;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("price")
    public Double getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(Double price) {
        this.price = price;
    }

    public Preopen withPrice(Double price) {
        this.price = price;
        return this;
    }

    @JsonProperty("buyQty")
    public Integer getBuyQty() {
        return buyQty;
    }

    @JsonProperty("buyQty")
    public void setBuyQty(Integer buyQty) {
        this.buyQty = buyQty;
    }

    public Preopen withBuyQty(Integer buyQty) {
        this.buyQty = buyQty;
        return this;
    }

    @JsonProperty("sellQty")
    public Integer getSellQty() {
        return sellQty;
    }

    @JsonProperty("sellQty")
    public void setSellQty(Integer sellQty) {
        this.sellQty = sellQty;
    }

    public Preopen withSellQty(Integer sellQty) {
        this.sellQty = sellQty;
        return this;
    }

    @JsonProperty("iep")
    public Boolean getIep() {
        return iep;
    }

    @JsonProperty("iep")
    public void setIep(Boolean iep) {
        this.iep = iep;
    }

    public Preopen withIep(Boolean iep) {
        this.iep = iep;
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

    public Preopen withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(price).append(buyQty).append(sellQty).append(iep).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Preopen) == false) {
            return false;
        }
        Preopen rhs = ((Preopen) other);
        return new EqualsBuilder().append(price, rhs.price).append(buyQty, rhs.buyQty).append(sellQty, rhs.sellQty).append(iep, rhs.iep).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
