package org.stocksrin.v2.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.stocksrin.collector.option.data.InMemoryStrategyies;
import org.stocksrin.common.model.trade.Strategy;

@RestController
@RequestMapping("/strategiesBuilder")
public class Controller {

	// Define the log object for this class
	// private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Value("${logging.file}")
	private String name;

	// http://13.234.37.254:8088/strategiesBuilder/strategiesIntraDay
	@RequestMapping("/hello")
	public String sayHello() {

		Set<String> daily = InMemoryStrategyies.getStrategies().keySet();
		Set<String> intraday = InMemoryStrategyies.getStrategiesIntraDay().keySet();
		StringBuilder s = new StringBuilder();
		for (String string : daily) {
			s.append(string);
			s.append("\n");
		}

		for (String string : intraday) {
			s.append(string);
			s.append("\n");
		}

		return s.toString();
	}

	@RequestMapping("/strategies")
	public Map<String, Strategy> getStrategyResult() {
		return InMemoryStrategyies.getStrategies();
	}

	@RequestMapping("/strategiesIntraDay")
	public Map<String, Strategy> getIntraDayStrategyResult() {
		return InMemoryStrategyies.getStrategiesIntraDay();
	}

}
