package api.weather.proxy.api.weather.proxy.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GridPoint {

	@JsonProperty("periods")
	private List<WeatherPeriod> periodsList;

	public List<WeatherPeriod> getPeriodsList() {
		return periodsList;
	}

	public void setPeriodsList(List<WeatherPeriod> periodsList) {
		this.periodsList = periodsList;
	}
	
	
}
