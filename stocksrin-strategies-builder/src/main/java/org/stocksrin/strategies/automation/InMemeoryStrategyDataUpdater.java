package org.stocksrin.strategies.automation;

import java.util.List;
import java.util.Set;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.stocksrin.StrategyBuilderApplication;
import org.stocksrin.collector.option.data.InMemoryStrategyies;
import org.stocksrin.common.model.trade.Strategy;
import org.stocksrin.common.model.trade.StrategyModel;
import org.stocksrin.common.model.trade.UnderLyingInstrument;
import org.stocksrin.common.utils.CommonUtils;
import org.stocksrin.common.utils.DateUtils;
import org.stocksrin.common.utils.NSEHolidayUtils;
import org.stocksrin.strategies.utils.result.StrategyResult;
import org.stocksrin.v2.services.RestServiceV2;

@Component
public class InMemeoryStrategyDataUpdater extends TimerTask {
	private static final Logger log = LoggerFactory.getLogger(InMemeoryStrategyDataUpdater.class);

	static long timeInteval = 120000L;

	
	@Autowired(required = true)
	@Qualifier("BNFConsumeWebServiceV2")
	private RestServiceV2 bnfConsumeWebService;

	@Autowired
	@Qualifier("NiftyConsumeWebServiceV2")
	private RestServiceV2 niftyConsumeWebService;

	public void run() {
		StrategyBuilderApplication.appWait();
		if (!DateUtils.isWeekEndDay() && !NSEHolidayUtils.isHoliday()) {
			InMemoryStrategyies.clear();
			while (CommonUtils.getEveningTimeForStrategy()) {
				log.info("updating price Start..");
				try {
					try {
						LockObject.getWriteLock();
						Set<String> keys = InMemoryStrategyies.getStrategies().keySet();
						for (String string2 : keys) {
							Strategy strategy = (Strategy) InMemoryStrategyies.getStrategies().get(string2);
							updatePrice(strategy);
						}
						Set<String> keys2 = InMemoryStrategyies.getStrategiesIntraDay().keySet();
						for (String string2 : keys2) {
							Strategy strategy = (Strategy) InMemoryStrategyies.getStrategiesIntraDay().get(string2);
							updatePrice(strategy);
						}
					} finally {
						LockObject.realseWriteLock();
						log.info("Updating price Complete..");
						Thread.sleep(timeInteval);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (CommonUtils.isTimeLessThen(Integer.valueOf(15), Integer.valueOf(55))) {
				log.info("Writing result to file ");
				StrategyResult.writeResult();
			}
		}
	}

	private synchronized void updatePrice(Strategy strategy) throws Exception {
		if (strategy.getUnderlying().equals(UnderLyingInstrument.BANKNIFTY)) {
			List<StrategyModel> strategyModels = strategy.getStrategyModels();
			strategy.setUnderlying_ltp(this.bnfConsumeWebService.getSpot().doubleValue());
			double totalPL = 0.0D;
			Double ltp = null;
			Double iv = null;
			String lastDataUpdateTime = "";
			for (StrategyModel strategyModel : strategyModels) {
				ltp = this.bnfConsumeWebService.getOptionLtp(strategyModel.getExpiry(), strategyModel.getStrike().intValue(), strategyModel.getType());
				// iv = this.bnfConsumeWebService.getIV(strategyModel.getExpiry(),
				// strategyModel.getStrike().intValue(), strategyModel.getType());

				if (ltp != null && ltp.doubleValue() != 0.0D && ltp.doubleValue() != 0.0D)
					strategyModel.setLtp(ltp.doubleValue());
				totalPL += strategyModel.getQuantity() * (strategyModel.getLtp() - strategyModel.getAvgPrice());
				lastDataUpdateTime = this.bnfConsumeWebService.getLastDataUpdated(strategyModel.getExpiry());
			}
			updateData(strategy, lastDataUpdateTime, totalPL);
		} else if (strategy.getUnderlying().equals(UnderLyingInstrument.NIFTY)) {
			List<StrategyModel> strategyModels = strategy.getStrategyModels();
			strategy.setUnderlying_ltp(this.niftyConsumeWebService.getSpot().doubleValue());
			double totalPL = 0.0D;
			Double iv = null;
			Double ltp = null;
			String lastDataUpdateTime = "";
			for (StrategyModel strategyModel : strategyModels) {
				ltp = this.niftyConsumeWebService.getOptionLtp(strategyModel.getExpiry(), strategyModel.getStrike(), strategyModel.getType());
				if (ltp != null && ltp.doubleValue() != 0.0D && ltp.doubleValue() != 0.0D)
					strategyModel.setLtp(ltp.doubleValue());
				totalPL += strategyModel.getQuantity() * (strategyModel.getLtp() - strategyModel.getAvgPrice());
				lastDataUpdateTime = this.niftyConsumeWebService.getLastDataUpdated(strategyModel.getExpiry());
			}
			log.info(strategy.getStrategyName());
			List<StrategyModel> lst = strategy.getStrategyModels();
			for (StrategyModel strategyModel : lst)
				log.info(strategyModel.getStrike() + "-:" + strategyModel.getType() + "->" + String.valueOf(strategyModel.getLtp()));
			updateData(strategy, lastDataUpdateTime, totalPL);
		} else {
			throw new Exception("Underlying is not set");
		}
	}

	private void updateData(Strategy strategy, String lastDataUpdateTime, double totalPL) throws Exception {
		strategy.setDataUpdatedAt(lastDataUpdateTime);
		strategy.setTotalPL(totalPL);
		strategy.getPnlList().put(lastDataUpdateTime, Integer.valueOf((int) totalPL));
		if (strategy.getTotalPLMax() == 0.0D && strategy.getTotalPLMin() == 0.0D) {
			strategy.setTotalPLMax(strategy.getTotalPL());
			strategy.setTotalPLMin(strategy.getTotalPL());
			strategy.setTotalPLMaxSpot(strategy.getUnderlying_ltp());
			strategy.setTotalPLMinSpot(strategy.getUnderlying_ltp());
			strategy.setTotalPLMaxTime(lastDataUpdateTime);
			strategy.setTotalPLMinTime(lastDataUpdateTime);
		}
		if (strategy.getTotalPL() > strategy.getTotalPLMax()) {
			strategy.setTotalPLMax(strategy.getTotalPL());
			strategy.setTotalPLMaxSpot(strategy.getUnderlying_ltp());
			strategy.setTotalPLMaxTime(lastDataUpdateTime);
		}
		if (strategy.getTotalPL() < strategy.getTotalPLMin()) {
			strategy.setTotalPLMin(strategy.getTotalPL());
			strategy.setTotalPLMinSpot(strategy.getUnderlying_ltp());
			strategy.setTotalPLMinTime(lastDataUpdateTime);
		}
	}
}
