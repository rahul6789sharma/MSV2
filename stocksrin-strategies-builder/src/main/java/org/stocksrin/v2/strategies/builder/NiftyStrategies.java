package org.stocksrin.v2.strategies.builder;

import java.util.List;

import org.stocksrin.common.model.option.OptionType;
import org.stocksrin.common.model.trade.Strategy;
import org.stocksrin.common.model.trade.UnderLyingInstrument;
import org.stocksrin.common.utils.DateUtils;
import org.stocksrin.common.utils.options.ExpiryUtils;
import org.stocksrin.strategies.common.utils.StrategyUtils;
import org.stocksrin.v2.common.model.option.Datum;
import org.stocksrin.v2.common.model.option.OptionModel;
import org.stocksrin.v2.common.option.utils.OptionUtils;
import org.stocksrin.v2.services.RestServiceV2;

public class NiftyStrategies {

	public static Strategy ratio(RestServiceV2 restServiceV2, int ratio, StrategyType strategyType, int distance) throws Exception {

		
		List<String> allExpiry = restServiceV2.getAllExpiry();
		String currentExpiry = null;
		if (StrategyType.INTRADAY.equals(strategyType)) {
			currentExpiry = allExpiry.get(0);
		} else if (StrategyType.POSITIONAL.equals(strategyType)) {
			if (ExpiryUtils.isTodayExpiry(allExpiry)) {
				currentExpiry = allExpiry.get(1);
			} else {
				currentExpiry = allExpiry.get(0);
			}
		}

		// currentExpiry = StrategyUtils.getNiftyExpiryForDayTrading(allExpiry,
		// currentExpiry);
		Long dte = DateUtils.getDte(currentExpiry, "dd-MMM-yyyy");
		// OptionModles optionModles = NiftyData.optionData.get(currentExpiry);
		OptionModel optionModles = restServiceV2.getOptionModel(currentExpiry);
		if (optionModles == null) {
			return null;
		}
		Integer atmStrike = OptionUtils.getATMStrikeV2(optionModles, 50);
		// double atmStrike = optionModles.getAtmStrike();

		Integer lowerStrike1 = atmStrike - distance;
		Integer uperStrike1 = atmStrike + distance;

		List<Datum> lst = optionModles.getDatums();
		Strategy strategy = new Strategy(UnderLyingInstrument.NIFTY);
		strategy.setStrategyName("NIFTY");
		strategy.setDte(dte.toString());

		int qnt = 75;
		Strategy sortleg1put = StrategyUtils.buildStrategy(optionModles.getTimestamp(),"NF", lst, lowerStrike1 - 50, OptionType.PUT, currentExpiry, optionModles.getUnderlyingValue(), -ratio * qnt);
		Strategy longleg1put = StrategyUtils.buildStrategy(optionModles.getTimestamp(),"NF", lst, lowerStrike1, OptionType.PUT, currentExpiry, optionModles.getUnderlyingValue(), qnt);

		Strategy longleg1Call = StrategyUtils.buildStrategy(optionModles.getTimestamp(),"NF", lst, uperStrike1, OptionType.CALL, currentExpiry, optionModles.getUnderlyingValue(), qnt);
		Strategy sortleg1Call = StrategyUtils.buildStrategy(optionModles.getTimestamp(),"NF", lst, uperStrike1 + 50, OptionType.CALL, currentExpiry, optionModles.getUnderlyingValue(), -ratio * qnt);

		strategy.getStrategyModels().addAll(sortleg1put.getStrategyModels());
		strategy.getStrategyModels().addAll(longleg1put.getStrategyModels());

		strategy.getStrategyModels().addAll(longleg1Call.getStrategyModels());
		strategy.getStrategyModels().addAll(sortleg1Call.getStrategyModels());
		return strategy;
	}

}
