package com.careerdevs.StockMarketAPI.Utility;

import com.careerdevs.StockMarketAPI.Models.CompAV;
import com.careerdevs.StockMarketAPI.Models.CompCSV;

public class DataConverter {

    public static CompAV convertOverview(CompAV comp){
        CompAV tempComp = new CompAV();
        tempComp.setName(comp.getName());
        tempComp.setSymbol(comp.getSymbol());
        tempComp.setExchange(comp.getExchange());

        return tempComp;
    }

    public static CompCSV convertGetAll(CompCSV comp){
        CompCSV tempComp = new CompCSV();
        tempComp.setName(comp.getName());
        tempComp.setSymbol(comp.getSymbol());
        tempComp.setExchange(comp.getExchange());

        return tempComp;
    }

    public static CompAV convertMarketCap (CompAV comp){
        CompAV tempComp = new CompAV();
        tempComp.setName(comp.getName());
        tempComp.setSymbol(comp.getSymbol());
        tempComp.setMarketCapitalization(comp.getMarketCapitalization());

        return tempComp;
    }

    public static CompAV convertDividendDate (CompAV comp){
        CompAV tempComp = new CompAV();
        tempComp.setName(comp.getName());
        tempComp.setSymbol(comp.getSymbol());
        tempComp.setDividenedDate(comp.getDividenedDate());

        return tempComp;
    }

}
