package org.stocksrin.v2.arbitrage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.stocksrin.v2.common.model.future.Future;
import org.stocksrin.v2.common.model.future.Metadata;
import org.stocksrin.v2.common.model.future.Stock;

public class IndexFuture {

	private static RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {
		Future future = task("NIFTY");
		System.out.println(future.getUnderlyingValue());
		List<Stock> s = future.getStocks();
		
		for (Stock stock : s) {
			System.out.println(stock.getMetadata().getExpiryDate());
			System.out.println(stock.getMetadata().getLastPrice());	
		}
		
	}

	public static Future task(String symbole) {
		String url = "https://www.nseindia.com/api/quote-derivative?symbol=" + symbole;

		try {

			HttpHeaders headers = new HttpHeaders();
			headers.set("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36");
			headers.set("Accept-Language", "en-US,en;q=0.9,es;q=0.8,pt;q=0.7");
			// headers.set("Accept-Encoding", "gzip, deflate, br");
			headers.set("Accept", "application/json; charset=utf-8");
			headers.set("Cookie",
					"74EDEAFE789812D7FF0D24771DEABECD~MBA/NpvD4f9pyxmiFjMlUBTQYF4blmFjbNZL1bCIWBCQwJY2HphgdLq2wkorojKmjvRG+eFFYwXw1EmqfnmuPJvTTSt17fUMVGv80x3CLw2daBG+tCuBFubmR7AJPs3CiB2MTP9ZxZ+yPT6SrpRHsNFfi9qP7Ynxv/EGeoZOfoc=");
			List<MediaType> mediaTypes = new ArrayList<>();
			mediaTypes.add(MediaType.APPLICATION_JSON);
			headers.setAccept(mediaTypes);

			HttpEntity request = new HttpEntity(headers);
			ResponseEntity<Future> response = restTemplate.exchange(url, HttpMethod.GET, request, Future.class, 1);

			Future future = response.getBody();

			List<Stock> stocks = future.getStocks();
			List<Stock> futureIndex = new ArrayList<>();
			for (Stock stock : stocks) {
				Metadata m = stock.getMetadata();
				if ("Index Futures".equals(m.getInstrumentType())) {
					
					futureIndex.add(stock);
				}
			}
			future.setStocks(futureIndex);
			// System.out.println(future.getStocks().size());
			return future;
		} catch (Exception e) {
			System.out.println("ERROR url :" + url);
			e.printStackTrace();
		}
		return null;
	}

}
