package org.stocksrin.v2.strategies.builder;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.stocksrin.common.model.option.OptionType;
import org.stocksrin.common.model.trade.Strategy;
import org.stocksrin.common.model.trade.UnderLyingInstrument;
import org.stocksrin.common.utils.CommonUtils;
import org.stocksrin.common.utils.DateUtils;
import org.stocksrin.common.utils.FileUtils;
import org.stocksrin.email.SendEmail;
import org.stocksrin.strategies.common.utils.StrategyUtils;
import org.stocksrin.v2.common.model.option.Datum;
import org.stocksrin.v2.common.model.option.OptionModel;
import org.stocksrin.v2.common.option.utils.OptionUtils;
import org.stocksrin.v2.services.RestServiceV2;

@Controller
public class IntraDayNiftyStrategies {

	private final Logger log = LoggerFactory.getLogger(IntraDayNiftyStrategies.class);

	@Autowired(required = true)
	@Qualifier("NiftyConsumeWebServiceV2")
	private RestServiceV2 restServiceV2;

	public String invertedStrangle(String fileName, String dir) {

		String file = dir + fileName + ".csv";
		try {
			boolean status = FileUtils.isTodayFileExist(file);
			if (!status) {
				Strategy strategy = invertedStrangle();
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
	
	public String strangleOTM200PointFar(String fileName, String dir) {

		String file = dir + fileName + ".csv";
		try {
			boolean status = FileUtils.isTodayFileExist(file);
			if (!status) {
				Strategy strategy = strangleNiftyOTM200PointFar();
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

	public String ratio(String fileName, String dir, int distance) {

		String file = dir + fileName + ".csv";
		try {
			boolean status = FileUtils.isTodayFileExist(file);
			if (!status) {
				Strategy strategy = NiftyStrategies.ratio(restServiceV2, 3, StrategyType.INTRADAY, distance);
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
				Strategy strategy = NiftyStrategies.ratio(restServiceV2, 3, StrategyType.INTRADAY, distance);
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
				Strategy strategy = NiftyStrategies.ratio(restServiceV2, 3, StrategyType.INTRADAY, distance);
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

	public String straddle2_calender(String fileName, String dir) {

		String file = dir + fileName + ".csv";
		try {
			boolean status = FileUtils.isTodayFileExist(file);
			if (!status) {
				Strategy strategy = straddle2_calender();
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

	public String straddle3_calender(String fileName, String dir) {

		String file = dir + fileName + ".csv";
		try {
			boolean status = FileUtils.isTodayFileExist(file);
			if (!status) {
				Strategy strategy = straddle3_calender();
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

	public String straddle3(String fileName, String dir) {

		String file = dir + fileName + ".csv";
		try {
			boolean status = FileUtils.isTodayFileExist(file);
			if (!status) {
				Strategy strategy = straddle3();
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

	private Strategy straddle3() throws Exception {

		List<String> allExpiry = restServiceV2.getAllExpiry();
		String currentExpiry = allExpiry.get(0);

		Long dte = DateUtils.getDte(currentExpiry, "dd-MMM-yyyy");
		// OptionModles optionModles = NiftyData.optionData.get(currentExpiry);
		OptionModel optionModles = restServiceV2.getOptionModel(currentExpiry);
		if (optionModles == null) {
			return null;
		}
		Integer atmStrike = OptionUtils.getATMStrikeV2(optionModles, 50);
		// double atmStrike = optionModles.getAtmStrike();

		Integer lowerStrike1 = atmStrike - 50;
		Integer uperStrike1 = atmStrike + 50;

		List<Datum> lst = optionModles.getDatums();
		Strategy strategy = new Strategy(UnderLyingInstrument.NIFTY);
		strategy.setStrategyName("NIFTY");
		strategy.setDte(dte.toString());

		int qnt = -75;

		Strategy leg0put = StrategyUtils.buildStrategy(optionModles.getTimestamp(), "NF", lst, lowerStrike1, OptionType.PUT, currentExpiry, optionModles.getUnderlyingValue(), qnt);
		Strategy leg0Call = StrategyUtils.buildStrategy(optionModles.getTimestamp(), "NF", lst, lowerStrike1, OptionType.CALL, currentExpiry, optionModles.getUnderlyingValue(), qnt);

		Strategy leg1put = StrategyUtils.buildStrategy(optionModles.getTimestamp(), "NF", lst, atmStrike, OptionType.PUT, currentExpiry, optionModles.getUnderlyingValue(), qnt);
		Strategy leg3Call = StrategyUtils.buildStrategy(optionModles.getTimestamp(), "NF", lst, atmStrike, OptionType.CALL, currentExpiry, optionModles.getUnderlyingValue(), qnt);

		Strategy leg2put = StrategyUtils.buildStrategy(optionModles.getTimestamp(), "NF", lst, uperStrike1, OptionType.PUT, currentExpiry, optionModles.getUnderlyingValue(), qnt);
		Strategy leg2Call = StrategyUtils.buildStrategy(optionModles.getTimestamp(), "NF", lst, uperStrike1, OptionType.CALL, currentExpiry, optionModles.getUnderlyingValue(), qnt);

		strategy.getStrategyModels().addAll(leg0put.getStrategyModels());
		strategy.getStrategyModels().addAll(leg0Call.getStrategyModels());

		strategy.getStrategyModels().addAll(leg1put.getStrategyModels());
		strategy.getStrategyModels().addAll(leg3Call.getStrategyModels());

		strategy.getStrategyModels().addAll(leg2put.getStrategyModels());
		strategy.getStrategyModels().addAll(leg2Call.getStrategyModels());

		return strategy;
	}

	private Strategy straddle2_calender() throws Exception {

		List<String> allExpiry = restServiceV2.getAllExpiry();
		String currentExpiry = allExpiry.get(0);
		String nextExpiry = allExpiry.get(1);

		Long dte = DateUtils.getDte(currentExpiry, "dd-MMM-yyyy");
		// OptionModles optionModles = NiftyData.optionData.get(currentExpiry);
		OptionModel optionModles = restServiceV2.getOptionModel(currentExpiry);
		OptionModel optionModles_next = restServiceV2.getOptionModel(nextExpiry);

		if (optionModles == null || optionModles_next == null) {
			return null;
		}

		Integer atmStrike = OptionUtils.getATMStrikeV2(optionModles, 50);
		// double atmStrike = optionModles.getAtmStrike();

		Integer lowerStrike1 = atmStrike - 50;
		Integer uperStrike1 = atmStrike + 50;

		Double ce_l = restServiceV2.getOptionLtp(currentExpiry, lowerStrike1, OptionType.CALL);
		Double pe_l = restServiceV2.getOptionLtp(currentExpiry, lowerStrike1, OptionType.PUT);
		Double diff_l = Math.abs(ce_l - pe_l);

		Double ce_u = restServiceV2.getOptionLtp(currentExpiry, uperStrike1, OptionType.CALL);
		Double pe_u = restServiceV2.getOptionLtp(currentExpiry, uperStrike1, OptionType.PUT);
		Double diff_u = Math.abs(ce_u - pe_u);

		Integer other_strike = 0;
		if (diff_l > diff_u) {
			other_strike = lowerStrike1;
		} else {
			other_strike = uperStrike1;
		}

		List<Datum> lst = optionModles.getDatums();
		List<Datum> lst_next = optionModles_next.getDatums();
		Strategy strategy = new Strategy(UnderLyingInstrument.NIFTY);
		strategy.setStrategyName("NIFTY");
		strategy.setDte(dte.toString());

		int qnt = 75;

		Strategy leg0put = StrategyUtils.buildStrategy(optionModles.getTimestamp(), "NF", lst, other_strike, OptionType.PUT, currentExpiry, optionModles.getUnderlyingValue(), -qnt);
		Strategy leg0Call = StrategyUtils.buildStrategy(optionModles.getTimestamp(), "NF", lst, other_strike, OptionType.CALL, currentExpiry, optionModles.getUnderlyingValue(), -qnt);

		Strategy leg1put = StrategyUtils.buildStrategy(optionModles.getTimestamp(), "NF", lst, atmStrike, OptionType.PUT, currentExpiry, optionModles.getUnderlyingValue(), -qnt);
		Strategy leg3Call = StrategyUtils.buildStrategy(optionModles.getTimestamp(), "NF", lst, atmStrike, OptionType.CALL, currentExpiry, optionModles.getUnderlyingValue(), -qnt);

		Strategy leg0put_next = StrategyUtils.buildStrategy(optionModles_next.getTimestamp(), "NF", lst_next, other_strike, OptionType.PUT, nextExpiry, optionModles_next.getUnderlyingValue(), qnt);
		Strategy leg0Call_next = StrategyUtils.buildStrategy(optionModles_next.getTimestamp(), "NF", lst_next, other_strike, OptionType.CALL, nextExpiry, optionModles_next.getUnderlyingValue(), qnt);

		Strategy leg1put_next = StrategyUtils.buildStrategy(optionModles_next.getTimestamp(), "NF", lst_next, atmStrike, OptionType.PUT, nextExpiry, optionModles_next.getUnderlyingValue(), qnt);
		Strategy leg3Call_next = StrategyUtils.buildStrategy(optionModles_next.getTimestamp(), "NF", lst_next, atmStrike, OptionType.CALL, nextExpiry, optionModles_next.getUnderlyingValue(), qnt);

		strategy.getStrategyModels().addAll(leg0put.getStrategyModels());
		strategy.getStrategyModels().addAll(leg0Call.getStrategyModels());

		strategy.getStrategyModels().addAll(leg1put.getStrategyModels());
		strategy.getStrategyModels().addAll(leg3Call.getStrategyModels());

		strategy.getStrategyModels().addAll(leg0put_next.getStrategyModels());
		strategy.getStrategyModels().addAll(leg0Call_next.getStrategyModels());

		strategy.getStrategyModels().addAll(leg1put_next.getStrategyModels());
		strategy.getStrategyModels().addAll(leg3Call_next.getStrategyModels());

		return strategy;
	}

	private Strategy straddle3_calender() throws Exception {

		List<String> allExpiry = restServiceV2.getAllExpiry();
		String currentExpiry = allExpiry.get(0);
		String nextExpiry = allExpiry.get(1);

		Long dte = DateUtils.getDte(currentExpiry, "dd-MMM-yyyy");
		// OptionModles optionModles = NiftyData.optionData.get(currentExpiry);
		OptionModel optionModles = restServiceV2.getOptionModel(currentExpiry);
		OptionModel optionModles_next = restServiceV2.getOptionModel(nextExpiry);

		if (optionModles == null || optionModles_next == null) {
			return null;
		}

		Integer atmStrike = OptionUtils.getATMStrikeV2(optionModles, 50);
		// double atmStrike = optionModles.getAtmStrike();

		Integer lowerStrike1 = atmStrike - 50;
		Integer uperStrike1 = atmStrike + 50;

		List<Datum> lst = optionModles.getDatums();
		List<Datum> lst_next = optionModles_next.getDatums();
		Strategy strategy = new Strategy(UnderLyingInstrument.NIFTY);
		strategy.setStrategyName("NIFTY");
		strategy.setDte(dte.toString());

		int qnt = 75;

		Strategy leg0put = StrategyUtils.buildStrategy(optionModles.getTimestamp(), "NF", lst, lowerStrike1, OptionType.PUT, currentExpiry, optionModles.getUnderlyingValue(), -qnt);
		Strategy leg0Call = StrategyUtils.buildStrategy(optionModles.getTimestamp(), "NF", lst, lowerStrike1, OptionType.CALL, currentExpiry, optionModles.getUnderlyingValue(), -qnt);

		Strategy leg1put = StrategyUtils.buildStrategy(optionModles.getTimestamp(), "NF", lst, atmStrike, OptionType.PUT, currentExpiry, optionModles.getUnderlyingValue(), -qnt);
		Strategy leg3Call = StrategyUtils.buildStrategy(optionModles.getTimestamp(), "NF", lst, atmStrike, OptionType.CALL, currentExpiry, optionModles.getUnderlyingValue(), -qnt);

		Strategy leg2put = StrategyUtils.buildStrategy(optionModles.getTimestamp(), "NF", lst, uperStrike1, OptionType.PUT, currentExpiry, optionModles.getUnderlyingValue(), -qnt);
		Strategy leg2Call = StrategyUtils.buildStrategy(optionModles.getTimestamp(), "NF", lst, uperStrike1, OptionType.CALL, currentExpiry, optionModles.getUnderlyingValue(), -qnt);

		Strategy leg0put_next = StrategyUtils.buildStrategy(optionModles_next.getTimestamp(), "NF", lst_next, lowerStrike1, OptionType.PUT, nextExpiry, optionModles_next.getUnderlyingValue(), qnt);
		Strategy leg0Call_next = StrategyUtils.buildStrategy(optionModles_next.getTimestamp(), "NF", lst_next, lowerStrike1, OptionType.CALL, nextExpiry, optionModles_next.getUnderlyingValue(), qnt);

		Strategy leg1put_next = StrategyUtils.buildStrategy(optionModles_next.getTimestamp(), "NF", lst_next, atmStrike, OptionType.PUT, nextExpiry, optionModles_next.getUnderlyingValue(), qnt);
		Strategy leg3Call_next = StrategyUtils.buildStrategy(optionModles_next.getTimestamp(), "NF", lst_next, atmStrike, OptionType.CALL, nextExpiry, optionModles_next.getUnderlyingValue(), qnt);

		Strategy leg2put_next = StrategyUtils.buildStrategy(optionModles_next.getTimestamp(), "NF", lst_next, uperStrike1, OptionType.PUT, nextExpiry, optionModles_next.getUnderlyingValue(), qnt);
		Strategy leg2Call_next = StrategyUtils.buildStrategy(optionModles_next.getTimestamp(), "NF", lst_next, uperStrike1, OptionType.CALL, nextExpiry, optionModles_next.getUnderlyingValue(), qnt);

		strategy.getStrategyModels().addAll(leg0put.getStrategyModels());
		strategy.getStrategyModels().addAll(leg0Call.getStrategyModels());

		strategy.getStrategyModels().addAll(leg1put.getStrategyModels());
		strategy.getStrategyModels().addAll(leg3Call.getStrategyModels());

		strategy.getStrategyModels().addAll(leg2put.getStrategyModels());
		strategy.getStrategyModels().addAll(leg2Call.getStrategyModels());

		strategy.getStrategyModels().addAll(leg0put_next.getStrategyModels());
		strategy.getStrategyModels().addAll(leg0Call_next.getStrategyModels());

		strategy.getStrategyModels().addAll(leg1put_next.getStrategyModels());
		strategy.getStrategyModels().addAll(leg3Call_next.getStrategyModels());

		strategy.getStrategyModels().addAll(leg2put_next.getStrategyModels());
		strategy.getStrategyModels().addAll(leg2Call_next.getStrategyModels());

		return strategy;
	}

	private Strategy strangleNiftyOTM200PointFar() throws Exception {

		List<String> allExpiry = restServiceV2.getAllExpiry();

		String currentExpiry = allExpiry.get(0);
		Long dte = DateUtils.getDte(currentExpiry, "dd-MMM-yyyy");
		// OptionModles optionModles = NiftyData.optionData.get(currentExpiry);
		OptionModel optionModles = restServiceV2.getOptionModel(currentExpiry);
		if (optionModles == null) {
			return null;
		}
		Integer atmStrike = OptionUtils.getATMStrikeV2(optionModles, 50);
		// double atmStrike = optionModles.getAtmStrike();

		Integer lowerStrike1 = atmStrike - 200;
		Integer uperStrike1 = atmStrike + 200;

		List<Datum> lst = optionModles.getDatums();
		Strategy strategy = new Strategy(UnderLyingInstrument.NIFTY);
		strategy.setStrategyName("NIFTY");
		strategy.setDte(dte.toString());

		int qnt = -150;
		Strategy leg1put = StrategyUtils.buildStrategy(optionModles.getTimestamp(), "NF", lst, lowerStrike1, OptionType.PUT, currentExpiry, optionModles.getUnderlyingValue(), qnt);
		Strategy leg3Call = StrategyUtils.buildStrategy(optionModles.getTimestamp(), "NF", lst, uperStrike1, OptionType.CALL, currentExpiry, optionModles.getUnderlyingValue(), qnt);

		strategy.getStrategyModels().addAll(leg1put.getStrategyModels());
		strategy.getStrategyModels().addAll(leg3Call.getStrategyModels());
		return strategy;
	}
	
	private Strategy invertedStrangle() throws Exception {

		List<String> allExpiry = restServiceV2.getAllExpiry();

		String currentExpiry = allExpiry.get(0);
		Long dte = DateUtils.getDte(currentExpiry, "dd-MMM-yyyy");
		// OptionModles optionModles = NiftyData.optionData.get(currentExpiry);
		OptionModel optionModles = restServiceV2.getOptionModel(currentExpiry);
		if (optionModles == null) {
			return null;
		}
		Integer atmStrike = OptionUtils.getATMStrikeV2(optionModles, 50);
		// double atmStrike = optionModles.getAtmStrike();

		Integer ceStrike = atmStrike - 50;
		Integer peStrike = atmStrike + 50;

		List<Datum> lst = optionModles.getDatums();
		Strategy strategy = new Strategy(UnderLyingInstrument.NIFTY);
		strategy.setStrategyName("NIFTY");
		strategy.setDte(dte.toString());

		int qnt = -150;
		Strategy leg1put = StrategyUtils.buildStrategy(optionModles.getTimestamp(), "NF", lst, ceStrike, OptionType.CALL, currentExpiry, optionModles.getUnderlyingValue(), qnt);
		Strategy leg3Call = StrategyUtils.buildStrategy(optionModles.getTimestamp(), "NF", lst, peStrike, OptionType.PUT, currentExpiry, optionModles.getUnderlyingValue(), qnt);

		strategy.getStrategyModels().addAll(leg1put.getStrategyModels());
		strategy.getStrategyModels().addAll(leg3Call.getStrategyModels());
		return strategy;
	}

}
