package org.stocksrin.restclient;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import org.stocksrin.common.model.option.OptionModles;
import org.stocksrin.common.utils.ComparatorBasedOnDate;

@Controller
public class BNFConsumeWebService {
  private static final Logger log = LoggerFactory.getLogger(org.stocksrin.restclient.BNFConsumeWebService.class);
  
  @Value("${microservice.liveData.url}")
  private String uri;
  
  private RestTemplate restTemplate = new RestTemplate();
  
  public Map<String, OptionModles> getBankNiftyWeeklyOptionChain() throws Exception {
    String url = String.valueOf(this.uri) + "/bnf/bankNiftyWeeklyOptionChain/";
    Map<String, OptionModles> data = (Map<String, OptionModles>)this.restTemplate.getForObject(url, Map.class, new Object[0]);
    return data;
  }
  
  public SortedSet<String> getAllExpiry() {
    String url = String.valueOf(this.uri) + "/bnf/shortedExpiry";
    Set<String> data = (Set<String>)this.restTemplate.getForObject(url, Set.class, new Object[0]);
    SortedSet<String> result = new TreeSet<>((Comparator<? super String>)new ComparatorBasedOnDate());
    result.addAll(data);
    return result;
  }
  
  public OptionModles getOptionModel(String expiry) {
    String url = String.valueOf(this.uri) + "/bnf/optionModel/" + expiry;
    OptionModles data = (OptionModles)this.restTemplate.getForObject(url, OptionModles.class, new Object[0]);
    return data;
  }
  
  public Double getSpotPrice() {
    String url = String.valueOf(this.uri) + "/bnf/spotPrice";
    Double data = (Double)this.restTemplate.getForObject(url, Double.class, new Object[0]);
    return data;
  }
}
