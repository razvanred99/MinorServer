package it.minoranza.minorstations.owm;

import it.minoranza.commons.City;
import it.minoranza.commons.Forecast;
import it.minoranza.commons.Icon;
import it.minoranza.commons.Station;
import it.minoranza.minorstations.commons.AbstractStation;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

public class OpenWeatherMap extends AbstractStation {

    private final JSONArray cities;
    private final static int MAX_SUGG = 10;

    protected OpenWeatherMap() throws IOException {
        super(Station.OWM, "86b0ab1880d7ae578438074239305b4d");

        final InputStream in = getClass().getResourceAsStream("/city.list.json");
        final StringWriter reader = new StringWriter();

        IOUtils.copy(in, reader);
        cities = new JSONArray(reader.toString());

        in.close();
        reader.close();
    }

    @Override
    public JSONArray getSugg(final String query) {

        final JSONArray result = new JSONArray();

        for (int i = 0; i < cities.length(); i++) {
            final JSONObject obj = cities.getJSONObject(i);
            if (new String(obj.getString("name") + " " + obj.getString("country")).toLowerCase().startsWith(query.trim().replaceAll(" +", " ").toLowerCase()))
                result.put(obj);

            if (result.length() > MAX_SUGG)
                break;
        }
        return stardardizePlaces(result);
    }

    @Override
    public JSONObject getWeather(final String id) throws IOException {

        final JSONObject object = new JSONObject(request(id, "http://api.openweathermap.org/data/2.5/weather?units=metric&lang=it&appid=", "&id="));
        final JSONObject toReturn = new JSONObject();
        final JSONObject weather = object.getJSONArray("weather").getJSONObject(0);

        toReturn.put(Forecast.values()[0].name(), weather.getString("description"));
        final Icon toPut;
        switch (weather.getString("icon")) {
            case "01d":
                toPut = Icon.sun;
                break;
            case "02d":
                toPut = Icon.cloudy_sun;
                break;
            case "03d":
            case "03n":
            case "04n":
            case "04d":
                toPut = Icon.cloudy;
                break;
            case "50d":
            case "50n":
                toPut=Icon.foggy;
                break;

            case"10d":
                toPut=Icon.showers;
                break;
            case "11d":
            case "11n":
                toPut=Icon.storm;
                break;
            case "09d":
            case "09n":
                toPut=Icon.rain;
                break;
            case "13d":
            case "13n":
                toPut=Icon.snow;
                break;
            case "01n":
                toPut=Icon.moon;
                break;
            case "02n":
                toPut=Icon.cloudy_moon;
                break;
            case "10n":
                toPut= Icon.moon_showers;
                break;
            default:
                toPut=Icon.unknown;
                break;
        }

        toReturn.put(Forecast.values()[1].name(), toPut.name());
        toReturn.put(Forecast.values()[2].name(), object.getJSONObject("main").getString("temp"));

        return toReturn;
    }

    @Override
    public JSONArray stardardizePlaces(final JSONArray array) {

        final JSONArray ar = new JSONArray();

        for (int i = 0; i < array.length(); i++) {
            final JSONObject object = new JSONObject();
            final JSONObject piece = ar.getJSONObject(i);
            object.put(City.key.name(), piece.getString("id"));
            object.put(City.city_name.name(), piece.getString("name"));
            object.put(City.country.name(), piece.getString("country"));

            ar.put(object);
        }

        return ar;
    }
}
