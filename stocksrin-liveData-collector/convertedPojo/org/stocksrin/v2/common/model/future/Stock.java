
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
    "metadata",
    "underlyingValue",
    "volumeFreezeQuantity",
    "marketDeptOrderBook"
})
public class Stock {

    @JsonProperty("metadata")
    private Metadata metadata;
    @JsonProperty("underlyingValue")
    private Double underlyingValue;
    @JsonProperty("volumeFreezeQuantity")
    private Integer volumeFreezeQuantity;
    @JsonProperty("marketDeptOrderBook")
    private MarketDeptOrderBook marketDeptOrderBook;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("metadata")
    public Metadata getMetadata() {
        return metadata;
    }

    @JsonProperty("metadata")
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Stock withMetadata(Metadata metadata) {
        this.metadata = metadata;
        return this;
    }

    @JsonProperty("underlyingValue")
    public Double getUnderlyingValue() {
        return underlyingValue;
    }

    @JsonProperty("underlyingValue")
    public void setUnderlyingValue(Double underlyingValue) {
        this.underlyingValue = underlyingValue;
    }

    public Stock withUnderlyingValue(Double underlyingValue) {
        this.underlyingValue = underlyingValue;
        return this;
    }

    @JsonProperty("volumeFreezeQuantity")
    public Integer getVolumeFreezeQuantity() {
        return volumeFreezeQuantity;
    }

    @JsonProperty("volumeFreezeQuantity")
    public void setVolumeFreezeQuantity(Integer volumeFreezeQuantity) {
        this.volumeFreezeQuantity = volumeFreezeQuantity;
    }

    public Stock withVolumeFreezeQuantity(Integer volumeFreezeQuantity) {
        this.volumeFreezeQuantity = volumeFreezeQuantity;
        return this;
    }

    @JsonProperty("marketDeptOrderBook")
    public MarketDeptOrderBook getMarketDeptOrderBook() {
        return marketDeptOrderBook;
    }

    @JsonProperty("marketDeptOrderBook")
    public void setMarketDeptOrderBook(MarketDeptOrderBook marketDeptOrderBook) {
        this.marketDeptOrderBook = marketDeptOrderBook;
    }

    public Stock withMarketDeptOrderBook(MarketDeptOrderBook marketDeptOrderBook) {
        this.marketDeptOrderBook = marketDeptOrderBook;
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

    public Stock withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(metadata).append(underlyingValue).append(volumeFreezeQuantity).append(marketDeptOrderBook).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Stock) == false) {
            return false;
        }
        Stock rhs = ((Stock) other);
        return new EqualsBuilder().append(metadata, rhs.metadata).append(underlyingValue, rhs.underlyingValue).append(volumeFreezeQuantity, rhs.volumeFreezeQuantity).append(marketDeptOrderBook, rhs.marketDeptOrderBook).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
