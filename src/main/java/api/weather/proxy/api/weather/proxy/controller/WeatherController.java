package api.weather.proxy.api.weather.proxy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import api.weather.proxy.api.weather.proxy.model.IncomingApiModel.WeatherPeriod;
import api.weather.proxy.api.weather.proxy.service.WeatherApiCallService;

@Controller
public class WeatherController {

	@Autowired
	private WeatherApiCallService weatherApiCallService;

	
	
	
	// TESTS
//	@RequestMapping("/test-get-url")
//	public String testGetUrl(@RequestParam Double lat, @RequestParam Double lon) {
//		String url = weatherApiCallService.getForcastUrl(lat, lon);
//		System.out.println(url);
//		return "bacon";
//	}
//
//	@RequestMapping("/test-get-periods")
//	public String testPeriodListReturn(@RequestParam Double lat, @RequestParam Double lon) {
//		List<WeatherPeriod> periodList = weatherApiCallService.getWeatherPeriodList(lat, lon);
//		for (WeatherPeriod wp : periodList) {
//			System.out.println(wp);
//		}
//		return "bacon";
//	}

}
