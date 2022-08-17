package api.weather.proxy.api.weather.proxy.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherPeriod {

	@JsonProperty("number")
	private Integer number;

	@JsonProperty("name")
	private String name;

	@JsonProperty("startTime")
	private String startTime;

	@JsonProperty("endTime")
	private String endTime;

	@JsonProperty("isDaytime")
	private boolean isDaytime;

	@JsonProperty("temperature")
	private Integer temperature;

	@JsonProperty("temperatureUnit")
	private String temperatureUnit;

	@JsonProperty("temperatureTrend")
	private String temperatureTrend;

	@JsonProperty("windSpeed")
	private String windSpeed;

	@JsonProperty("windDirection")
	private String windDirection;

	@JsonProperty("icon")
	private String icon;

	@JsonProperty("shortForecast")
	private String shortForecast;

	@JsonProperty("detailedForecast")
	private String detailedForecast;

	// constructors
	
	public WeatherPeriod() {};
	
	public WeatherPeriod(Integer number, String name, String startTime, String endTime, boolean isDaytime,
			Integer temperature, String temperatureUnit, String temperatureTrend, String windSpeed,
			String windDirection, String icon, String shortForecast, String detailedForecast) {
		this.number = number;
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isDaytime = isDaytime;
		this.temperature = temperature;
		this.temperatureUnit = temperatureUnit;
		this.temperatureTrend = temperatureTrend;
		this.windSpeed = windSpeed;
		this.windDirection = windDirection;
		this.icon = icon;
		this.shortForecast = shortForecast;
		this.detailedForecast = detailedForecast;
	}

	// getters & setters
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public boolean getIsDaytime() {
		return isDaytime;
	}

	public void setIsDaytime(boolean isDaytime) {
		this.isDaytime = isDaytime;
	}

	public Integer getTemperature() {
		return temperature;
	}

	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}

	public String getTemperatureUnit() {
		return temperatureUnit;
	}

	public void setTemperatureUnit(String temperatureUnit) {
		this.temperatureUnit = temperatureUnit;
	}

	public String getTemperatureTrend() {
		return temperatureTrend;
	}

	public void setTemperatureTrend(String temperatureTrend) {
		this.temperatureTrend = temperatureTrend;
	}

	public String getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}

	public String getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getShortForecast() {
		return shortForecast;
	}

	public void setShortForecast(String shortForecast) {
		this.shortForecast = shortForecast;
	}

	public String getDetailedForecast() {
		return detailedForecast;
	}

	public void setDetailedForecast(String detailedForecast) {
		this.detailedForecast = detailedForecast;
	}

	@Override
	public String toString() {
		return "WeatherPeriod [number=" + number + ", name=" + name + ", temperature=" + temperature
				+ ", temperatureUnit=" + temperatureUnit + ", shortForecast=" + shortForecast + "]";
	}

}
