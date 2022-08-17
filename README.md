LAB 9.2 - WEATHER API PROXY
Task: The US Government provides a public weather API at weather.gov, but to use it you have to know weather station identifiers and gridpoint coordinates. Create a proxy API that returns weather data from the National Weather Service API, but uses standard latitude and longitude for locations. Additionally, you will augment the data from the API with additional statistics and insights based on your own calculations.

Setup
Create a new Spring project without a database. However, it will not have any views.

For this lab you can also use the Distributed Systems Tester client app for testing. There are instructions for downloading and running that app in the readme.md here. See "Testing Your API" below.

PART ONE - Simple Proxy using latitude and longitude
Create a single API endpoint. It takes in latitude and longitude. It makes a call to the weather.gov gridpoints API using the latitude and longitude to get the forecast URL. Once it has the forecast URL, it uses that to make a second call which will get the actual forecast for that location. Finally, pull out the periods array from the forecast JSON response. That array will be the response of our API.

Build Specifications
Here's an overview of our endpoint:
GET /forecast
Response: A JSON array of objects, which is the array of periods received from the weather.gov forecast API. (See Sample Responses below.)
Response Code: 200 (OK)
Query string parameters
lat - a double number for latitude. e.g. 42.395387
lon - a double number for longitude. e.g. -83.0559509
Sample Request: http://localhost:8080/forecast?lat=42.395387&lon=-83.0559509
See Sample Responses below.

The National Weather Service API
This API is for public use and does not require an API key. See an overview here.

For our use, we will be calling the /points endpoint to find the gridpoint information based on latitude and longitude.

Example: https://api.weather.gov/points/39.7456,-97.0892

That response includes a forecast URL, labeled "forecast" inside of "properties". This URL is the API URL we need to call to get the actual forecast data at the /gridpoints/…/forecast endpoint.

Example: https://api.weather.gov/gridpoints/TOP/31,80/forecast




PART TWO - Additional Data and Stats
Add an additional endpoint that returns a JSON object with both the array of forecast periods and an object which contains stats. The objects in the forecast period also have additional information added to them.

Build Specifications
For your API model you will need three classes:
ProxyPeriod - this represents one of the forecast periods in the array. It should be a subclass of the model class used for the periods in the NWS API. It inherits all the fields from that class, but you will add some additional fields.
Stats - this has fields for the additional stats provided in the response.
ProxyResponse - this is your main response body. It includes two properties: List<ProxyPeriod> periods and Stats stats.

Add one additional field to ProxyPeriod:
Add a temperatureCelsius field. Calculate the temperature in celsius for this period, rounding to the nearest integer.

Add three fields to Stats:
averageTemperature (int) - the average (mean) temperature for all the forecast periods, rounded to the nearest integer.
hottestPeriod (ProxyPeriod) - the period object with the maximum temperature of all the forecast periods.
coldestPeriod (ProxyPeriod) - the period object with the minimum temperature of all the forecast periods.


Extended Challenges
Add additional fields to ProxyPeriod:
maxWindSpeed (int) - Parse out the maximum wind speed from the windSpeed field. windSpeed is a string such as "7 mph" or "15 to 25 mph". So find the last number in the string. (e.g. 7 or 25). Regular Expressions might help here, or just start with an online search.
rating (int) - Determine a rating for the given period based on factors like temperature, conditions (shortForecast), and wind. You can come up with your own formula, but here's an example:
50 points for exactly 72 degrees. -1 point for every degree too high or low, but can't go below 0 points. This part of the score will be between 0 and 50.
Add 50 points if the shortForecast includes the word "sun". Or 20 points for rain, shower, storm, or wind. Or 0 points for "snow", "ice", "hail", or "sleet". If it contains none of those words, give 30 points.
temperatureChange (Integer) - Determine how many degrees this period is from the previous period (positive or negative). The first period will just be null since we don't know what the temperature was before.
Extra challenge: only compare each period to the previous period of the same type: daytime vs nighttime.

Add additional fields to Stats:
* averageDaytimeTemperature (int) - the average (mean) temperature for all the forecast periods where isDaytime is true, rounded to the nearest integer.
* averageNighttimeTemperature (int) - the average (mean) temperature for all the forecast periods where isDaytime is false, rounded to the nearest integer.
* bestPeriod (ProxyPeriod) - the period object with the maximum rating of all the forecast periods.

Add an optional query string parameter to the /forecast endpoint.
includeOnly (string) - If the value is "day" include only periods where daytime is true. If the value is "night", include only periods where daytime is false. If this parameter is missing or is any other value, include all periods in the response. Sample Request: http://localhost:8080/forecast/plus?lat=42.395387&lon=-83.0559509&includeOnly=day
