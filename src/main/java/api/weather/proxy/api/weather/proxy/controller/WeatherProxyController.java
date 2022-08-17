package api.weather.proxy.api.weather.proxy.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import api.weather.proxy.api.weather.proxy.CoordinatesNotFoundException;
import api.weather.proxy.api.weather.proxy.model.ProxyPeriod;
import api.weather.proxy.api.weather.proxy.model.ProxyResponse;
import api.weather.proxy.api.weather.proxy.model.Stats;
import api.weather.proxy.api.weather.proxy.model.WeatherPeriod;
import api.weather.proxy.api.weather.proxy.service.WeatherApiCallService;

@RestController
public class WeatherProxyController {

	@Autowired
	private WeatherApiCallService apiCallSvc;

	// Returns an array of ProxyPeriod
	@GetMapping("forecast")
	public List<ProxyPeriod> getProxyForcastResponse(
			@RequestParam(required = false) Double lat,
			@RequestParam(required = false) Double lon) {

		if (lat == null || lon == null) {
			throw new CoordinatesNotFoundException();
		}

		try {

			List<ProxyPeriod> proxyPeriodList = getForecastPeriodList(lat, lon);

			return proxyPeriodList;
		} catch (Exception e) {

			throw new CoordinatesNotFoundException();
		}
	}

	// Returns a ProxyResponse including array of ProxyPeriod and Stats
	@GetMapping("forecast/plus")
	public ProxyResponse getProxyForcastPlusResponse(
			@RequestParam(required = false) Double lat,
			@RequestParam(required = false) Double lon) {

		if (lat == null || lon == null) {
			throw new CoordinatesNotFoundException();
		}

		try {

			List<ProxyPeriod> proxyPeriodList = getForecastPeriodList(lat, lon);
			Stats stats = getStats(proxyPeriodList);

			ProxyResponse response = new ProxyResponse(proxyPeriodList, stats);
			return response;

		} catch (Exception e) {

			throw new CoordinatesNotFoundException();
		}
	}

	// Returns a List of ProxyPeriod
	private List<ProxyPeriod> getForecastPeriodList(double lat, double lon) {

		List<ProxyPeriod> proxyPeriodList = new ArrayList<>();

		try {

			List<WeatherPeriod> periodList = apiCallSvc.getWeatherPeriodList(lat, lon);

			for (WeatherPeriod wp : periodList) {
				proxyPeriodList.add(new ProxyPeriod(wp.getNumber(), wp.getName(), wp.getStartTime(), wp.getEndTime(),
						wp.getIsDaytime(), wp.getTemperature(), wp.getTemperatureUnit(), wp.getTemperatureTrend(),
						wp.getWindSpeed(), wp.getWindDirection(), wp.getIcon(), wp.getShortForecast(),
						wp.getDetailedForecast()));
			}

		} catch (Exception e) {
			System.out.println("exception thrown at getForcastPeriodList");
			throw new CoordinatesNotFoundException();
		}

		return proxyPeriodList;
	}

	// Returns a Stats based on input of List<ProxyPeriod>
	private Stats getStats(List<ProxyPeriod> proxyPeriodList) {

		int averageTemperature = getAverageTemperature(proxyPeriodList);
		ProxyPeriod hottestPeriod = getHottestPeriod(proxyPeriodList);
		ProxyPeriod coldestPeriod = getCoolestPeriod(proxyPeriodList);

		Stats stats = new Stats(averageTemperature, hottestPeriod, coldestPeriod);

		return stats;
	}

	// returns averageTemp from a list of ProxyPeriod
	private int getAverageTemperature(List<ProxyPeriod> pList) {

		int averageTemp = 0;

		for (ProxyPeriod p : pList) {
			averageTemp += p.getTemperature();
		}
		averageTemp /= pList.size();

		return averageTemp;
	}

	// returns ProxyPeriod with the hottest temperature in a list of ProxyPeriod
	private ProxyPeriod getHottestPeriod(List<ProxyPeriod> pList) {

		ProxyPeriod p = pList.stream().max(Comparator.comparingInt(ProxyPeriod::getTemperature)).get();

		return p;
	}

	// returns ProxyPeriod with the coolest temperature in a list of ProxyPeriod
	private ProxyPeriod getCoolestPeriod(List<ProxyPeriod> pList) {

		ProxyPeriod p = pList.stream().min(Comparator.comparingInt(ProxyPeriod::getTemperature)).get();

		return p;
	}

	// error handling
	@ResponseBody
	@ExceptionHandler(CoordinatesNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String coordinatesNotFoundHandler(CoordinatesNotFoundException ex) {
		return ex.getMessage();
	}

}
