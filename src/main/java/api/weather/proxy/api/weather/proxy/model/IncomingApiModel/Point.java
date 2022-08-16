package api.weather.proxy.api.weather.proxy.model.IncomingApiModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Point {

	@JsonProperty("forecast")
	private String forecast;

	public String getForecast() {
		return forecast;
	}

	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	
	
}
