package org.stocksrin.services.rest.V2;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.stocksrin.services.rest.BNFController;
import org.stocksrin.v2.common.model.oi.ParticapentOIData;

@RestController
@RequestMapping({ "/v2/oi" })
public class OIController2 {

	private static final Logger log = LoggerFactory.getLogger(BNFController.class);

	@Value("${microservice.liveData.url}")
	private String uri;

	private RestTemplate restTemplate = new RestTemplate();

	@GetMapping({ "/data" })
	public List<ParticapentOIData> getStrategyResult() {
		List<ParticapentOIData> result = new ArrayList<>();
		String url = String.valueOf(this.uri) + "/v2/oi/data";
		try {
			System.out.println("url : " + url);
			result = this.restTemplate.getForObject(url, List.class, new Object[0]);
		} catch (Exception e) {
			log.error("Error, URL  " + url + " : " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}
