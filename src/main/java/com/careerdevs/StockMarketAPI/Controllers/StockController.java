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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    @Autowired
    Environment env;

    private String getURL(String symbol)/*Simplifies the process of getting symbol and adding the api key to the URL*/ {
        return "https://www.alphavantage.co/query?function=OVERVIEW&symbol=" + symbol + "&=apikey=" + env.getProperty("api.key");
    }

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");

    @GetMapping("/feature1") //Symbols and Name sort by Name INTERNAL
    public ArrayList<CompCSV> getAll(RestTemplate restTemplate) {
        ArrayList<CompCSV> tempCsvData = StockCSVParcer.readCSV();

        assert tempCsvData != null;
        tempCsvData.sort(Comparator.comparing(CompCSV::getName));

        ArrayList<CompCSV> sortedData = new ArrayList<>();
        for (CompCSV compData : tempCsvData) {
            CompCSV tempData = DataConverter.convertFeature1(compData);

            sortedData.add(tempData);
        }

        return sortedData;
    }

    @GetMapping("/feature2") //Gets company names and IPO data(in ascending order). INTERNAL
    public ArrayList<CompCSV> companyIpoData(RestTemplate restTemplate) throws ParseException {
        ArrayList<CompCSV> tempCsvData = StockCSVParcer.readCSV();

        assert tempCsvData != null;
        tempCsvData.sort(Comparator.comparing(CompCSV :: getIpoDate));

        ArrayList<CompCSV> sortedData = new ArrayList<>();
        for (CompCSV compData : tempCsvData) {
            CompCSV tempData = DataConverter.convertFeature2(compData);

            sortedData.add(tempData);
        }

        return sortedData;
    }

    @GetMapping("/feature3/{stock}") //companies that traded on NASDAQ. INTERNAL //TEST
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
        } catch (Exception e) {
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

    @GetMapping("/feature5") //Include Symbol, AssetType, Name, Description, and AddressEXTERNAL
    public ArrayList<CompAV> overview(RestTemplate restTemplate) {
        ArrayList<CompCSV> tempData = StockCSVParcer.readCSV();
        ArrayList<CompAV> compOverview = new ArrayList<>();

        try {
            for (CompCSV compData : tempData) {
                CompAV comp = restTemplate.getForObject(getURL(compData.getSymbol()), CompAV.class);

                assert comp != null;

                CompAV trimmedData = DataConverter.convertFeature5(comp);
                compOverview.add(trimmedData);

            }
            return compOverview;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/feature6")//Companies name, symbol, and market cap (high to low) EXTERNAL//TODO: STILL NEEDS WORK
    public ArrayList<CompAV> highLowMarketCap(RestTemplate restTemplate) {
        ArrayList<CompCSV> tempData = StockCSVParcer.readCSV();
        ArrayList<CompAV> comps = new ArrayList<>();


        for (CompCSV compData : tempData) {
            CompAV apiComps = restTemplate.getForObject(getURL(compData.getSymbol()), CompAV.class);
            CompAV trimmedData = DataConverter.convertFeature6(apiComps);
            comps.add(trimmedData);
        }
        assert comps != null;
        comps.sort(new StockComparator.sortMarketCap());

        return comps;
    }

    @GetMapping("/feature7")
    //Company names, symbol and dividend date. Order by date closest to current date. EXTERNAL //TODO: FINISH
    public ArrayList<CompAV> divDates(RestTemplate restTemplate) {
        ArrayList<CompCSV> tempCompArr = StockCSVParcer.readCSV();
        ArrayList<CompAV> comps = new ArrayList<>();

        for (CompCSV compData : tempCompArr) {
            CompAV apiComps = restTemplate.getForObject(getURL(compData.getSymbol()), CompAV.class);
            assert apiComps != null;

            CompAV trimmedData = DataConverter.convertFeature7(apiComps);
            comps.add(trimmedData);
        }

        comps.sort(new StockComparator.sortByDivDate());
        Collections.reverse(comps);

        return comps;
    }
}
