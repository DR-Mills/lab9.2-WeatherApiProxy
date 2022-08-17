package api.weather.proxy.api.weather.proxy.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "number", "name", "startTime", "endTime", "isDaytime", "temperature", "temperatureUnit",
		"temperatureTrend", "windSpeed", "windDirection", "icon", "shortForecast", "detailedForecast",
		"temperatureCelsius" })
public class ProxyPeriod extends WeatherPeriod {

	// field variables
	private int temperatureCelsius;

	
	// getters & setters
	public int getTemperatureCelsius() {
		return temperatureCelsius;
	}

	public void setTemperatureCelsius(Integer temperature) {
		double tempC = ((temperature - 32.00) * 5) / 9;
		int tempCInt = (int) Math.round(tempC);
		this.temperatureCelsius = tempCInt;
	}

}
