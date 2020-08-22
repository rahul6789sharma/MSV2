package org.stocksrin.v2.cash;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.stocksrin.common.utils.AppConstant;
import org.stocksrin.common.utils.CommonUtils;
import org.stocksrin.v2.arbitrage.FutureArbitrage;

public class CashPriceRetrival {

	private static RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {
		Stocks stocks =task("SBIN");
		System.out.println(stocks.getPriceInfo());
		String fnolist = AppConstant.STOCKSRIN_OPTION_DATA_DIR_STOCKS_LIST_2;
		List<String> stocsk = CommonUtils.getFNOList(fnolist);
		
		List<TradeModle> shortList= new ArrayList<>();
		List<TradeModle> longList= new ArrayList<>();
		
		for (String string : stocsk) {
			try {
				String[] item = string.split(",");
				if (!(item[1].trim().equalsIgnoreCase("SYMBOL") || item[1].trim().equals("BANKNIFTY") || item[1].trim().equals("NIFTY") || item[1].trim().equals("NIFTYIT"))) {
					System.out.println("string -> " + item[1].trim());
					Stocks s = task(item[1].trim());
					TradeModle t =ModelUtils.convert(s);
					
					if(ModelUtils.isShortValid(t)) {
						shortList.add(t);
					}
					
					if(ModelUtils.isLongValid(t)) {
						longList.add(t);
					}
				}
				//Thread.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

		System.out.println(shortList);
		System.out.println(longList);
	}
	
	public static Stocks task(String symbole) {
		String url = "https://www.nseindia.com/api/quote-equity?symbol=" + symbole;

		try {

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
			ResponseEntity<Stocks> response = restTemplate.exchange(url, HttpMethod.GET, request, Stocks.class, 1);
			
			Stocks stocks = response.getBody();
			System.out.println(stocks);
			return stocks;
		} catch (Exception e) {
			System.out.println("ERROR url :" + url);
			e.printStackTrace();
		}
		return null;
	}
}
