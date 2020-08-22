package org.stocksrin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {
	@RequestMapping({ "/login" })
	public String login(@RequestParam String name) {
		System.out.println("name :" + name);
		return "login";
	}

	
	@RequestMapping(value="/{test:[a]}")
	public String test(@PathVariable String txt, @PathVariable String num) {
		return "ads.txt";
	}
	
	@RequestMapping({ "/ads.txt" })
	public String ads() {
		return "ads.txt";
	}

	@RequestMapping({ "/niftyOptionChainIntraDay" })
	public String niftyOptionChainIntraDay() {
		return "niftyOptionChainIntraDay";
	}

	@RequestMapping({ "/niftyOptionChainDaily" })
	public String niftyOptionChainDaily() {
		return "niftyOptionChainDaily";
	}

	@RequestMapping({ "/portfolioI" })
	public String dbIntradayStrategy() {
		return "portfolioI";
	}

	@RequestMapping({ "/portfolioO" })
	public String dbovernightStrategy() {
		return "portfolioO";
	}

	@RequestMapping({ "/niftyoc" })
	public String niftyOptioncahin() {
		return "niftyoc";
	}

	@RequestMapping({ "/index" })
	public String index() {
		return "index";
	}

	@RequestMapping({ "/bnfOI" })
	public String bnfOI() {
		return "bnfOI";
	}

	@RequestMapping({ "/portfolioIdayv2" })
	public String portfolioIdayv2() {
		return "portfolioIdayv2";
	}

	@RequestMapping({ "/portfoliov2" })
	public String portfoliov2() {
		return "portfoliov2";
	}

	@RequestMapping({ "/portfolio" })
	public String portfolio() {
		return "portfolio";
	}

	@RequestMapping({ "/portfolio2" })
	public String portfolio2() {
		return "portfolio2";
	}

	@RequestMapping({ "/nfOI" })
	public String nfOI() {
		return "nfOI";
	}

	@RequestMapping({ "/rangeCalculatorSD" })
	public String rangeCalculatorSD() {
		return "rangeCalculatorSD";
	}

	@RequestMapping({ "/training" })
	public String training() {
		return "training";
	}

	@RequestMapping({ "/poi" })
	public String poi() {
		return "poi";
	}

	@RequestMapping({ "/admin" })
	public String admin() {
		return "admin";
	}

	@RequestMapping({ "/niftyChain" })
	public String niftyChain() {
		return "niftyChain";
	}

	@RequestMapping({ "/poi2" })
	public String poi2() {
		return "poi2";
	}

	@RequestMapping({ "/iday" })
	public String iday() {
		return "iday";
	}

	@RequestMapping({ "/iday2" })
	public String iday2() {
		return "iday2";
	}

	@RequestMapping({ "/idayOI" })
	public String idayOI() {
		return "idayOI";
	}

	@RequestMapping({ "/abr" })
	public String abr() {
		return "abr";
	}

	@RequestMapping({ "/ss" })
	public String ss() {
		return "ss";
	}

	@RequestMapping({ "/t" })
	public String t() {
		return "t";
	}

	@RequestMapping({ "/futureOI" })
	public String futureOI() {
		return "futureOI";
	}

	@RequestMapping({ "/optionsOI" })
	public String optionsOI() {
		return "optionsOI";
	}

	@RequestMapping({ "/straddlePrice" })
	public String straddlePrice() {
		return "straddlePrice";
	}
	
	@RequestMapping({ "/idayOI2" })
	public String idayOI2() {
		return "idayOI2";
	}


}