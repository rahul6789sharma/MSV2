package org.stocksrin.v2.intraday;

import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class OptionChainIntraDayDownloader extends TimerTask {

	private long timeInteval5min = 300000; // 5 min
	private static final Logger log = LoggerFactory.getLogger(OptionChainIntraDayDownloader.class);
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
