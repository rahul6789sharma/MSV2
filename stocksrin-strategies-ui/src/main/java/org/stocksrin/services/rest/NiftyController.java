package org.stocksrin.services.rest;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.stocksrin.common.model.option.IntraDayOidata;
import org.stocksrin.common.model.option.OptionModle;
import org.stocksrin.common.model.option.OptionModles;
import org.stocksrin.common.model.trade.Strategy;
import org.stocksrin.common.utils.ComparatorBasedOnDate;
import org.stocksrin.services.rest.BNFController;

@RestController
@RequestMapping({ "/nifty" })
public class NiftyController {
	private static final Logger log = LoggerFactory.getLogger(BNFController.class);

	@Value("${microservice.liveData.url}")
	private String uri;

	@Value("${microservice.strategies-builder.url}")
	private String uri_strategies;

	private RestTemplate restTemplate = new RestTemplate();

	@GetMapping({ "/currentMonthOptionChain2" })
	public Map<String, OptionModles> getNiftyWeeklyOptionChain2() throws Exception {
		String url = String.valueOf(this.uri) + "/nifty/shortedExpiry/";
		Set<String> expiry = (Set<String>) this.restTemplate.getForObject(url, SortedSet.class, new Object[0]);
		SortedSet<String> expirySet = new TreeSet<>((Comparator<? super String>) new ComparatorBasedOnDate());
		expirySet.addAll(expiry);
		String currentExpiry = expirySet.first();
		String url2 = String.valueOf(this.uri) + "/nifty/optionModel/" + "27JUN2019";
		OptionModles data = (OptionModles) this.restTemplate.getForObject(url2, OptionModles.class, new Object[0]);
		List<OptionModle> optionData = data.getOptionModle();
		double lastCallStrike = 0.0D;
		double firstPutStrike = 0.0D;
		int oilimit = 1000000;
		int i = 0;
		for (OptionModle optionModle : optionData) {
			if (optionModle != null) {
				if (optionModle.getC_oi() != null && optionModle.getC_oi().intValue() > oilimit)
					lastCallStrike = optionModle.getStrike_price().doubleValue();
				if (optionModle.getP_oi() != null && optionModle.getP_oi().intValue() > oilimit && i == 0) {
					firstPutStrike = optionModle.getStrike_price().doubleValue();
					i++;
				}
			}
		}
		double firstPutStrike1 = firstPutStrike;
		double lastCallStrike1 = lastCallStrike;
		/*List<OptionModle> optionDataResult = (List<OptionModle>) optionData.stream()
				.filter(k -> (k.getStrike_price().doubleValue() >= paramDouble))
				.filter(j -> (j.getStrike_price().doubleValue() <= paramDouble)).collect(Collectors.toList());
		
		data.setOptionModle(optionDataResult);*/
		System.out.println("lastCallStrike " + lastCallStrike);
		System.out.println("firstPutStrike " + firstPutStrike);
		Map<String, OptionModles> result = new HashMap<>();
		result.put(currentExpiry, data);
		return result;
	}

	@GetMapping({ "/currentMonthOptionChain" })
	public Map<String, OptionModles> getNiftyWeeklyOptionChain() throws Exception {
		String url = String.valueOf(this.uri) + "/nifty/shortedExpiry/";
		Set<String> expiry = (Set<String>) this.restTemplate.getForObject(url, SortedSet.class, new Object[0]);
		SortedSet<String> expirySet = new TreeSet<>((Comparator<? super String>) new ComparatorBasedOnDate());
		expirySet.addAll(expiry);
		String currentExpiry = expirySet.first();
		String url2 = String.valueOf(this.uri) + "/nifty/optionModel/" + currentExpiry;
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

	@GetMapping({ "/strategiesIntraDay" })
	public Map<String, Strategy> getIntraDayStrategyResult() {
		Map<String, Strategy> result = new HashMap<>();
		String url = String.valueOf(this.uri_strategies) + "strategiesIntraDay";
		try {
			result = (Map<String, Strategy>) this.restTemplate.getForObject(url, Map.class, new Object[0]);
		} catch (Exception e) {
			log.error("Error, URL  " + url + " : " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@GetMapping({ "/intraDayOptionChain" })
	public IntraDayOidata intraDayOptionChain() {
		String url2 = String.valueOf(this.uri) + "/nifty/intaDayOptionChain";
		IntraDayOidata data = (IntraDayOidata) this.restTemplate.getForObject(url2, IntraDayOidata.class,
				new Object[0]);
		return data;
	}

	@GetMapping({ "/intraDayOptionChain/{delayDataInMins}" })
	public IntraDayOidata intraDayOptionChain2(@PathVariable("delayDataInMins") int delayDataInMins) {
		System.out.println(delayDataInMins);
		String url2 = String.valueOf(this.uri) + "/intaDayService/intaDayOptionChain/" + delayDataInMins;
		System.out.println(url2);
		IntraDayOidata data = (IntraDayOidata) this.restTemplate.getForObject(url2, IntraDayOidata.class,
				new Object[0]);
		return data;
	}
}