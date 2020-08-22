package org.stocksrin.services.rest;


import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.stocksrin.services.rest.BNFController;
import org.stocksrin.strategy.db.model.StrategyEntity;

public class DBStrategyRestController {
  private static final Logger log = LoggerFactory.getLogger(BNFController.class);
  
  private String uri;
  
  private RestTemplate restTemplate = new RestTemplate();
  
  @GetMapping({"/intadayStrategies"})
  public List<StrategyEntity> getStrategyResult() {
    List<StrategyEntity> result = new ArrayList<>();
    String url = String.valueOf(this.uri) + "strategies/intraday";
    try {
      result = (List<StrategyEntity>)this.restTemplate.getForObject(url, List.class, new Object[0]);
    } catch (Exception e) {
      log.error("Error, URL  " + url + " : " + e.getMessage());
      e.printStackTrace();
    } 
    return result;
  }
  
  @GetMapping({"/overnightStrategies"})
  public List<StrategyEntity> getovernigthResult() {
    List<StrategyEntity> result = new ArrayList<>();
    String url = String.valueOf(this.uri) + "strategies/overnight";
    try {
      result = (List<StrategyEntity>)this.restTemplate.getForObject(url, List.class, new Object[0]);
    } catch (Exception e) {
      log.error("Error, URL  " + url + " : " + e.getMessage());
      e.printStackTrace();
    } 
    return result;
  }
}
