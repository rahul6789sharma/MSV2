
package org.stocksrin.v2.common.model.future;

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
    "totalBuyQuantity",
    "totalSellQuantity",
    "bid",
    "ask",
    "carryOfCost",
    "tradeInfo",
    "otherInfo"
})
public class MarketDeptOrderBook {

    @JsonProperty("totalBuyQuantity")
    private Integer totalBuyQuantity;
    @JsonProperty("totalSellQuantity")
    private Integer totalSellQuantity;
    @JsonProperty("bid")
    private List<Bid> bid = new ArrayList<Bid>();
    @JsonProperty("ask")
    private List<Ask> ask = new ArrayList<Ask>();
    @JsonProperty("carryOfCost")
    private CarryOfCost carryOfCost;
    @JsonProperty("tradeInfo")
    private TradeInfo tradeInfo;
    @JsonProperty("otherInfo")
    private OtherInfo otherInfo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("totalBuyQuantity")
    public Integer getTotalBuyQuantity() {
        return totalBuyQuantity;
    }

    @JsonProperty("totalBuyQuantity")
    public void setTotalBuyQuantity(Integer totalBuyQuantity) {
        this.totalBuyQuantity = totalBuyQuantity;
    }

    public MarketDeptOrderBook withTotalBuyQuantity(Integer totalBuyQuantity) {
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

    public MarketDeptOrderBook withTotalSellQuantity(Integer totalSellQuantity) {
        this.totalSellQuantity = totalSellQuantity;
        return this;
    }

    @JsonProperty("bid")
    public List<Bid> getBid() {
        return bid;
    }

    @JsonProperty("bid")
    public void setBid(List<Bid> bid) {
        this.bid = bid;
    }

    public MarketDeptOrderBook withBid(List<Bid> bid) {
        this.bid = bid;
        return this;
    }

    @JsonProperty("ask")
    public List<Ask> getAsk() {
        return ask;
    }

    @JsonProperty("ask")
    public void setAsk(List<Ask> ask) {
        this.ask = ask;
    }

    public MarketDeptOrderBook withAsk(List<Ask> ask) {
        this.ask = ask;
        return this;
    }

    @JsonProperty("carryOfCost")
    public CarryOfCost getCarryOfCost() {
        return carryOfCost;
    }

    @JsonProperty("carryOfCost")
    public void setCarryOfCost(CarryOfCost carryOfCost) {
        this.carryOfCost = carryOfCost;
    }

    public MarketDeptOrderBook withCarryOfCost(CarryOfCost carryOfCost) {
        this.carryOfCost = carryOfCost;
        return this;
    }

    @JsonProperty("tradeInfo")
    public TradeInfo getTradeInfo() {
        return tradeInfo;
    }

    @JsonProperty("tradeInfo")
    public void setTradeInfo(TradeInfo tradeInfo) {
        this.tradeInfo = tradeInfo;
    }

    public MarketDeptOrderBook withTradeInfo(TradeInfo tradeInfo) {
        this.tradeInfo = tradeInfo;
        return this;
    }

    @JsonProperty("otherInfo")
    public OtherInfo getOtherInfo() {
        return otherInfo;
    }

    @JsonProperty("otherInfo")
    public void setOtherInfo(OtherInfo otherInfo) {
        this.otherInfo = otherInfo;
    }

    public MarketDeptOrderBook withOtherInfo(OtherInfo otherInfo) {
        this.otherInfo = otherInfo;
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

    public MarketDeptOrderBook withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(totalBuyQuantity).append(totalSellQuantity).append(bid).append(ask).append(carryOfCost).append(tradeInfo).append(otherInfo).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MarketDeptOrderBook) == false) {
            return false;
        }
        MarketDeptOrderBook rhs = ((MarketDeptOrderBook) other);
        return new EqualsBuilder().append(totalBuyQuantity, rhs.totalBuyQuantity).append(totalSellQuantity, rhs.totalSellQuantity).append(bid, rhs.bid).append(ask, rhs.ask).append(carryOfCost, rhs.carryOfCost).append(tradeInfo, rhs.tradeInfo).append(otherInfo, rhs.otherInfo).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
