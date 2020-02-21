package org.stocksrin.v2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.stocksrin.common.model.option.OptionType;
import org.stocksrin.v2.common.model.option.OptionModel;

@Component
@Qualifier("NiftyConsumeWebServiceV2")
@Primary
public class NiftyConsumeWebServiceV2 implements RestServiceV2 {

	@Autowired
	private Environment env;

	RestTemplate restTemplate = new RestTemplate();

	private final String PATH = "/niftydata";

	@Override
	public List<String> getAllExpiry() throws Exception {
		String uri = env.getProperty("microservice.liveData.url.v2") + PATH + "/shortedExpiry";
		try {
			List<String> data = restTemplate.getForObject(uri, List.class);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error url :" + uri);
		}
	}

	@Override
	public OptionModel getOptionModel(String expiry) throws Exception {
		String uri = env.getProperty("microservice.liveData.url.v2") + PATH + "/optionModel/" + expiry;
		try {
			OptionModel data = restTemplate.getForObject(uri, OptionModel.class);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error url :" + uri);
		}

	}

	@Override
	public Double getOptionLtp(String expiry, Integer strike, OptionType optionType) throws Exception {
		String uri = env.getProperty("microservice.liveData.url.v2");
		uri = uri + PATH + "/optionLtp/" + expiry + "/" + strike + "/" + optionType;
		try {
			Double data = restTemplate.getForObject(uri, Double.class);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error url :" + uri);
		}
	}

	@Override
	public String getLastDataUpdated(String expiry) throws Exception {
		String uri = env.getProperty("microservice.liveData.url.v2") + PATH + "/lastTimeStamp";
		try {
			String data = restTemplate.getForObject(uri, String.class);
			return data;
		} catch (Exception e) {
			// e.printStackTrace();
			throw new Exception("Error url :" + uri);
		}
	}

	@Override
	public Double getSpot() throws Exception {
		String uri = env.getProperty("microservice.liveData.url.v2") + PATH + "/spotPrice";
		try {
			Double data = restTemplate.getForObject(uri, Double.class);
			return data;
		} catch (Exception e) {
			// e.printStackTrace();
			throw new Exception("Error url :" + uri);
		}
	}
}
