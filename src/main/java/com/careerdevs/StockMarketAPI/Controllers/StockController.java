package com.careerdevs.StockMarketAPI.Controllers;

import com.careerdevs.StockMarketAPI.Models.CompAV;
import com.careerdevs.StockMarketAPI.Models.CompCSV;
import com.careerdevs.StockMarketAPI.Utility.StockCSVParcer;
import com.careerdevs.StockMarketAPI.Utility.StockComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    @Autowired
    Environment env;

    public String getURL(String symbol){
        return "https://www.alphavantage.co/query?function=OVERVIEW&symbol=" + symbol + "&=apikey=" + env.getProperty("api.key");
    }

    @GetMapping("/getall") //INTERNAL
    public List<CompAV> getAll(RestTemplate restTemplate) {
        List<CompCSV> tempCsvData = StockCSVParcer.readCSV();
        List<CompAV> allCompData = new ArrayList<>();

        for (CompCSV compData : tempCsvData) {
            System.out.println(compData.getSymbol());
            CompAV compAllData = restTemplate.getForObject(getURL(compData.getSymbol()), CompAV.class);
            allCompData.add(compAllData);
        }

        return allCompData;
    }

    @GetMapping("/ipodata") //Gets company names and IPO data(in ascending order). INTERNAL //TODO: FINISH
    public List<CompCSV> companyIpoData(RestTemplate restTemplate) {
        List<CompCSV> tempCsvData = StockCSVParcer.readCSV();

        return tempCsvData;
    }

    @GetMapping("/nasdaq") //companies that traded on NASDAQ. INTERNAL
    public List<CompCSV> nasdaqComps(RestTemplate restTemplate) {
        List<CompCSV> tempCsvData = StockCSVParcer.readCSV();
        List<CompCSV> sortedComps = new ArrayList<>();

        for (CompCSV compData : tempCsvData) {

            if (compData.getExchange().equalsIgnoreCase("nasdaq")) {
                sortedComps.add(compData);
            }
        }
        return sortedComps;
    }

    @GetMapping("/nyse")//companies that traded on NYSE. INTERNAL
    public List<CompCSV> nyseComps(RestTemplate restTemplate) {
        List<CompCSV> tempCsvData = StockCSVParcer.readCSV();
        List<CompCSV> sortedComps = new ArrayList<>();

        for (CompCSV compData : tempCsvData) {

            if (compData.getExchange().equalsIgnoreCase("nyse")) {
                sortedComps.add(compData);
            }
        }
        return sortedComps;
    }

    @GetMapping("/overviewinfo") //EXTERNAL
    public List<CompAV> overview(RestTemplate restTemplate) {
        List<CompCSV> tempData = StockCSVParcer.readCSV();
        List<CompAV> compOverview = new ArrayList<>();

        try {
        for (CompCSV compData : tempData){
        CompAV comp = restTemplate.getForObject(getURL(compData.getSymbol()), CompAV.class);
        compOverview.add(comp);
        }
            return compOverview;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping ("/marketcap")//Companies name, symbol, and market cap (high to low) EXTERNAL//TODO: STILL NEEDS WORK
    public List<CompAV> highLowMarketCap(RestTemplate restTemplate){
        List<CompCSV> tempData = StockCSVParcer.readCSV();
        List<CompAV> comps = new ArrayList<>();

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
    public List<CompAV> divDates (RestTemplate restTemplate){
        List<CompCSV> tempCompArr = StockCSVParcer.readCSV();
        List<CompAV> comps = new ArrayList<>();

        for (CompCSV compData : tempCompArr){
            CompAV apiComps = restTemplate.getForObject(getURL(compData.getSymbol()), CompAV.class);
            comps.add(apiComps);
        }

        return comps;
    }
}
