package api.weather.proxy.api.weather.proxy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.weather.proxy.api.weather.proxy.model.ProxyPeriod;
import api.weather.proxy.api.weather.proxy.model.WeatherPeriod;
import api.weather.proxy.api.weather.proxy.service.WeatherApiCallService;

@RestController
public class WeatherProxyController {

	@Autowired
	private WeatherApiCallService apiCallSvc;

	@GetMapping("forecast")
	public List<ProxyPeriod> getForecastPeriodList(@RequestParam Double lat, @RequestParam Double lon) {

		List<WeatherPeriod> periodList = apiCallSvc.getWeatherPeriodList(lat, lon);

		List<ProxyPeriod> proxyPeriodList = new ArrayList<>();

		for (WeatherPeriod wp : periodList) {
			proxyPeriodList.add(
					new ProxyPeriod(
							wp.getNumber(), wp.getName(), wp.getStartTime(), wp.getEndTime(), wp.getIsDaytime(),
							wp.getTemperature(), wp.getTemperatureUnit(), wp.getTemperatureTrend(), wp.getWindSpeed(),
							wp.getWindDirection(), wp.getIcon(), wp.getShortForecast(), wp.getDetailedForecast()));
		}

		return proxyPeriodList;
	}

}