package org.stocksrin.v2.services;

import java.util.List;

import org.stocksrin.common.model.option.OptionType;
import org.stocksrin.v2.common.model.option.OptionModel;

public interface RestServiceV2 {

	public List<String> getAllExpiry() throws Exception;
	public OptionModel getOptionModel(String expiry) throws Exception;
	public Double getOptionLtp(String expiry, Integer strike, OptionType optionType) throws Exception;
	public String getLastDataUpdated(String expiry) throws Exception;
	public Double getSpot() throws Exception;
}
