package api.weather.proxy.api.weather.proxy.model.IncomingApiModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherPointsResponse {

	@JsonProperty("properties")
	private Point pointProperties;

	
	public Point getPointProperties() {
		return pointProperties;
	}

	public void setPointProperties(Point pointProperties) {
		this.pointProperties = pointProperties;
	}
	
	
}
