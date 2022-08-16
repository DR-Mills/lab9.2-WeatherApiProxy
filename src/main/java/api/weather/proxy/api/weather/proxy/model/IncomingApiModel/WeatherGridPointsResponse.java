package api.weather.proxy.api.weather.proxy.model.IncomingApiModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherGridPointsResponse {

	@JsonProperty("properties")
	private GridPoint gridPointProperties;

	public GridPoint getGridPointProperties() {
		return gridPointProperties;
	}

	public void setGridPointProperties(GridPoint gridPointProperties) {
		this.gridPointProperties = gridPointProperties;
	}
	
	
}
