package api.weather.proxy.api.weather.proxy.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import api.weather.proxy.api.weather.proxy.CoordinatesNotFoundException;
import api.weather.proxy.api.weather.proxy.model.ProxyPeriod;
import api.weather.proxy.api.weather.proxy.model.ProxyResponse;
import api.weather.proxy.api.weather.proxy.model.Stats;
import api.weather.proxy.api.weather.proxy.service.WeatherApiCallService;

@RestController
public class WeatherProxyController {

	@Autowired
	private WeatherApiCallService apiCallSvc;

	// Returns an array of ProxyPeriod
	@GetMapping("forecast")
	public List<ProxyPeriod> getProxyForcastResponse(@RequestParam(required = false) Double lat,
			@RequestParam(required = false) Double lon) {

		if (lat == null || lon == null) {
			throw new CoordinatesNotFoundException();
		}

		try {

			List<ProxyPeriod> proxyPeriodList = getProxyPeriodList(lat, lon);

			return proxyPeriodList;

		} catch (Exception e) {

			System.out.println("error @ getProxyForcastResponse() in WeatherProxyController");
			throw new CoordinatesNotFoundException();
		}
	}

	// Returns a ProxyResponse including array of ProxyPeriod and Stats
	@GetMapping("forecast/plus")
	public ProxyResponse getProxyForcastPlusResponse(@RequestParam(required = false) Double lat,
			@RequestParam(required = false) Double lon) {

		if (lat == null || lon == null) {
			throw new CoordinatesNotFoundException();
		}

		try {

			List<ProxyPeriod> proxyPeriodList = getProxyPeriodList(lat, lon);
			Stats stats = getStats(proxyPeriodList);

			ProxyResponse response = new ProxyResponse(proxyPeriodList, stats);
			return response;

		} catch (Exception e) {

			System.out.println("error @ getProxyForcastPlusResponse() in WeatherProxyController");
			throw new CoordinatesNotFoundException();
		}
	}

	// Returns a List of ProxyPeriod
	private List<ProxyPeriod> getProxyPeriodList(Double lat, Double lon) {

		try {

			List<ProxyPeriod> proxyPeriodList = apiCallSvc.getPeriodList(lat, lon);

			for (ProxyPeriod p : proxyPeriodList) {
				p.setTemperatureCelsius(p.getTemperature());
				;
			}

			return proxyPeriodList;

		} catch (Exception e) {
			System.out.println("exception thrown at getForcastPeriodList");
			throw new CoordinatesNotFoundException();
		}

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
