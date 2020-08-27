package org.stocksrin.v2.price.retrival.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.stocksrin.collector.option.data.utils.Calculation;
import org.stocksrin.common.model.option.MaxPains;
import org.stocksrin.common.utils.AppConstant;
import org.stocksrin.common.utils.CommonUtils;
import org.stocksrin.common.utils.DateUtils;
import org.stocksrin.common.utils.NSEHolidayUtils;
import org.stocksrin.email.SendEmail;
import org.stocksrin.v2.common.model.future.Future;
import org.stocksrin.v2.common.model.future.Stock;
import org.stocksrin.v2.common.model.option.Datum;
import org.stocksrin.v2.common.model.option.IntraDayOptionModel;
import org.stocksrin.v2.common.model.option.NSEOptionChainData;
import org.stocksrin.v2.common.model.option.OptionModel;
import org.stocksrin.v2.data.Data;

import java.util.*;

public class OptionChainPriceRetrivalTask extends TimerTask {

    private boolean isForEveningDownLoad;

    //
    public OptionChainPriceRetrivalTask(boolean isForEveningDownLoad) {
        this.isForEveningDownLoad = isForEveningDownLoad;
    }

    // store data from this number of expiry in inmemory
    private static final int NUMBER_EXPIRY = 5;
    private long timeInteval5min = 300000; // 5 min

    private List<Integer> strikes = new ArrayList<>();

    private RestTemplate restTemplate = new RestTemplate();

    private static final Logger log = LoggerFactory.getLogger(OptionChainPriceRetrivalTask.class);

    @Override
    public void run() {
        try {
            log.info("Starting option Chain Retrival Service... " + DateUtils.getTodayDateTime());
            if (!DateUtils.isWeekEndDay() && !NSEHolidayUtils.isHoliday()) {
                if (!isForEveningDownLoad) {
                    Data.clean();
                }
                Runnable task5min = () -> {
                    startTask(timeInteval5min, "NIFTY");
                };
                new Thread(task5min).start();

                Runnable task5min2 = () -> {
                    startTask(timeInteval5min, "BANKNIFTY");
                };
                new Thread(task5min2).start();
            } else {
                log.info(" its off today ");
                if (NSEHolidayUtils.isHoliday()) {
                    SendEmail.sentMail("Market is closed Today", "Take rest", "Live Data Collector");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startTask(long sleepTimeInteval, String symbole) {

        // if (true) {

        // handle null data over weekend and after market time
        // deployment

        update(symbole);
        // int count = 1;
        while (CommonUtils.getEveningTime()) {
            try {
                Thread.sleep(sleepTimeInteval);
                // count++;
                update(symbole);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /*
     * @Autowired private RestTemplate restTemplate = new RestTemplate();
     */

    public void update(String symbole) {
        Future future = FutureRetrivalService.task(symbole);
        Map<String, Double> futurePrice = new HashMap<>();
        List<Stock> s = future.getStocks();
        for (Stock stock : s) {
            futurePrice.put(stock.getMetadata().getExpiryDate(), stock.getMetadata().getLastPrice());
        }
        optionpriceretrivalTask(symbole, futurePrice);
    }

    public void optionpriceretrivalTask(String symbole, Map<String, Double> futurePrice) {
        log.info(symbole + " Data Retriving..." + DateUtils.getTodayDateTime());
        String url = AppConstant.OPTION_BASE_URL_V2 + symbole;

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36");
        headers.set("Accept-Language", "en-US,en;q=0.9,es;q=0.8,pt;q=0.7");
        // headers.set("Accept-Encoding", "gzip, deflate, br");
        headers.set("Accept", "application/json; charset=utf-8");
        headers.set("Cookie",
                "74EDEAFE789812D7FF0D24771DEABECD~MBA/NpvD4f9pyxmiFjMlUBTQYF4blmFjbNZL1bCIWBCQwJY2HphgdLq2wkorojKmjvRG+eFFYwXw1EmqfnmuPJvTTSt17fUMVGv80x3CLw2daBG+tCuBFubmR7AJPs3CiB2MTP9ZxZ+yPT6SrpRHsNFfi9qP7Ynxv/EGeoZOfoc=");
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        headers.setAccept(mediaTypes);

        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<NSEOptionChainData> response = restTemplate.exchange(url, HttpMethod.GET, request,
                NSEOptionChainData.class, 1);
        // ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
        log.info("status code " + response.getStatusCode().toString());

        if (symbole.equalsIgnoreCase("NIFTY")) {
            Map<String, OptionModel> data = convert(symbole, response.getBody(), futurePrice);

            data.forEach((i, j) -> {
                MaxPains maxpain = Calculation.calMaxPain(j, 100);
                j.setMaxPain(maxpain.getMaxPainStrick());
            });

            Data.updateNiftyData(data);
            List<String> expiry = getExpiryList(response.getBody());
            Data.updateExpiry(expiry);
            filter(data.get(expiry.get(0)));
            if (!expiry.isEmpty()) {
                log.info(data.get(expiry.get(0)).getSymbol() + " : " + data.get(expiry.get(0)).getUnderlyingValue() + ", at: " + data.get(expiry.get(0)).getTimestamp());
            }

        } else if (symbole.equalsIgnoreCase("BANKNIFTY")) {
            Map<String, OptionModel> data = convert(symbole, response.getBody(), futurePrice);
            Data.updateBNFData(data);
            if (!Data.shortedExpiry.isEmpty()) {
                log.info(data.get(Data.shortedExpiry.get(0)).getSymbol() + ":" + data.get(Data.shortedExpiry.get(0)).getUnderlyingValue() + ", at " + data.get(Data.shortedExpiry.get(0)).getTimestamp());
            }
        }

    }

    private List<String> getExpiryList(NSEOptionChainData nseOptionChainData) {
        return nseOptionChainData.getRecords().getExpiryDates();
    }

    private Map<String, OptionModel> convert(String symbol, NSEOptionChainData nseOptionChainData,
                                             Map<String, Double> futurePrice) {

        Map<String, List<Datum>> data = new HashMap<>();
        Map<String, OptionModel> result = new HashMap<>();

        List<String> expiryDates = nseOptionChainData.getRecords().getExpiryDates();

        List<Datum> datalst = nseOptionChainData.getRecords().getData();

        if (!isForEveningDownLoad) {
            for (int j = 0; j <= NUMBER_EXPIRY; j++) {
                data.put(expiryDates.get(j), new ArrayList<>());
            }
        } else {
            String currentyr = DateUtils.getCurrentYear();
            for (String d : expiryDates) {
                if (d.contains(currentyr)) {
                    data.put(d, new ArrayList<>());
                }
            }
        }

        for (String string : expiryDates) {
            if (data.containsKey(string)) {
                for (Datum datum : datalst) {
                    if (datum.getExpiryDate().equalsIgnoreCase(string)) {
                        // System.out.println(datum.getCE().getImpliedVolatility());
                        data.get(string).add(datum);
                    }
                }
            }
        }

        Set<String> keys2 = data.keySet();
        for (String string : keys2) {
            OptionModel optionModel = new OptionModel(symbol);
            optionModel.setFutureValue(futurePrice);
            optionModel.setTimestamp(nseOptionChainData.getRecords().getTimestamp());
            optionModel.setUnderlyingValue(nseOptionChainData.getRecords().getUnderlyingValue());
            optionModel.setDatums(data.get(string));
            optionModel.setExpiryDate(string);
            result.put(string, optionModel);
        }


        return result;
    }

    // need to fix strikes in morning only for ss graph
    // store data only for +-300 from spot
    public void filter(OptionModel optionModel) {
        double spot = optionModel.getUnderlyingValue();
        List<Datum> data = optionModel.getDatums();
        IntraDayOptionModel optionModelResult = new IntraDayOptionModel(optionModel.getSymbol());
        optionModelResult.setExpiryDate(optionModel.getExpiryDate());
        optionModelResult.setTimestamp(optionModel.getTimestamp());
        optionModelResult.setUnderlyingValue(optionModel.getUnderlyingValue());
        List<Datum> lst = new ArrayList<>();
        updateIntraDay(optionModelResult, data);
        for (Datum datum : data) {
            double diff = Math.abs(spot - datum.getStrikePrice());
            // fixing strikes
            if (diff < 500) {
                if (datum.getStrikePrice() % 100 == 0) {
                    strikes.add(datum.getStrikePrice());
                }
            }
        }

        for (Datum datum : data) {
            if (strikes.contains(datum.getStrikePrice())) {
                lst.add(datum);
            }
        }

        optionModelResult.setDatums(lst);
        // TODO

        Data.niftyIntraDay.add(optionModelResult);
        // List<Datum> lst2 = optionModelResult.getDatums();
        /*
         * for (Datum datum : lst2) { System.out.println(datum.getStrikePrice());
         * System.out.println(" Selcted strike price : " + datum.getStrikePrice());
         * System.out.println(datum.getCE()); System.out.println(datum.getPE()); }
         */
    }

    private static void updateIntraDay(IntraDayOptionModel intraDayOptionModel, List<Datum> data) {

        Map<Integer, Integer> totalOI = new HashMap<>();

        Map<Integer, Integer> changeOI_ce = new HashMap<>();
        Map<Integer, Integer> changeOI_pe = new HashMap<>();

        Map<Integer, Integer> OI_ce = new HashMap<>();
        Map<Integer, Integer> OI_pe = new HashMap<>();

        for (Datum datum : data) {
            datum.getStrikePrice();
            totalOI.put(datum.getStrikePrice(), datum.getCE().getOpenInterest() + datum.getPE().getOpenInterest());
            changeOI_ce.put(datum.getStrikePrice(), datum.getCE().getChangeinOpenInterest());
            changeOI_pe.put(datum.getStrikePrice(), datum.getPE().getChangeinOpenInterest());

            OI_ce.put(datum.getStrikePrice(), datum.getCE().getOpenInterest());
            OI_pe.put(datum.getStrikePrice(), datum.getPE().getOpenInterest());

        }
        System.out.println("************************");
        Integer key = Collections.max(totalOI.entrySet(), Map.Entry.comparingByValue()).getKey();
        Integer key2 = Collections.max(changeOI_ce.entrySet(), Map.Entry.comparingByValue()).getKey();
        Integer key3 = Collections.max(changeOI_pe.entrySet(), Map.Entry.comparingByValue()).getKey();

        Integer key4 = Collections.max(OI_ce.entrySet(), Map.Entry.comparingByValue()).getKey();
        Integer key5 = Collections.max(OI_pe.entrySet(), Map.Entry.comparingByValue()).getKey();

        Map<Integer, Integer> setMaxOIs = new HashMap<>(1);
        setMaxOIs.put(key, totalOI.get(key));
        intraDayOptionModel.setMaxOIs(setMaxOIs);

        Map<Integer, Integer> MaxChangeCEOI = new HashMap<>(1);
        MaxChangeCEOI.put(key2, changeOI_ce.get(key2));
        intraDayOptionModel.setMaxChangeCEOI(MaxChangeCEOI);

        Map<Integer, Integer> MaxChangePEOI = new HashMap<>(1);
        MaxChangePEOI.put(key3, changeOI_pe.get(key3));
        intraDayOptionModel.setMaxChangePEOI(MaxChangePEOI);

        Map<Integer, Integer> maxCEOI = new HashMap<>(1);
        maxCEOI.put(key4, OI_ce.get(key4));
        intraDayOptionModel.setMaxCEOI(maxCEOI);

        Map<Integer, Integer> maxPEOI = new HashMap<>(1);
        maxPEOI.put(key5, OI_pe.get(key5));
        intraDayOptionModel.setMaxPEOI(maxPEOI);

        //System.out.println("Put Max " + key3 + " value " + changeOI_pe.get(key3));
    }

    public static void main(String[] args) {
        OptionChainPriceRetrivalTask OptionChainPriceRetrivalService = new OptionChainPriceRetrivalTask(false);
        OptionChainPriceRetrivalService.run();
    }
    /*
     * public static void main(String[] args) throws Exception {
     *
     * List<StrikePrice> lst = new ArrayList<>();
     *
     * List<OptionModel> dat =
     * IntraDayOptionChainJosnReader.getNiftyOptionChain("30-Jan-2020",
     * "30-Jan-2020", false); for (OptionModel optionModel : dat) { List<Datum> d =
     * optionModel.getDatums(); StrikePrice strikePrice = new StrikePrice();
     * strikePrice.setTime(optionModel.getTimestamp());
     * strikePrice.setUnderlyingPice(optionModel.getUnderlyingValue()); for (Datum
     * item : d) { if (item.getCE().getStrikePrice() == 12050) {
     * strikePrice.setCeStrike(item.getCE().getStrikePrice());
     * strikePrice.setCePrice(item.getCE().getLastPrice()); }
     *
     * if (item.getPE().getStrikePrice() == 12150) {
     * strikePrice.setPeStrike(item.getPE().getStrikePrice());
     * strikePrice.setPePrice(item.getPE().getLastPrice()); } }
     * lst.add(strikePrice);
     *
     * }
     *
     * for (StrikePrice item : lst) { System.out.println(item.getCePrice() +
     * item.getPePrice()); } }
     */
}

class StrikePrice {

    String expiry;
    String date;
    String time;
    Integer ceStrike;
    double cePrice;
    Integer peStrike;
    double pePrice;
    double underlyingPice;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getCeStrike() {
        return ceStrike;
    }

    public void setCeStrike(Integer ceStrike) {
        this.ceStrike = ceStrike;
    }

    public double getCePrice() {
        return cePrice;
    }

    public void setCePrice(double cePrice) {
        this.cePrice = cePrice;
    }

    public Integer getPeStrike() {
        return peStrike;
    }

    public void setPeStrike(Integer peStrike) {
        this.peStrike = peStrike;
    }

    public double getPePrice() {
        return pePrice;
    }

    public void setPePrice(double pePrice) {
        this.pePrice = pePrice;
    }

    public double getUnderlyingPice() {
        return underlyingPice;
    }

    public void setUnderlyingPice(double underlyingPice) {
        this.underlyingPice = underlyingPice;
    }

    @Override
    public String toString() {
        return "StrikePrice [expiry=" + expiry + ", date=" + date + ", time=" + time + ", ceStrike=" + ceStrike
                + ", cePrice=" + cePrice + ", peStrike=" + peStrike + ", pePrice=" + pePrice + ", underlyingPice="
                + underlyingPice + "]";
    }

}