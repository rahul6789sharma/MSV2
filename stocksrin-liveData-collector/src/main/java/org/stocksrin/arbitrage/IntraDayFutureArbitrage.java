package org.stocksrin.arbitrage;

import java.util.Collection;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import org.stocksrin.collector.option.data.utils.FutureDataCollector;
import org.stocksrin.common.model.future.Future;
import org.stocksrin.common.utils.AppConstant;
import org.stocksrin.common.utils.CommonUtils;

public class IntraDayFutureArbitrage {

	static ConcurrentHashMap<String, Arbitrage> thismonth = new ConcurrentHashMap<>();
	static ConcurrentHashMap<String, Arbitrage> nextmonth = new ConcurrentHashMap<>();
	static ConcurrentHashMap<String, Arbitrage> farmonth = new ConcurrentHashMap<>();

	public static void main(String[] args) {
		cal("31OCT2019", thismonth);

		cal("28NOV2019", nextmonth);
		cal("26DEC2019", farmonth);
		sot(thismonth);
		System.out.println("----------------");
		sot(nextmonth);
		System.out.println("----------------");
		sot(farmonth);

	}

	private static void sot(ConcurrentHashMap<String, Arbitrage> map) {
		Collection<Arbitrage> i = map.values();
		SortedSet<Arbitrage> j = new TreeSet<>(i);
		for (Arbitrage arbitrage : j) {
			System.out.println(arbitrage);
		}
	}

	private static void cal(String expiry, ConcurrentHashMap<String, Arbitrage> map) {
		String fnolist = AppConstant.STOCKSRIN_OPTION_DATA_DIR_STOCKS_LIST;
		System.out.println(fnolist);
		List<String> stocsk = CommonUtils.getFNOList(fnolist);
		for (String string : stocsk) {
			try {
				Future future = FutureDataCollector.getFutureData(string, expiry, AppConstant.FUTSTK);
				if (future != null && future.getData()[0].getUnderlyingValue() != null && future.getData()[0].getLastPrice() != null) {
					Integer lotsize = Integer.parseInt(future.getData()[0].getMarketLot());
					Double futurePrice = Double.parseDouble(future.getData()[0].getLastPrice().replace(",", ""));
					Double underlyingPrice = Double.parseDouble(future.getData()[0].getUnderlyingValue().replace(",", ""));
					Double diff = futurePrice - underlyingPrice;
					// System.out.println(diff);
					if (diff > 0) {
						// System.out.println("******** " + diff);
						System.out.println(string);
						double arbitrage = diff * lotsize;
						double investment = underlyingPrice * lotsize;

						double per = investment * .01;
						System.out.println("1 % -> " + per);
						if (per < arbitrage) {
							System.out.println("true");
							System.out.println("Money required " + investment);
							System.out.println("arbitrage " + arbitrage);
						} else {
							System.out.println("Money required " + investment);
							System.out.println("arbitrage " + arbitrage);
							Double percentage = (arbitrage * 100) / investment;
							System.out.println("% " + percentage);
							Arbitrage obj = new Arbitrage(string, futurePrice, underlyingPrice, lotsize, arbitrage, percentage, investment);
							map.put(string, obj);
						}
						System.out.println("***********************");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}

class Arbitrage implements Comparable<Arbitrage> {

	private String sybmoble;
	private double futurePrice;
	private double undelyingPrice;
	private int lot;
	private double arbitrage;
	private Double arbitragePer;
	double investment;

	public Arbitrage(String sybmoble, double futurePrice, double undelyingPrice, int lot, double arbitrage, double arbitragePer, double investment) {
		super();
		this.sybmoble = sybmoble;
		this.futurePrice = futurePrice;
		this.undelyingPrice = undelyingPrice;
		this.lot = lot;
		this.arbitrage = arbitrage;
		this.arbitragePer = arbitragePer;
		this.investment = investment;
	}

	public String getSybmoble() {
		return sybmoble;
	}

	public void setSybmoble(String sybmoble) {
		this.sybmoble = sybmoble;
	}

	public double getFuturePrice() {
		return futurePrice;
	}

	public void setFuturePrice(double futurePrice) {
		this.futurePrice = futurePrice;
	}

	public double getUndelyingPrice() {
		return undelyingPrice;
	}

	public void setUndelyingPrice(double undelyingPrice) {
		this.undelyingPrice = undelyingPrice;
	}

	public int getLot() {
		return lot;
	}

	public void setLot(int lot) {
		this.lot = lot;
	}

	public double getArbitrage() {
		return arbitrage;
	}

	public void setArbitrage(double arbitrage) {
		this.arbitrage = arbitrage;
	}

	public Double getArbitragePer() {
		return arbitragePer;
	}

	public void setArbitragePer(Double arbitragePer) {
		this.arbitragePer = arbitragePer;
	}

	@Override
	public int compareTo(Arbitrage o) {
		return o.getArbitragePer().compareTo(arbitragePer);
	}

	@Override
	public String toString() {
		return "Arbitrage [sybmoble=" + sybmoble + ", futurePrice=" + futurePrice + ", undelyingPrice=" + undelyingPrice + ", lot=" + lot + ", arbitrage=" + arbitrage + ", arbitragePer="
				+ arbitragePer + ", investment=" + investment + "]";
	}

}
