package org.stocksrin.v2.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.stocksrin.v2.common.model.oi.ParticapentOIData;
import org.stocksrin.v2.oi.OIUtil;

@RestController
@RequestMapping("/v2/oi")
public class OIRestController {

	@RequestMapping("/hello")
	public String sayHello() {
		return "OIController";
	}

	@RequestMapping("/data")
	public List<ParticapentOIData> getDat() {
		return OIUtil.load();
	}
}
