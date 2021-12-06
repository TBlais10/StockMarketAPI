package com.careerdevs.StockMarketAPI.Controllers;

import com.careerdevs.StockMarketAPI.Models.CompAV;
import com.careerdevs.StockMarketAPI.Models.CompCSV;
import com.careerdevs.StockMarketAPI.Utility.DataConverter;
import com.careerdevs.StockMarketAPI.Utility.StockCSVParcer;
import com.careerdevs.StockMarketAPI.Utility.StockComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    @Autowired
    Environment env;

    private String getURL(String symbol)/*Simplifies the process of getting symbol and adding the api key to the URL*/{
        return "https://www.alphavantage.co/query?function=OVERVIEW&symbol=" + symbol + "&=apikey=" + env.getProperty("api.key");
    }

    @GetMapping("/getall") //Symbols and Name by Name INTERNAL //TODO: TEST
    public ArrayList<CompCSV> getAll(RestTemplate restTemplate) {
        ArrayList<CompCSV> tempCsvData = StockCSVParcer.readCSV();

        assert tempCsvData != null;
        tempCsvData.sort(Comparator.comparing(CompCSV::getName));

        ArrayList<CompCSV> sortedData = new ArrayList<>();
        for (CompCSV compData : tempCsvData){
            CompCSV tempData = DataConverter.convertGetAll(compData);

            sortedData.add(tempData);
        }

        return sortedData;
    }

    @GetMapping("/ipodata") //Gets company names and IPO data(in ascending order). INTERNAL //TODO: TEST
    public ArrayList<CompCSV> companyIpoData(RestTemplate restTemplate) {
        ArrayList<CompCSV> tempCsvData = StockCSVParcer.readCSV();

        ArrayList<CompCSV> sortedData = new ArrayList<>();
        for (CompCSV compData : tempCsvData){
            CompCSV tempComp = new CompCSV();
            tempComp.setName(compData.getName());
            tempComp.setSymbol(compData.getSymbol());
            tempComp.setIpoDate(compData.getIpoDate());

            sortedData.add(tempComp);
        }

        return tempCsvData;
    }

    @GetMapping("/{stock}") //companies that traded on NASDAQ. INTERNAL
    public ArrayList<CompCSV> nasdaqComps(RestTemplate restTemplate, @RequestParam String stock) {
        ArrayList<CompCSV> tempCsvData = StockCSVParcer.readCSV();
        ArrayList<CompCSV> sortedComps = new ArrayList<>();

        try {
            for (CompCSV compData : tempCsvData) {
                if (compData.getExchange().equalsIgnoreCase(stock)) {
                    sortedComps.add(compData);
                }
            }
            return sortedComps;
        } catch (Exception e){
            e.getMessage();
            return null;
        }
    }

//    @GetMapping("/nyse")//companies that traded on NYSE. INTERNAL
//    public List<CompCSV> nyseComps(RestTemplate restTemplate) {
//        List<CompCSV> tempCsvData = StockCSVParcer.readCSV();
//        List<CompCSV> sortedComps = new ArrayList<>();
//
//        for (CompCSV compData : tempCsvData) {
//
//            if (compData.getExchange().equalsIgnoreCase("nyse")) {
//                sortedComps.add(compData);
//            }
//        }
//        return sortedComps;
//    }

    @GetMapping("/overviewinfo") //EXTERNAL //TODO: TEST
    public ArrayList<CompAV> overview(RestTemplate restTemplate) {
        ArrayList<CompCSV> tempData = StockCSVParcer.readCSV();
        ArrayList<CompAV> compOverview = new ArrayList<>();

        try {
        for (CompCSV compData : tempData){
        CompAV comp = restTemplate.getForObject(getURL(compData.getSymbol()), CompAV.class);

        assert comp != null;

        CompAV trimmedData = DataConverter.convertOverview(comp);
        compOverview.add(trimmedData);

        }
            return compOverview;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping ("/marketcap")//Companies name, symbol, and market cap (high to low) EXTERNAL//TODO: STILL NEEDS WORK
    public ArrayList<CompAV> highLowMarketCap(RestTemplate restTemplate){
        ArrayList<CompCSV> tempData = StockCSVParcer.readCSV();
        ArrayList<CompAV> comps = new ArrayList<>();

        for (CompCSV compData : tempData){
            CompAV apiComps = restTemplate.getForObject(getURL(compData.getSymbol()), CompAV.class);
            comps.add(apiComps);
        }

        Collections.sort(comps);
        Collections.reverse(comps);
        System.out.println(comps);

        return comps;
    }

    @GetMapping("/divdates") //Company names, symbol and dividend date. Order by date closest to current date. EXTERNAL //TODO: FINISH
    public ArrayList<CompAV> divDates (RestTemplate restTemplate){
        ArrayList<CompCSV> tempCompArr = StockCSVParcer.readCSV();
        ArrayList<CompAV> comps = new ArrayList<>();

        for (CompCSV compData : tempCompArr){
            CompAV apiComps = restTemplate.getForObject(getURL(compData.getSymbol()), CompAV.class);
            comps.add(apiComps);
        }

        return comps;
    }
}
