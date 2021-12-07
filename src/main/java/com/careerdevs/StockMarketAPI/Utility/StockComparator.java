package com.careerdevs.StockMarketAPI.Utility;

import com.careerdevs.StockMarketAPI.Models.CompAV;
import com.careerdevs.StockMarketAPI.Models.CompCSV;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class StockComparator {

    public static class sortByDivDate implements Comparator<CompAV>{
        public int compare (CompAV a, CompAV b){
            try {
                Date comp1date = new SimpleDateFormat("yyyy-mm-dd").parse(a.getDividendDate());
                Date comp2date = new SimpleDateFormat("yyyy-mm-dd").parse(b.getDividendDate());

                Date today = new Date();

                //Calculate days until comp1 divdate

                int daysbetweenComp1 =  Math.abs((int) ((comp1date.getTime() - today.getTime())/1000/60/60/ 24)); //converts to milliseconds, finds difference between dates, then converts back to days
                int daysbetweenComp2 = Math.abs((int) ((comp2date.getTime() - today.getTime())/1000/60/60/ 24)); //converts to milliseconds, finds difference between dates, then converts back to days

                if (comp1date.before(today)){ //if comp1 is before today, return true
                    daysbetweenComp1 = 365 - daysbetweenComp1;
                }
                if (comp2date.before(today)){
                    daysbetweenComp2 = 365 - daysbetweenComp2;
                }

                return Integer.compare(daysbetweenComp1, daysbetweenComp2);

            } catch (ParseException e) {
                return -1;
            } catch (Exception exception){
                exception.getMessage();
                System.out.println(exception.getMessage());
                return -1;
            }
        }


    }

    public static class sortMarketCap implements Comparator<CompAV>{
        public int compare (CompAV a, CompAV b){
            long comp1 = Long.parseLong(a.getMarketCap());
            long comp2 = Long.parseLong(b.getMarketCap());

            return Long.compare(comp1, comp2) * -1; //-1 causes it to sort from greatest to least
        }
    }

}
