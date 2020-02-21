
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
    "price",
    "carry"
})
public class CarryOfCost {

    @JsonProperty("price")
    private Price price;
    @JsonProperty("carry")
    private Carry carry;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("price")
    public Price getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(Price price) {
        this.price = price;
    }

    public CarryOfCost withPrice(Price price) {
        this.price = price;
        return this;
    }

    @JsonProperty("carry")
    public Carry getCarry() {
        return carry;
    }

    @JsonProperty("carry")
    public void setCarry(Carry carry) {
        this.carry = carry;
    }

    public CarryOfCost withCarry(Carry carry) {
        this.carry = carry;
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

    public CarryOfCost withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(price).append(carry).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CarryOfCost) == false) {
            return false;
        }
        CarryOfCost rhs = ((CarryOfCost) other);
        return new EqualsBuilder().append(price, rhs.price).append(carry, rhs.carry).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
