package com.careerdevs.StockMarketAPI.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompMarketCap {

    private String Symbol;
    private String Name;
    private String MarketCapitalization;

    public String getSymbol() {
        return Symbol;
    }

    @JsonProperty("Symbol")
    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public String getName() {
        return Name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        Name = name;
    }

    public String getMarketCapitalization() {
        return MarketCapitalization;
    }

    @JsonProperty("MarketCap")
    public void setMarketCapitalization(String marketCapitalization) {
        MarketCapitalization = marketCapitalization;
    }
}
