package org.stocksrin.services.rest.V2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.stocksrin.v2.common.model.option.OptionModel;
import org.stocksrin.v2.common.model.option.SSEntity;

@RestController
@RequestMapping({ "/v2/nifty" })
public class NiftyController2 {
	private static final Logger log = LoggerFactory.getLogger(NiftyController2.class);

	@Value("${microservice.liveData.url}")
	private String uri;

	private RestTemplate restTemplate = new RestTemplate();

	@GetMapping("/currentWeekExpiry")
	public Map<String, OptionModel> getNiftyWeeklyOptionChain() throws Exception {
		String url = String.valueOf(this.uri) + "/niftydata/shortedExpiry";
		List<String> expiry = (List<String>) this.restTemplate.getForObject(url, List.class, new Object[0]);
		String currentExpiry = expiry.get(0);
		String url2 = String.valueOf(this.uri) + "/niftydata/optionModel/" + currentExpiry;
		OptionModel data = (OptionModel) this.restTemplate.getForObject(url2, OptionModel.class, new Object[0]);
		Map<String, OptionModel> result = new HashMap<>();
		result.put(currentExpiry, data);
		return result;
	}

	@GetMapping("/optionchain/{expiry}")
	public Map<String, OptionModel> getOptionChain(@PathVariable("expiry") String expiry) throws Exception {
		System.out.println("Current expiry " + expiry);
		String url2 = String.valueOf(this.uri) + "/niftydata/optionModel/" + expiry;
		OptionModel data = (OptionModel) this.restTemplate.getForObject(url2, OptionModel.class, new Object[0]);
		Map<String, OptionModel> result = new HashMap<>();
		result.put(expiry, data);
		return result;
	}

	@GetMapping("/intraDayData")
	public List<OptionModel> getIntraDayData() throws Exception {
		String url2 = String.valueOf(this.uri) + "/niftydata/intraDayData/";
		List<OptionModel> data = (List<OptionModel>) this.restTemplate.getForObject(url2, List.class, new Object[0]);
		return data;
	}
}
