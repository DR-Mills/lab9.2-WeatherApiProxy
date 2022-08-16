package api.weather.proxy.api.weather.proxy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.weather.proxy.api.weather.proxy.model.IncomingApiModel.WeatherPeriod;
import api.weather.proxy.api.weather.proxy.service.WeatherApiCallService;

@RestController
public class WeatherProxyController {

	@Autowired
	private WeatherApiCallService apiCallSvc;

	@GetMapping("forecast")
	public List<WeatherPeriod> getForecastPeriodList(
			@RequestParam Double lat, 
			@RequestParam Double lon) {
		
		List<WeatherPeriod> periodList = apiCallSvc.getWeatherPeriodList(lat, lon);
		
		return periodList;
	}
		
	}