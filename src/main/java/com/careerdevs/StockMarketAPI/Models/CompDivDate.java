package com.careerdevs.StockMarketAPI.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompDivDate {

    private String Symbol;
    private String Name;
    private String DividenedDate;

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

    public String getDividenedDate() {
        return DividenedDate;
    }

    @JsonProperty("DivDate")
    public void setDividenedDate(String dividenedDate) {
        DividenedDate = dividenedDate;
    }
}
