package org.stocksrin.v2.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.stocksrin.v2.common.model.option.SSEntity;
import org.stocksrin.v2.data.Data;

@RestController
@RequestMapping("/ssRestController")
public class SSRestController {

	@RequestMapping("/ping")
	public String ping() {
		return "ssRestController is UP";
	}

	// http://localhost:8082/ssRestController/data
	@RequestMapping("/data")
	public static ResponseEntity<List<SSEntity>> getStocks() {
		return ResponseEntity.ok(Data.ssEntitys);
	}
}