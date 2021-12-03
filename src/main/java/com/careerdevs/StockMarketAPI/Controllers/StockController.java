package com.careerdevs.StockMarketAPI.Controllers;

import com.careerdevs.StockMarketAPI.Models.CompAV;
import com.careerdevs.StockMarketAPI.Models.CompCSV;
import com.careerdevs.StockMarketAPI.Utility.StockCSVParcer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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
    public List<CompAV> getAll (RestTemplate restTemplate) {
        List<CompCSV> tempCsvData = StockCSVParcer.readCSV();
        List<CompAV> allCompData = new ArrayList<>();

        for(CompCSV compData : tempCsvData){
            System.out.println(compData.getSymbol());
            CompAV compAllData = restTemplate.getForObject(URL, CompAV.class);
            allCompData.add(compAllData);
        }

        return allCompData;
    }

    @GetMapping("/overviewinfo")
    public CompAV overview(RestTemplate restTemplate){
        String overviewURL = "https://www.alphavantage.co/query?function=OVERVIEW&symbol=IBM&apikey=" + env.getProperty("api.key");
        try{
        return restTemplate.getForObject(overviewURL, CompAV.class);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
