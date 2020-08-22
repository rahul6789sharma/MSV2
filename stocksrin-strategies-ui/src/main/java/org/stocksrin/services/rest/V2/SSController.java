package org.stocksrin.services.rest.V2;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.stocksrin.v2.common.model.option.OptionModel;
import org.stocksrin.v2.common.model.option.SSEntity;

@RestController
@RequestMapping({ "/ssRestController" })
public class SSController {

	private static final Logger log = LoggerFactory.getLogger(SSController.class);

	@Value("${microservice.liveData.url}")
	private String uri;

	private RestTemplate restTemplate = new RestTemplate();

	@GetMapping({ "/data" })
	public List<SSEntity> getIntraDayData() throws Exception {
		String url2 = String.valueOf(this.uri) + "/ssRestController/data/";
		List<SSEntity> data = (List<SSEntity>) this.restTemplate.getForObject(url2, List.class, new Object[0]);
		return data;
	}
}
