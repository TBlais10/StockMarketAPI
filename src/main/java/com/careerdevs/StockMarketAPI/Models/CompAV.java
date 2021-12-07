package com.careerdevs.StockMarketAPI.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompAV {
    private String symbol;
    private String assetType;
    private String name;
    private String Exchange;
    private String description;
    private String address;
    private String MarketCap;
    private String DividendDate;
    @JsonIgnore
    private int daysUntil;

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
        this.Exchange = exchange;
    }

    public String getMarketCap() {
        return MarketCap;
    }

    @JsonProperty("MarketCapitalization")
    public void setMarketCap(String marketCap) {
        this.MarketCap = marketCap;
    }

    public String getDividendDate() {
        return DividendDate;
    }

    @JsonProperty("DividendDate")
    public void setDividendDate(String dividendDate) {
        DividendDate = dividendDate;
    }

    public int getDaysUntil() {
        return daysUntil;
    }

    public void setDaysUntil(int daysUntil) {
        this.daysUntil = daysUntil;
    }
}
