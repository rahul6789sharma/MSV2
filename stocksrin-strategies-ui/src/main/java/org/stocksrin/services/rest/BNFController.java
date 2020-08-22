package org.stocksrin.services.rest;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.stocksrin.common.model.option.OptionModles;
import org.stocksrin.common.model.trade.Strategy;
import org.stocksrin.common.utils.ComparatorBasedOnDate;

@RestController
@RequestMapping({ "/bnf" })
public class BNFController {
	private static final Logger log = LoggerFactory.getLogger(org.stocksrin.services.rest.BNFController.class);

	@Value("${microservice.liveData.url}")
	private String uri;

	@Value("${microservice.strategies-builder.url}")
	private String uri_strategies;

	private RestTemplate restTemplate = new RestTemplate();

	@GetMapping({ "/currentWeekOptionChain" })
	public Map<String, OptionModles> getBankNiftyWeeklyOptionChain() throws Exception {
		String url = String.valueOf(this.uri) + "/bnf/shortedExpiry/";
		Set<String> expiry = (Set<String>) this.restTemplate.getForObject(url, SortedSet.class, new Object[0]);
		SortedSet<String> expirySet = new TreeSet<>((Comparator<? super String>) new ComparatorBasedOnDate());
		expirySet.addAll(expiry);
		String currentExpiry = expirySet.first();
		String url2 = String.valueOf(this.uri) + "/bnf/optionModel/" + currentExpiry;
		OptionModles data = (OptionModles) this.restTemplate.getForObject(url2, OptionModles.class, new Object[0]);
		Map<String, OptionModles> result = new HashMap<>();
		result.put(currentExpiry, data);
		return result;
	}

	@GetMapping({ "/strategies" })
	public Map<String, Strategy> getStrategyResult() {
		Map<String, Strategy> result = new HashMap<>();
		String url = String.valueOf(this.uri_strategies) + "strategies";
		try {
			result = (Map<String, Strategy>) this.restTemplate.getForObject(url, Map.class, new Object[0]);
		} catch (Exception e) {
			log.error("Error, URL  " + url + " : " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}