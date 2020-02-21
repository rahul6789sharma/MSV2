package org.stocksrin.v2.strategies.builder;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.stocksrin.common.model.option.OptionType;
import org.stocksrin.common.model.trade.Strategy;
import org.stocksrin.common.model.trade.UnderLyingInstrument;
import org.stocksrin.common.utils.CommonUtils;
import org.stocksrin.common.utils.DateUtils;
import org.stocksrin.common.utils.FileUtils;
import org.stocksrin.common.utils.options.ExpiryUtils;
import org.stocksrin.email.SendEmail;
import org.stocksrin.strategies.common.utils.StrategyUtils;
import org.stocksrin.v2.common.model.option.Datum;
import org.stocksrin.v2.common.model.option.OptionModel;
import org.stocksrin.v2.common.option.utils.OptionUtils;
import org.stocksrin.v2.services.NiftyConsumeWebServiceV2;

@Controller
public class PositionalIntraDayNiftyStrategies {

	private final Logger log = LoggerFactory.getLogger(PositionalIntraDayNiftyStrategies.class);
	
	@Autowired
	private NiftyConsumeWebServiceV2 niftyConsumeWebService;

	public String ratio(String fileName, String dir) {

		String file = dir + fileName + ".csv";
		try {
			boolean status = FileUtils.isTodayFileExist(file);
			if (!status) {
				Strategy strategy = ratio(3);
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

	private Strategy ratio(int ratio) throws Exception {

		// String currentExpiry = NiftyData.shortedExpiry.first();
		List<String> allExpiry = niftyConsumeWebService.getAllExpiry();
		// String currentExpiry = allExpiry.first();

		String currentExpiry = null;

		if (!ExpiryUtils.isTodayExpiry(allExpiry)) {
			currentExpiry = allExpiry.get(0);
		} else {
			// go to new Expiry
			List<String> expiries = new ArrayList<>(allExpiry);
			currentExpiry = expiries.get(0);
		}

		// currentExpiry = StrategyUtils.getNiftyExpiryForDayTrading(allExpiry,
		// currentExpiry);
		Long dte = DateUtils.getDte(currentExpiry, "dd-MMM-yyyy");
		// OptionModles optionModles = NiftyData.optionData.get(currentExpiry);
		OptionModel optionModles = niftyConsumeWebService.getOptionModel(currentExpiry);
		if (optionModles == null) {
			return null;
		}
		Integer atmStrike = OptionUtils.getATMStrikeV2(optionModles, 50);
		// double atmStrike = optionModles.getAtmStrike();

		Integer lowerStrike1 = atmStrike - 100;
		Integer uperStrike1 = atmStrike + 100;

		List<Datum> lst = optionModles.getDatums();
		Strategy strategy = new Strategy(UnderLyingInstrument.NIFTY);
		strategy.setStrategyName("NIFTY");
		strategy.setDte(dte.toString());

		int qnt = 75;
		Strategy sortleg1put = StrategyUtils.buildStrategy(optionModles.getTimestamp(), "NF", lst, lowerStrike1 - 50, OptionType.PUT, currentExpiry, optionModles.getUnderlyingValue(), -ratio * qnt);
		Strategy longleg1put = StrategyUtils.buildStrategy(optionModles.getTimestamp(), "NF", lst, lowerStrike1, OptionType.PUT, currentExpiry, optionModles.getUnderlyingValue(), qnt);

		Strategy longleg1Call = StrategyUtils.buildStrategy(optionModles.getTimestamp(), "NF", lst, uperStrike1, OptionType.CALL, currentExpiry, optionModles.getUnderlyingValue(), qnt);
		Strategy sortleg1Call = StrategyUtils.buildStrategy(optionModles.getTimestamp(), "NF", lst, uperStrike1 + 50, OptionType.CALL, currentExpiry, optionModles.getUnderlyingValue(), -ratio * qnt);

		strategy.getStrategyModels().addAll(sortleg1put.getStrategyModels());
		strategy.getStrategyModels().addAll(longleg1put.getStrategyModels());

		strategy.getStrategyModels().addAll(longleg1Call.getStrategyModels());
		strategy.getStrategyModels().addAll(sortleg1Call.getStrategyModels());
		return strategy;
	}

}
