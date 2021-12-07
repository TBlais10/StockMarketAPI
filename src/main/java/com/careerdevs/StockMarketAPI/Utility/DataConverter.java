package com.careerdevs.StockMarketAPI.Utility;

import com.careerdevs.StockMarketAPI.Models.CompAV;
import com.careerdevs.StockMarketAPI.Models.CompCSV;

public class DataConverter {


    public static CompCSV convertFeature1(CompCSV comp){
        CompCSV tempComp = new CompCSV();
        tempComp.setName(comp.getName());
        tempComp.setSymbol(comp.getSymbol());
        tempComp.setExchange(comp.getExchange());

        return tempComp;
    }

    public static CompCSV convertFeature2(CompCSV comp){
        CompCSV tempComp = new CompCSV();
        tempComp.setName(comp.getName());
        tempComp.setSymbol(comp.getSymbol());
        tempComp.setIpoDate(comp.getIpoDate());

        return tempComp;
    }

    public static CompAV convertFeature5(CompAV comp){
        CompAV tempComp = new CompAV();
        tempComp.setSymbol(comp.getSymbol());
        tempComp.setAssetType(comp.getAssetType());
        tempComp.setName(comp.getName());
        tempComp.setDescription(comp.getDescription());
        tempComp.setAddress(comp.getAddress());


        return tempComp;
    }

    public static CompAV convertFeature6(CompAV comp){
        CompAV tempComp = new CompAV();
        tempComp.setName(comp.getName());
        tempComp.setSymbol(comp.getSymbol());
        tempComp.setMarketCap(comp.getMarketCap());

        return tempComp;
    }


    public static CompAV convertFeature7(CompAV comp){
        CompAV tempComp = new CompAV();
        tempComp.setName(comp.getName());
        tempComp.setSymbol(comp.getSymbol());
        tempComp.setDividendDate(comp.getDividendDate());

        return tempComp;
    }

//    public static Consumer<CompAV> daysUntilDivDate = (tempCompAV) -> {
//        int daysUntil = (short) DateUtils.getDaysUntil
//    }

    //numberformat = numberformat.getInstance()
    //Consumer method

}
