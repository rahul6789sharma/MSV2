package org.stocksrin.v2.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.stocksrin.common.model.option.OptionType;
import org.stocksrin.v2.common.model.option.OptionModel;
import org.stocksrin.v2.data.Data;

@RestController
@RequestMapping("/bnfdata")
public class BNFRestController {

	@RequestMapping("/ping")
	public String ping() {
		return "BNFRestController is UP";
	}

	// http://localhost:8082/niftydata/spotPrice
	@RequestMapping("/spotPrice")
	public ResponseEntity<Double> getSpotPrice() {
		return ResponseEntity.ok(Data.getBNFSpot());
	}

	@RequestMapping("/lastTimeStamp")
	public ResponseEntity<String> lastTimeStamp() {
		return ResponseEntity.ok(Data.getBNFLastUpdatedTimestamp());
	}

	@RequestMapping("/shortedExpiry")
	public ResponseEntity<List<String>> getShortedExpiry() {
		List<String> data = Data.shortedExpiry;
		if (!data.isEmpty()) {
			return ResponseEntity.ok(data);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping("/optionModel/{expiry}")
	public ResponseEntity<OptionModel> getOptionModel(@PathVariable("expiry") String expiry) {
		OptionModel optionModel = Data.getBNFData(expiry);
		if (optionModel != null) {
			return ResponseEntity.ok(optionModel);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@RequestMapping("/optionLtp/{expiry}/{strike}/{type}")
	public ResponseEntity<Double> getOptionLtp(@PathVariable("expiry") String expiry, @PathVariable("strike") Integer strike, @PathVariable("type") OptionType type) {
		return ResponseEntity.ok(Data.getLtp(Data.getBNFData(), strike, type, expiry));

	}
}
