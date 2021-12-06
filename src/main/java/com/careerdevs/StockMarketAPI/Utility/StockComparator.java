package com.careerdevs.StockMarketAPI.Utility;

import com.careerdevs.StockMarketAPI.Models.CompAV;
import com.careerdevs.StockMarketAPI.Models.CompCSV;

import java.util.Comparator;

public class StockComparator {

    public static class sortByDate {

    }

    public static class sortByName implements Comparator<CompCSV> {
        public int compare(CompCSV a, CompCSV b){
            return a.getName().compareTo(b.getName());
        }
    }


    public static class SortBySymbol {
        public int compare(CompAV a, CompAV b) {
            int result = 0;

            String compACap = a.getMarketCapitalization();
            String compBCap = b.getMarketCapitalization();

            if (compACap == null && compBCap != null) {
                result = 1;
            } else if (compACap != null && compBCap == null) {
                result = -1;
            } else if (compACap == null) {
                result = 0;
            } else {
                long compAMc = Long.parseLong(a.getMarketCapitalization());
                long compBMc = Long.parseLong(b.getMarketCapitalization());
            }

            return result;
        }
    }
}
