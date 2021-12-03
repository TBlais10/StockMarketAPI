package com.careerdevs.StockMarketAPI.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompAV {

    private String symbol;
    private String assetType;
    private String name;
    private String description;
    private String address;

    public String getSymbol() {
        return symbol;
    }

    @JsonProperty ("Symbol")
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getAssetType() {
        return assetType;
    }

    @JsonProperty ("AssetType")
    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    @JsonProperty ("Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    @JsonProperty ("Description")
    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    @JsonProperty ("Address")
    public void setAddress(String address) {
        this.address = address;
    }
}
