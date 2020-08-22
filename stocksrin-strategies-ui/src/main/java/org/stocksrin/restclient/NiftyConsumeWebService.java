package org.stocksrin.restclient;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import org.stocksrin.common.model.option.OptionModles;
import org.stocksrin.common.model.option.OptionType;
import org.stocksrin.common.utils.ComparatorBasedOnDate;

@Controller
public class NiftyConsumeWebService {

	@Autowired
	private Environment env;

	public SortedSet<String> getAllExpiry() {
		String uri = this.env.getProperty("microservice.liveData.url");
		uri = String.valueOf(uri) + "/nifty/shortedExpiry";
		RestTemplate restTemplate = new RestTemplate();
		Set<String> data = (Set<String>) restTemplate.getForObject(uri, Set.class, new Object[0]);
		SortedSet<String> result = new TreeSet<>((Comparator<? super String>) new ComparatorBasedOnDate());
		result.addAll(data);
		return result;
	}

	public OptionModles getOptionModel(String expiry) {
		String uri = this.env.getProperty("microservice.liveData.url");
		uri = String.valueOf(uri) + "/nifty/optionModel/" + expiry;
		RestTemplate restTemplate = new RestTemplate();
		OptionModles data = (OptionModles) restTemplate.getForObject(uri, OptionModles.class, new Object[0]);
		return data;
	}

	public Double getSpotPrice() {
		String uri = this.env.getProperty("microservice.liveData.url");
		uri = String.valueOf(uri) + "/nifty/spotPrice";
		RestTemplate restTemplate = new RestTemplate();
		Double data = (Double) restTemplate.getForObject(uri, Double.class, new Object[0]);
		return data;
	}

	public Double getOptionLtp(String expiry, double strike, OptionType optionType) {
		String uri = this.env.getProperty("microservice.liveData.url");
		uri = String.valueOf(uri) + "/nifty/optionLtp/" + expiry + "/" + strike + "/" + optionType;
		RestTemplate restTemplate = new RestTemplate();
		Double data = (Double) restTemplate.getForObject(uri, Double.class, new Object[0]);
		return data;
	}

	public Double getIV(String expiry, double strike, OptionType optionType) {
		String uri = this.env.getProperty("microservice.liveData.url");
		uri = String.valueOf(uri) + "/nifty/iv/" + expiry + "/" + strike + "/" + optionType;
		RestTemplate restTemplate = new RestTemplate();
		Double data = (Double) restTemplate.getForObject(uri, Double.class, new Object[0]);
		return data;
	}

	public String getLastDataUpdated(String expiry) {
		String uri = this.env.getProperty("microservice.liveData.url");
		uri = String.valueOf(uri) + "/nifty/lastDataUpdated/" + expiry;
		RestTemplate restTemplate = new RestTemplate();
		String data = (String) restTemplate.getForObject(uri, String.class, new Object[0]);
		return data;
	}
}
