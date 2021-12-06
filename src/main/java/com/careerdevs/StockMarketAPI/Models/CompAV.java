package com.careerdevs.StockMarketAPI.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompAV implements Comparable<CompAV> {
    private String symbol;
    private String assetType;
    private String name;
    private String Exchange;
    private String description;
    private String address;
    private String MarketCapitalization;


//    public CompAV(String symbol, String name, String marketCapitalization) {
//        this.symbol = symbol;
//        this.name = name;
//        MarketCapitalization = marketCapitalization;
//    }

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


    public String getExchange() {
        return Exchange;
    }

    @JsonProperty("Exchange")
    public void setExchange(String exchange) {
        Exchange = exchange;
    }

    public String getMarketCapitalization() {
        return MarketCapitalization;
    }

    @JsonProperty("Market Cap")
    public void setMarketCapitalization(String marketCapitalization) {
        MarketCapitalization = marketCapitalization;
    }


    @Override
    public int compareTo(CompAV otherComp) {
        return Integer.parseInt(getMarketCapitalization())  - Integer.parseInt(otherComp.getMarketCapitalization());
    }
}
