package api.weather.proxy.api.weather.proxy.model;

public class Stats {

	private int averageTemperature;
	
	private ProxyPeriod hottestPeriod;
	
	private ProxyPeriod coldestPeriod;

	//constructor
	public Stats(int averageTemperature, ProxyPeriod hottestPeriod, ProxyPeriod coldestPeriod) {
		this.averageTemperature = averageTemperature;
		this.hottestPeriod = hottestPeriod;
		this.coldestPeriod = coldestPeriod;
	}
	
	
	// getters & setters
	public int getAverageTemperature() {
		return averageTemperature;
	}


	public void setAverageTemperature(int averageTemperature) {
		this.averageTemperature = averageTemperature;
	}

	public ProxyPeriod getHottestPeriod() {
		return hottestPeriod;
	}

	public void setHottestPeriod(ProxyPeriod hottestPeriod) {
		this.hottestPeriod = hottestPeriod;
	}

	public ProxyPeriod getColdestPeriod() {
		return coldestPeriod;
	}

	public void setColdestPeriod(ProxyPeriod coldestPeriod) {
		this.coldestPeriod = coldestPeriod;
	}
	
	
}
