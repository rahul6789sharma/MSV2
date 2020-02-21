package org.stocksrin.v2.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.stocksrin.v2.common.model.future.EntityABR;
import org.stocksrin.v2.data.Data;

@RestController
@RequestMapping("/arb")
public class ArbRestController {

	@RequestMapping("/data")
	public ResponseEntity<Map<String, EntityABR>> getShortedExpiry() {
		Map<String, EntityABR> data = Data.currentMonthABR;
		if (!data.isEmpty()) {
			return ResponseEntity.ok(data);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
}
