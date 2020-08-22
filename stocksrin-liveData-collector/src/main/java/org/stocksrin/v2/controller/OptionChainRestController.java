package org.stocksrin.v2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.stocksrin.v2.common.model.option.OptionModel;
import org.stocksrin.v2.common.option.utils.IntraDayOptionChainJosnReader;

@RestController
@RequestMapping("/optionChainService")
public class OptionChainRestController {

	// http://localhost:8082/optionChainService/optionChain?expiry=30-Jan-2020&date=30-Jan-2020
		// expiry
		@RequestMapping("/optionChain")
		public ResponseEntity<List<OptionModel>> getNiftyExpiryDayChain(@RequestParam("expiry") String expiry, @RequestParam("date") String date) throws Exception {
			
			List<OptionModel> o = IntraDayOptionChainJosnReader.getNiftyOptionChain(expiry,date, false);
			if (o == null) {
				ResponseEntity.status(HttpStatus.NO_CONTENT);
			}
			return ResponseEntity.ok(o);
		}
}
