package api.weather.proxy.api.weather.proxy.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "number", "name", "startTime", "endTime", "isDaytime", "temperature", "temperatureUnit",
		"temperatureTrend", "windSpeed", "windDirection", "icon", "shortForecast", "detailedForecast",
		"temperatureCelsius" })
public class ProxyPeriod extends WeatherPeriod {

	// constructor
	public ProxyPeriod(Integer number, String name, String startTime, String endTime, boolean isDaytime,
			Integer temperature, String temperatureUnit, String temperatureTrend, String windSpeed,
			String windDirection, String icon, String shortForecast, String detailedForecast) {
		super(number, name, startTime, endTime, isDaytime, temperature, temperatureUnit, temperatureTrend, windSpeed,
				windDirection, icon, shortForecast, detailedForecast);
	}

	
	// field variables
	private int temperatureCelsius = convertTempFtoC(getTemperature());

	
	// methods
	private int convertTempFtoC(Integer temperature) {
		double tempF = temperature * 1.00;
		double tempC = ((tempF - 32.00) * 5) / 9;
		int tempCInt = (int) Math.round(tempC);
		return tempCInt;
	}

	
	// getters & setters
	public int getTemperatureCelsius() {
		return temperatureCelsius;
	}

	public void setTemperatureCelsius(int temperatureCelsius) {
		this.temperatureCelsius = temperatureCelsius;
	}

}
