package api.weather.proxy.api.weather.proxy.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import api.weather.proxy.api.weather.proxy.model.IncomingApiModel.WeatherGridPointsResponse;
import api.weather.proxy.api.weather.proxy.model.IncomingApiModel.WeatherPeriod;
import api.weather.proxy.api.weather.proxy.model.IncomingApiModel.WeatherPointsResponse;

@Service
public class WeatherApiCallService {

	private RestTemplate rt = new RestTemplate();
	
	
	// returns list of periods (under properties) from weather forecast
	public List<WeatherPeriod> getWeatherPeriodList(Double lat, Double lon) {
		
		String url = getForcastUrl(lat, lon);
		
		WeatherGridPointsResponse gridResponse = rt.getForObject(url, WeatherGridPointsResponse.class);
		
		return gridResponse.getGridPointProperties().getPeriodsList();		
	}

	
	// returns url (weather.gov grid-points format) for weather forecast based on latitude & longitude
	public String getForcastUrl(Double lat, Double lon) {
		
		String url = "https://api.weather.gov/points/{lat},{lon}";
		
		WeatherPointsResponse pointsResponse = rt.getForObject(url, WeatherPointsResponse.class, lat, lon);
		
		return pointsResponse.getPointProperties().getForecast();	
	}
}
