
package org.stocksrin.v2.common.model.option;

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
    "strikePrice",
    "expiryDate",
    "PE",
    "CE"
})
public class Datum_ {

    @JsonProperty("strikePrice")
    private Integer strikePrice;
   
    @JsonProperty("expiryDate")
    private String expiryDate;
    
    @JsonProperty("PE")
    private PE pE;
    
    @JsonProperty("CE")
    private CE cE;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("strikePrice")
    public Integer getStrikePrice() {
        return strikePrice;
    }

    @JsonProperty("strikePrice")
    public void setStrikePrice(Integer strikePrice) {
        this.strikePrice = strikePrice;
    }

    public Datum_ withStrikePrice(Integer strikePrice) {
        this.strikePrice = strikePrice;
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

    public Datum_ withExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    @JsonProperty("PE")
    public PE getPE() {
        return pE;
    }

    @JsonProperty("PE")
    public void setPE(PE pE) {
        this.pE = pE;
    }

    public Datum_ withPE(PE pE) {
        this.pE = pE;
        return this;
    }

    @JsonProperty("CE")
    public CE getCE() {
        return cE;
    }

    @JsonProperty("CE")
    public void setCE(CE cE) {
        this.cE = cE;
    }

    public Datum_ withCE(CE cE) {
        this.cE = cE;
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

    public Datum_ withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(strikePrice).append(expiryDate).append(pE).append(cE).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Datum_) == false) {
            return false;
        }
        Datum_ rhs = ((Datum_) other);
        return new EqualsBuilder().append(strikePrice, rhs.strikePrice).append(expiryDate, rhs.expiryDate).append(pE, rhs.pE).append(cE, rhs.cE).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
