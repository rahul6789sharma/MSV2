package org.stocksrin.collector.option.data.utils;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.stocksrin.common.model.future.Future;
import org.stocksrin.common.utils.AppConstant;
import org.stocksrin.common.utils.html.CommonHTMLDocParsher;
import org.stocksrin.common.utils.html.HTMLPageDocumentDownloader;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FutureDataCollector {

	public static void main(String[] args) {
		getFutureData("TCS", "27FEB2012", AppConstant.FUTSTK);
	}

	public static Future getFutureData(String symbole, String expiry, String insturmentType) {
		try {
			String url = AppConstant.getFutureURL(symbole, expiry, insturmentType);
			//System.out.println(url);
			Document doc = HTMLPageDocumentDownloader.getDocument(url);
			String file = CommonHTMLDocParsher.div(doc, "responseDiv");
			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

			try {
				Future data = mapper.readValue(file, Future.class);
				//System.out.println(data);
				return data;
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}

}
