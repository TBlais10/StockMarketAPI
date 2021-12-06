package com.careerdevs.StockMarketAPI.Utility;

import com.careerdevs.StockMarketAPI.Models.CompAV;

import java.util.Comparator;

public class StockComparator {

    public static class sortByDate {

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
