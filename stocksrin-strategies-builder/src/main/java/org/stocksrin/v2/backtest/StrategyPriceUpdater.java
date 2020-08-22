package org.stocksrin.v2.backtest;

import java.util.List;

import org.stocksrin.common.model.trade.Strategy;
import org.stocksrin.common.model.trade.StrategyModel;
import org.stocksrin.common.model.trade.UnderLyingInstrument;
import org.stocksrin.v2.common.model.option.OptionModel;
import org.stocksrin.v2.common.option.utils.IntraDayOptionChainJosnReader;

public class StrategyPriceUpdater {

	public synchronized static void updatePrice(Strategy strategy, OptionModel optionModel) throws Exception {
		if (strategy.getUnderlying().equals(UnderLyingInstrument.BANKNIFTY)) {
			/*
			 * List<StrategyModel> strategyModels = strategy.getStrategyModels();
			 * strategy.setUnderlying_ltp(this.bnfConsumeWebService.getSpot().doubleValue())
			 * ; double totalPL = 0.0D; Double ltp = null; Double iv = null; String
			 * lastDataUpdateTime = ""; for (StrategyModel strategyModel : strategyModels) {
			 * ltp = this.bnfConsumeWebService.getOptionLtp(strategyModel.getExpiry(),
			 * strategyModel.getStrike().intValue(), strategyModel.getType()); // iv =
			 * this.bnfConsumeWebService.getIV(strategyModel.getExpiry(), //
			 * strategyModel.getStrike().intValue(), strategyModel.getType());
			 * 
			 * if (ltp != null && ltp.doubleValue() != 0.0D && ltp.doubleValue() != 0.0D)
			 * strategyModel.setLtp(ltp.doubleValue()); totalPL +=
			 * strategyModel.getQuantity() * (strategyModel.getLtp() -
			 * strategyModel.getAvgPrice()); lastDataUpdateTime =
			 * this.bnfConsumeWebService.getLastDataUpdated(strategyModel.getExpiry()); }
			 * updateData(strategy, lastDataUpdateTime, totalPL);
			 */
		} else if (strategy.getUnderlying().equals(UnderLyingInstrument.NIFTY)) {
			List<StrategyModel> strategyModels = strategy.getStrategyModels();
			strategy.setUnderlying_ltp(IntraDayOptionChainJosnReader.getSpot(optionModel));
			double totalPL = 0.0D;
			Double iv = null;
			Double ltp = null;
			String lastDataUpdateTime = "";
			for (StrategyModel strategyModel : strategyModels) {
				ltp = IntraDayOptionChainJosnReader.getOptionLtp(optionModel, strategyModel.getStrike(),
						strategyModel.getType(), strategyModel.getExpiry());

				if (ltp != null && ltp.doubleValue() != 0.0D && ltp.doubleValue() != 0.0D)
					strategyModel.setLtp(ltp.doubleValue());
				totalPL += strategyModel.getQuantity() * (strategyModel.getLtp() - strategyModel.getAvgPrice());
				lastDataUpdateTime = IntraDayOptionChainJosnReader.getNiftyLastUpdatedTimestamp(optionModel);
			}
			//System.out.println(strategy.getStrategyName());
			List<StrategyModel> lst = strategy.getStrategyModels();
			for (StrategyModel strategyModel : lst)
				//System.out.println(strategyModel.getStrike() + "-:" + strategyModel.getType() + "->"
					//	+ String.valueOf(strategyModel.getLtp()));
			updateData(strategy, lastDataUpdateTime, totalPL);
		} else {
			throw new Exception("Underlying is not set");
		}
	}

	private static void updateData(Strategy strategy, String lastDataUpdateTime, double totalPL) throws Exception {
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
