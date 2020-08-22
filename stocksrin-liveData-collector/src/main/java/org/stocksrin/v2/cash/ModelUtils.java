package org.stocksrin.v2.cash;

public class ModelUtils {

	public static TradeModle convert(Stocks stocks) {

		TradeModle tradeModle = new TradeModle();
		tradeModle.setSymbol(stocks.getInfo().getSymbol());
		tradeModle.setPriceInfo(stocks.getPriceInfo());

		stocks.getPriceInfo().getPreviousClose();

		return tradeModle;

	}

	public static boolean isShortValid(TradeModle tradeModle) {
		
		Double open = tradeModle.getPriceInfo().getOpen();
		Double max = tradeModle.getPriceInfo().getIntraDayHighLow().getMax();
		System.out.println("open " + open);
		System.out.println("max " + max);
		if(open.equals(max) || open == max){
			System.out.println(tradeModle.getSymbol() + " " +  "valid");
			return true;
		}else {
			return false;
		}
		
	}
	
public static boolean isLongValid(TradeModle tradeModle) {
		
		Double open = tradeModle.getPriceInfo().getOpen();
		Double min = tradeModle.getPriceInfo().getIntraDayHighLow().getMin();
		System.out.println("open " + open);
		System.out.println("min " + min);
		if(open.equals(min) || open == min){
			System.out.println(tradeModle.getSymbol() + " " +  "valid");
			return true;
		}else {
			return false;
		}
		
	}
}
