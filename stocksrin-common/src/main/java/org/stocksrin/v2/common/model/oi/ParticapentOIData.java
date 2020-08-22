package org.stocksrin.v2.common.model.oi;

import java.util.ArrayList;
import java.util.List;

public class ParticapentOIData {

	private String date;
	private List<ParticipantOIModle> data = new ArrayList<>();

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<ParticipantOIModle> getData() {
		return data;
	}

	public void setData(List<ParticipantOIModle> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ParticapentOIData [date=" + date + ", data=" + data + "]";
	}

}
