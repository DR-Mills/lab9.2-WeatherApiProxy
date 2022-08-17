package api.weather.proxy.api.weather.proxy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import api.weather.proxy.api.weather.proxy.model.ProxyPeriod;
import api.weather.proxy.api.weather.proxy.service.WeatherApiCallService;

class WeatherProxyControllerTests {

	WeatherApiCallService apiCallSvc = new WeatherApiCallService();

	@Test
	void testAverageTempCalc() {

		List<ProxyPeriod> proxyList = new ArrayList<ProxyPeriod>();
		proxyList.add(new ProxyPeriod(1, "Sunday", "", "", false, 75, "", "", "", "", "", "", ""));
		proxyList.add(new ProxyPeriod(1, "Monday", "", "", false, 76, "", "", "", "", "", "", ""));
		proxyList.add(new ProxyPeriod(1, "Tuesday", "", "", false, 78, "", "", "", "", "", "", ""));
		proxyList.add(new ProxyPeriod(1, "Wednesday", "", "", false, 80, "", "", "", "", "", "", ""));
		proxyList.add(new ProxyPeriod(1, "Thursday", "", "", false, 79, "", "", "", "", "", "", ""));
		proxyList.add(new ProxyPeriod(1, "Friday", "", "", false, 80, "", "", "", "", "", "", ""));
		proxyList.add(new ProxyPeriod(1, "Saturday", "", "", false, 85, "", "", "", "", "", "", ""));

		int averageTemp = 0;
		for (ProxyPeriod p : proxyList) {
			averageTemp += p.getTemperature();
		}
		averageTemp /= proxyList.size();

		assertEquals(79, averageTemp);
	}

	@Test
	void testGetHottestPeriod() {

		List<ProxyPeriod> proxyList = new ArrayList<ProxyPeriod>();
		proxyList.add(new ProxyPeriod(1, "Sunday", "", "", false, 75, "", "", "", "", "", "", ""));
		proxyList.add(new ProxyPeriod(2, "Monday", "", "", false, 76, "", "", "", "", "", "", ""));
		proxyList.add(new ProxyPeriod(3, "Tuesday", "", "", false, 78, "", "", "", "", "", "", ""));
		proxyList.add(new ProxyPeriod(4, "Wednesday", "", "", false, 80, "", "", "", "", "", "", ""));
		proxyList.add(new ProxyPeriod(5, "Thursday", "", "", false, 79, "", "", "", "", "", "", ""));
		proxyList.add(new ProxyPeriod(6, "Friday", "", "", false, 80, "", "", "", "", "", "", ""));
		proxyList.add(new ProxyPeriod(7, "Saturday", "", "", false, 85, "", "", "", "", "", "", ""));

		ProxyPeriod actual = proxyList.stream().max(Comparator.comparingInt(ProxyPeriod::getTemperature)).get();
		ProxyPeriod expected = proxyList.get(6);

		assertEquals(expected, actual);

	}
	
	@Test
	void testGetCoolestPeriod() {

		List<ProxyPeriod> proxyList = new ArrayList<ProxyPeriod>();
		proxyList.add(new ProxyPeriod(1, "Sunday", "", "", false, 75, "", "", "", "", "", "", ""));
		proxyList.add(new ProxyPeriod(2, "Monday", "", "", false, 76, "", "", "", "", "", "", ""));
		proxyList.add(new ProxyPeriod(3, "Tuesday", "", "", false, 78, "", "", "", "", "", "", ""));
		proxyList.add(new ProxyPeriod(4, "Wednesday", "", "", false, 80, "", "", "", "", "", "", ""));
		proxyList.add(new ProxyPeriod(5, "Thursday", "", "", false, 79, "", "", "", "", "", "", ""));
		proxyList.add(new ProxyPeriod(6, "Friday", "", "", false, 80, "", "", "", "", "", "", ""));
		proxyList.add(new ProxyPeriod(7, "Saturday", "", "", false, 85, "", "", "", "", "", "", ""));

		ProxyPeriod actual = proxyList.stream().min(Comparator.comparingInt(ProxyPeriod::getTemperature)).get();
		ProxyPeriod expected = proxyList.get(0);

		assertEquals(expected, actual);

	}
	
}