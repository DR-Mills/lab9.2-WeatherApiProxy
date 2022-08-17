package api.weather.proxy.api.weather.proxy.model;

import java.util.List;

public class ProxyResponse {

	private List<ProxyPeriod> periods;
	
	private Stats stats;

	// constructor
	public ProxyResponse(List<ProxyPeriod> periods, Stats stats) {
		this.periods = periods;
		this.stats = stats;
	}
	
	public ProxyResponse(List<ProxyPeriod> periods) {
		this.periods = periods;
	}
	

	// getters & setters
	public List<ProxyPeriod> getPeriods() {
		return periods;
	}

	public void setPeriods(List<ProxyPeriod> periods) {
		this.periods = periods;
	}

	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}
	
	
}
