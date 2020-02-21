package org.stocksrin.strategies.automation;

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stocksrin.collector.option.data.InMemoryStrategyies;
import org.stocksrin.common.model.trade.Strategy;
import org.stocksrin.common.model.trade.UnderLyingInstrument;
import org.stocksrin.common.utils.StrategyUtil;

public class StrategyFileReader {
	private static final Logger log = LoggerFactory.getLogger(StrategyFileReader.class);

	public static synchronized void startManualStrategies(String starttegyDir) {
		try {
			try {
				log.info("StrategyFile updater Start..");
				LockObject.getWriteLock();

				// reading strategy frm file
				Map<String, Strategy> strategyMap = StrategyUtil.getStrategy2(starttegyDir);
				Set<String> strategies = strategyMap.keySet();

				for (String string : strategies) {
					log.info("Updating in Memory strategy File " + string);
					Strategy startgy = strategyMap.get(string);

					if (startgy.getUnderlying().equals(UnderLyingInstrument.BANKNIFTY)) {

						try {
							// BreakEvenCalUtils.calculatebreakEven(startgy);
							// BreakEvenCalUtils.findBreakEven(startgy);
						} catch (Exception e) {
							e.printStackTrace();
						}

						// Result
						// update in inmemeory and after that we have to update
						// ltp
						// based on dir it ll be updated in intra day or
						// positional
						try {
							InMemoryStrategyies.put(string, startgy, starttegyDir);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (startgy.getUnderlying().equals(UnderLyingInstrument.NIFTY)) {

						// Result
						try {
							InMemoryStrategyies.put(string, startgy, starttegyDir);
						} catch (Exception e) {
							e.printStackTrace();
						}

					} else if (startgy.getUnderlying().equals(UnderLyingInstrument.USDINR)) {
						InMemoryStrategyies.put(string, startgy, starttegyDir);
					}

					else {
						log.error("****** Underlying unknown ****" + startgy.getUnderlying());
					}
				}
			} finally {
				LockObject.realseWriteLock();
				log.info("StrategyFile updater completed..");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}