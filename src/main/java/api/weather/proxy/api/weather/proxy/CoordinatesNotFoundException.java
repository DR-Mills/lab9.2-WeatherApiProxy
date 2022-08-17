package api.weather.proxy.api.weather.proxy;

public class CoordinatesNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public CoordinatesNotFoundException() {
		super("Wookiees are from planet Kashyyyk and your request coordinates (latitude & longitude) could not be found");
	}
}
