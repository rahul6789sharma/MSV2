package org.stocksrin.services.rest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.stocksrin.services.rest.AbrComparator;
import org.stocksrin.v2.common.model.future.EntityABR;
import org.stocksrin.v2.common.model.option.OptionModel;

@RestController
@RequestMapping({"/optionChainRestController"})
public class OptionChainRestController {
  @Value("${microservice.liveData.url}")
  private String uri;
  
  private RestTemplate restTemplate = new RestTemplate();
  
  @GetMapping({"/optionChain"})
  public List<OptionModel> optionChain(@RequestParam("expiry") String expiry, @RequestParam("date") String date) {
    String url = String.valueOf(this.uri) + "/optionChainService/optionChain?expiry=" + expiry + "&date=" + date;
    System.out.println("url" + url);
    List<OptionModel> response = (List<OptionModel>)this.restTemplate.getForObject(url, List.class, new Object[0]);
    return response;
  }
  
  @GetMapping({"/abr"})
  public Map<String, EntityABR> abr() {
    String url = String.valueOf(this.uri) + "arb/data";
    Map<String, EntityABR> response = (Map<String, EntityABR>)this.restTemplate.getForObject(url, Map.class, new Object[0]);
    Set<String> keys = response.keySet();
    SortedSet<EntityABR> result = new TreeSet<>((Comparator<? super EntityABR>)new AbrComparator());
    List<EntityABR> lst = new ArrayList<>();
    for (String str : keys);
    return response;
  }
}
