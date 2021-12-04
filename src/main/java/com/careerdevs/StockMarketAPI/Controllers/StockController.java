package com.careerdevs.StockMarketAPI.Controllers;

import com.careerdevs.StockMarketAPI.Models.CompAV;
import com.careerdevs.StockMarketAPI.Models.CompCSV;
import com.careerdevs.StockMarketAPI.Models.CompDivDate;
import com.careerdevs.StockMarketAPI.Models.CompMarketCap;
import com.careerdevs.StockMarketAPI.Utility.StockCSVParcer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    @Autowired
    Environment env;

    final public String URL = "https://www.alphavantage.co/query?function=OVERVIEW&symbol=IBM&apikey=" + env.getProperty("api.key");

    @GetMapping("/getall")
    public List<CompAV> getAll(RestTemplate restTemplate) {
        List<CompCSV> tempCsvData = StockCSVParcer.readCSV();
        List<CompAV> allCompData = new ArrayList<>();

        for (CompCSV compData : tempCsvData) {
            System.out.println(compData.getSymbol());
            CompAV compAllData = restTemplate.getForObject(URL, CompAV.class);
            allCompData.add(compAllData);
        }

        return allCompData;
    }

    @GetMapping("/ipodata") //Gets company names and IPO data(in ascending order) //TODO: FINISH
    public List<CompCSV> companyIpoData(RestTemplate restTemplate) {
        List<CompCSV> tempCsvData = StockCSVParcer.readCSV();

        return tempCsvData;
    }

    @GetMapping("/nasdaq") //companies that traded on NASDAQ //TODO: TEST
    public List<CompAV> nasdaqComps(RestTemplate restTemplate) {
        List<CompCSV> tempCsvData = StockCSVParcer.readCSV();
        List<CompAV> filteredComps = new ArrayList<>();

        for (CompCSV compData : tempCsvData) {
            CompAV compAllData = restTemplate.getForObject(URL, CompAV.class);
            if (compData.getExchange().equalsIgnoreCase("nasda")) {
                filteredComps.add(compAllData);
            }
        }
        return filteredComps;
    }

    @GetMapping("/nyse")//companies that traded on NYSE //TODO:TEST
    public List<CompAV> nyseComps(RestTemplate restTemplate) {
        List<CompCSV> tempCsvData = StockCSVParcer.readCSV();
        List<CompAV> filteredComps = new ArrayList<>();

        for (CompCSV compData : tempCsvData) {
                CompAV compAllData = restTemplate.getForObject(URL,CompAV.class);
            if (compData.getExchange().equalsIgnoreCase("nyse")) {
                filteredComps.add(compAllData);
            }
        }
        return filteredComps;
    }

    @GetMapping("/overviewinfo")
    public CompAV overview(RestTemplate restTemplate) {
        String overviewURL = "https://www.alphavantage.co/query?function=OVERVIEW&symbol=IBM&apikey=" + env.getProperty("api.key");
        try {
            return restTemplate.getForObject(overviewURL, CompAV.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping ("/marketcap")//Companies name, symbol, and market cap (high to low) //TODO: FINISH
    public List<CompMarketCap> highLowMarketCap(RestTemplate restTemplate){
        List<CompMarketCap> comps = new ArrayList<>();


        return comps;
    }

    @GetMapping("/divdates") //Company names, symbol and dividend date. Order by date closest to current date. //TODO: FINISH
    public List<CompDivDate> divDates (RestTemplate restTemplate){
        List<CompDivDate> comps = new ArrayList<>();

        return comps;
    }
}
