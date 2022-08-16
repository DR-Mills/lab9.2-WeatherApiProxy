package api.weather.proxy.api.weather.proxy.model.OutgoingApiModel;

import api.weather.proxy.api.weather.proxy.model.IncomingApiModel.WeatherPeriod;

public class ProxyPeriod extends WeatherPeriod {

	private int temperatureCelsius;

	public int getTemperatureCelsius() {
		return temperatureCelsius;
	}

	public void setTemperatureCelsius(int temperatureCelsius) {
		this.temperatureCelsius = temperatureCelsius;
	}
	
	
	
}
