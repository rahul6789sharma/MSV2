package org.stocksrin.v2.common.model.option;


import java.io.Serializable;

public class MaxPainResponse implements Serializable {

    private String expiry;
    private Integer maxPain;
    private String lastupdateTimeStamp;

    public MaxPainResponse() {

    }

    public MaxPainResponse(String expiry, Integer maxPain) {
        this.expiry = expiry;
        this.maxPain = maxPain;
    }

    public String getLastupdateTimeStamp() {
        return lastupdateTimeStamp;
    }

    public void setLastupdateTimeStamp(String lastupdateTimeStamp) {
        this.lastupdateTimeStamp = lastupdateTimeStamp;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public Integer getMaxPain() {
        return maxPain;
    }

    public void setMaxPain(Integer maxPain) {
        this.maxPain = maxPain;
    }
}