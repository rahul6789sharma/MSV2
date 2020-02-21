package org.stocksrin.v2.strategies.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.stocksrin.common.model.trade.Strategy;
import org.stocksrin.common.utils.CommonUtils;
import org.stocksrin.common.utils.FileUtils;
import org.stocksrin.email.SendEmail;
import org.stocksrin.v2.services.RestServiceV2;

@Controller
public class IntraDayBNFStrategies {

	private final Logger log = LoggerFactory.getLogger(IntraDayBNFStrategies.class);

	@Autowired(required = true)
	@Qualifier("BNFConsumeWebServiceV2")
	private RestServiceV2 restServiceV2;

	public String ratio(String fileName, String dir, int distance) {

		String file = dir + fileName + ".csv";
		try {
			boolean status = FileUtils.isTodayFileExist(file);
			if (!status) {
				Strategy strategy = BNFStrategies.ratio(restServiceV2, 3, StrategyType.INTRADAY, distance);
				CommonUtils.createStrategyFile(strategy, dir, fileName);
			} else {
				log.warn("Intra DayFile strategy already exist " + file);
			}

		} catch (Exception e) {
			e.printStackTrace();
			SendEmail.sentMail("Critical Error in startegy file " + file, "", "strategyBuilder");
		}
		return file;
	}

	public String ratio_50(String fileName, String dir, int distance) {

		String file = dir + fileName + ".csv";
		try {
			boolean status = FileUtils.isTodayFileExist(file);
			if (!status) {
				Strategy strategy = BNFStrategies.ratio(restServiceV2, 3, StrategyType.INTRADAY, distance);
				CommonUtils.createStrategyFile(strategy, dir, fileName);
			} else {
				log.warn("Intra DayFile strategy already exist " + file);
			}

		} catch (Exception e) {
			e.printStackTrace();
			SendEmail.sentMail("Critical Error in startegy file " + file, "", "strategyBuilder");
		}
		return file;
	}

	public String ratio_atm(String fileName, String dir, int distance) {

		String file = dir + fileName + ".csv";
		try {
			boolean status = FileUtils.isTodayFileExist(file);
			if (!status) {
				Strategy strategy = BNFStrategies.ratio(restServiceV2, 3, StrategyType.INTRADAY, distance);
				CommonUtils.createStrategyFile(strategy, dir, fileName);
			} else {
				log.warn("Intra DayFile strategy already exist " + file);
			}

		} catch (Exception e) {
			e.printStackTrace();
			SendEmail.sentMail("Critical Error in startegy file " + file, "", "strategyBuilder");
		}
		return file;
	}

}
