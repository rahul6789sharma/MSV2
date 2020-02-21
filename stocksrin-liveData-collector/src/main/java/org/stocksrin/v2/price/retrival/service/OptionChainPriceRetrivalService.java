package org.stocksrin.v2.price.retrival.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.stocksrin.common.utils.AppConstant;
import org.stocksrin.common.utils.CommonUtils;
import org.stocksrin.common.utils.DateUtils;
import org.stocksrin.common.utils.NSEHolidayUtils;
import org.stocksrin.email.SendEmail;
import org.stocksrin.v2.common.model.option.Datum;
import org.stocksrin.v2.common.model.option.NSEOptionChainData;
import org.stocksrin.v2.common.model.option.OptionModel;
import org.stocksrin.v2.data.Data;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class OptionChainPriceRetrivalService extends TimerTask {

	// store data from this number of expiry in inmemory
	private static final int NUMBER_EXPIRY = 2;
	private long timeInteval5min = 300000; // 5 min

	// @Autowired
	private RestTemplate restTemplate = new RestTemplate();

	private static final Logger log = LoggerFactory.getLogger(OptionChainPriceRetrivalService.class);

	@Override
	public void run() {
		try {
			Runnable task5min = () -> {
				startTask(timeInteval5min);
			};
			new Thread(task5min).start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void startTask(long sleeTimeInteval) {
		task("NIFTY");
		task("BANKNIFTY");
		// if (true) {
		if (!DateUtils.isWeekEndDay() && !NSEHolidayUtils.isHoliday()) {
			// handle null data over weekend and after market time
			// deployment
			Data.clean();
			task("NIFTY");
			task("BANKNIFTY");
			//int count = 1;
			while (CommonUtils.getEveningTime()) {
				try {
					Thread.sleep(sleeTimeInteval);
					//count++;
					task("NIFTY");
					task("BANKNIFTY");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			log.info("its off today");
			if (NSEHolidayUtils.isHoliday()) {
				SendEmail.sentMail("Market is closed Today", "Take rest", "Live Data Collector");
			}
		}
	}
	/*
	 * @Autowired private RestTemplate restTemplate = new RestTemplate();
	 */

	public void task(String symbole) {
		log.info("NSE Data Retrival Start...");
		String url = AppConstant.OPTION_BASE_URL_V2 + symbole;

		HttpHeaders headers = new HttpHeaders();
		headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36");
		headers.set("Accept-Language", "en-US,en;q=0.9,es;q=0.8,pt;q=0.7");
		// headers.set("Accept-Encoding", "gzip, deflate, br");
		headers.set("Accept", "application/json; charset=utf-8");
		headers.set("Cookie",
				"74EDEAFE789812D7FF0D24771DEABECD~MBA/NpvD4f9pyxmiFjMlUBTQYF4blmFjbNZL1bCIWBCQwJY2HphgdLq2wkorojKmjvRG+eFFYwXw1EmqfnmuPJvTTSt17fUMVGv80x3CLw2daBG+tCuBFubmR7AJPs3CiB2MTP9ZxZ+yPT6SrpRHsNFfi9qP7Ynxv/EGeoZOfoc=");
		List<MediaType> mediaTypes = new ArrayList<>();
		mediaTypes.add(MediaType.APPLICATION_JSON);
		headers.setAccept(mediaTypes);

		HttpEntity request = new HttpEntity(headers);
		ResponseEntity<NSEOptionChainData> response = restTemplate.exchange(url, HttpMethod.GET, request, NSEOptionChainData.class, 1);
		// ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
		log.info(response.getStatusCode().toString());

		if (symbole.equalsIgnoreCase("NIFTY")) {
			Map<String, OptionModel> data = convert(symbole, response.getBody());
			Data.updateNiftyData(data);
			List<String> expiry = getExpiryList(response.getBody());
			Data.updateExpiry(expiry);
			filter(data.get(expiry.get(0)));
			log.info(data.get(expiry.get(0)).getSymbole() + " : " + data.get(expiry.get(0)).getUnderlyingValue());
		} else if (symbole.equalsIgnoreCase("BANKNIFTY")) {
			Map<String, OptionModel> data = convert(symbole, response.getBody());
			Data.updateBNFData(data);
			log.info(data.get(Data.shortedExpiry.get(0)).getSymbole() + ":" + data.get(Data.shortedExpiry.get(0)).getUnderlyingValue());
		}

	}

	private List<String> getExpiryList(NSEOptionChainData nseOptionChainData) {
		return nseOptionChainData.getRecords().getExpiryDates();
	}

	private Map<String, OptionModel> convert(String symbole, NSEOptionChainData nseOptionChainData) {

		Map<String, List<Datum>> data = new HashMap<>();
		Map<String, OptionModel> result = new HashMap<>();

		List<String> expiryDates = nseOptionChainData.getRecords().getExpiryDates();

		List<Datum> datalst = nseOptionChainData.getRecords().getData();
		for (int j = 0; j <= NUMBER_EXPIRY; j++) {
			data.put(expiryDates.get(j), new ArrayList<>());
		}

		for (String string : expiryDates) {
			if (data.containsKey(string)) {
				for (Datum datum : datalst) {
					if (datum.getExpiryDate().equalsIgnoreCase(string)) {
						data.get(string).add(datum);
					}
				}
			}
		}

		Set<String> keys2 = data.keySet();
		for (String string : keys2) {
			OptionModel optionModel = new OptionModel(symbole);
			optionModel.setTimestamp(nseOptionChainData.getRecords().getTimestamp());
			optionModel.setUnderlyingValue(nseOptionChainData.getRecords().getUnderlyingValue());
			optionModel.setDatums(data.get(string));
			optionModel.setExpiryDate(string);
			result.put(string, optionModel);
		}

		return result;
	}

	private List<Integer> strikes = new ArrayList<>();
	private int count = 0;

	// need to fix strikes in morning only
	// store data only for +-300 from spot
	public void filter(OptionModel optionModel) {
		double spot = optionModel.getUnderlyingValue();
		List<Datum> data = optionModel.getDatums();
		OptionModel optionModelResult = new OptionModel(optionModel.getSymbole());
		optionModelResult.setExpiryDate(optionModel.getExpiryDate());
		optionModelResult.setTimestamp(optionModel.getTimestamp());
		optionModelResult.setUnderlyingValue(optionModel.getUnderlyingValue());
		List<Datum> lst = new ArrayList<>();

		if (count == 0) {
			for (Datum datum : data) {
				double diff = Math.abs(spot - datum.getStrikePrice());
				// fixing strikes
				if (diff < 200) {
					strikes.add(datum.getStrikePrice());
				}
			}
		}

		count++;
		for (Datum datum : data) {

			if (strikes.contains(datum.getStrikePrice())) {
				lst.add(datum);
			}
		}

		optionModelResult.setDatums(lst);
		Data.niftyIntraDay.add(optionModelResult);
		//List<Datum> lst2 = optionModelResult.getDatums();
		/*
		 * for (Datum datum : lst2) { System.out.println(datum.getStrikePrice());
		 * System.out.println(" Selcted strike price : " + datum.getStrikePrice());
		 * System.out.println(datum.getCE()); System.out.println(datum.getPE()); }
		 */
	}

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		OptionChainPriceRetrivalService obj = new OptionChainPriceRetrivalService();
		obj.task("NIFTY");
		obj.task("BANKNIFTY");
		// OptionChainPriceRetrivalService.read();
	}
}
