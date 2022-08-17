package api.weather.proxy.api.weather.proxy.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GridPoint {

	@JsonProperty("periods")
	private List<ProxyPeriod> periodsList;

	public List<ProxyPeriod> getPeriodsList() {
		return periodsList;
	}

	public void setPeriodsList(List<ProxyPeriod> periodsList) {
		this.periodsList = periodsList;
	}
	
	
}
