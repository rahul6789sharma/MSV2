
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
    "min",
    "minDate",
    "max",
    "maxDate",
    "value"
})
public class WeekHighLow {

    @JsonProperty("min")
    private Double min;
    @JsonProperty("minDate")
    private String minDate;
    @JsonProperty("max")
    private Double max;
    @JsonProperty("maxDate")
    private String maxDate;
    @JsonProperty("value")
    private Double value;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("min")
    public Double getMin() {
        return min;
    }

    @JsonProperty("min")
    public void setMin(Double min) {
        this.min = min;
    }

    public WeekHighLow withMin(Double min) {
        this.min = min;
        return this;
    }

    @JsonProperty("minDate")
    public String getMinDate() {
        return minDate;
    }

    @JsonProperty("minDate")
    public void setMinDate(String minDate) {
        this.minDate = minDate;
    }

    public WeekHighLow withMinDate(String minDate) {
        this.minDate = minDate;
        return this;
    }

    @JsonProperty("max")
    public Double getMax() {
        return max;
    }

    @JsonProperty("max")
    public void setMax(Double max) {
        this.max = max;
    }

    public WeekHighLow withMax(Double max) {
        this.max = max;
        return this;
    }

    @JsonProperty("maxDate")
    public String getMaxDate() {
        return maxDate;
    }

    @JsonProperty("maxDate")
    public void setMaxDate(String maxDate) {
        this.maxDate = maxDate;
    }

    public WeekHighLow withMaxDate(String maxDate) {
        this.maxDate = maxDate;
        return this;
    }

    @JsonProperty("value")
    public Double getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(Double value) {
        this.value = value;
    }

    public WeekHighLow withValue(Double value) {
        this.value = value;
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

    public WeekHighLow withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(min).append(minDate).append(max).append(maxDate).append(value).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof WeekHighLow) == false) {
            return false;
        }
        WeekHighLow rhs = ((WeekHighLow) other);
        return new EqualsBuilder().append(min, rhs.min).append(minDate, rhs.minDate).append(max, rhs.max).append(maxDate, rhs.maxDate).append(value, rhs.value).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
