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
@RequestMapping("/niftydata")
public class NiftyRestController {

	@RequestMapping("/ping")
	public String ping() {
		return "NiftyRestController is UP";
	}

	// http://13.127.27.204:8082/niftydata/spotPrice
	@RequestMapping("/spotPrice")
	public ResponseEntity<Double> getSpotPrice() {
		return ResponseEntity.ok(Data.getNFSpot());
	}

	@RequestMapping("/lastTimeStamp")
	public ResponseEntity<String> lastTimeStamp() {
		return ResponseEntity.ok(Data.getNiftyLastUpdatedTimestamp());
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
		OptionModel optionModel = Data.getNiftyData(expiry);
		if (optionModel != null) {
			return ResponseEntity.ok(optionModel);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@RequestMapping("/intraDayData")
	public ResponseEntity<List<OptionModel>> getIntraDayData() {
		List<OptionModel> data = Data.niftyIntraDay;
		if (!data.isEmpty()) {
			return ResponseEntity.ok(data);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@RequestMapping("/optionLtp/{expiry}/{strike}/{type}")
	public ResponseEntity<Double> getOptionLtp(@PathVariable("expiry") String expiry, @PathVariable("strike") Integer strike, @PathVariable("type") OptionType type) {
		return ResponseEntity.ok(Data.getLtp(Data.getNiftyData(), strike, type, expiry));
		// Data.getNiftyData().getNiftyLtp(strike, type, expiry)
	}

}
