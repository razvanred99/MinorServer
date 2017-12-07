package it.minoranza.minorstations.aw;

import it.minoranza.commons.City;
import it.minoranza.commons.Forecast;
import it.minoranza.commons.Icon;
import it.minoranza.commons.Station;
import it.minoranza.minorstations.commons.AbstractStation;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class AccuWeather extends AbstractStation {

    protected AccuWeather() {
        super(Station.AW, "NpCeiuIbLXG2IxlcvqixN1okgTeRilNt");
        //2542114
    }

    @Override
    public JSONArray stardardizePlaces(final JSONArray array) {

        final JSONArray ar = new JSONArray();

        for (int i = 0; i < array.length(); i++) {
            final JSONObject object = new JSONObject();
            final JSONObject piece = ar.getJSONObject(i);
            object.put(City.key.name(), piece.getString("Key"));
            object.put(City.city_name.name(), piece.getString("LocalizedName") + " " + piece.getJSONObject("AdministrativeArea").getString("ID"));
            object.put(City.country.name(), piece.getJSONObject("Country").getString("LocalizedName"));

            ar.put(object);
        }

        return ar;
    }

    @Override
    public JSONArray getSugg(final String query) throws IOException {

        return stardardizePlaces(new JSONArray(request(query, "http://dataservice.accuweather.com/locations/v1/cities/autocomplete?language=it-it&apikey=", "&q=")));
    }

    @Override
    public JSONObject getWeather(final String id) throws IOException {

        final JSONObject object = new JSONArray(request("", "http://dataservice.accuweather.com/currentconditions/v1/" + id + ".json?anguage=it-it&apikey=", "")).getJSONObject(0);
        final JSONObject toReturn = new JSONObject();

        toReturn.put(Forecast.description.name(), object.getString("WeatherText"));

        final int icon = object.getInt("WeatherIcon");
        final Icon toPut;

        switch (icon) {
            default:
                toPut = Icon.unknown;
                break;
            case 1:
            case 2:
                toPut = Icon.sun;
                break;
            case 3:
            case 4:
                toPut = Icon.cloudy_sun;
                break;
            case 5:
                toPut = Icon.foggy_sun;
                break;
            case 6:
            case 7:
            case 8:
                toPut = Icon.cloudy;
                break;
            case 11:
                toPut = Icon.foggy;
                break;
            case 12:
                toPut = Icon.showers;
                break;
            case 13:
            case 14:
                toPut = Icon.sun_showers;
                break;
            case 15:
            case 42:
                toPut = Icon.storm;
                break;
            case 16:
            case 17:
                toPut = Icon.sun_storm;
                break;
            case 18:
                toPut = Icon.rain;
                break;
            case 20:
            case 21:
            case 23:
                toPut = Icon.sun_snow;
                break;
            case 22:
            case 25:
            case 26:
            case 19:
                    toPut=Icon.snow;
                    break;
            case 24:
                toPut=Icon.ice;
                break;
            case 29:
                toPut=Icon.rain_snow;
                break;
            case 30:
                toPut=Icon.hot;
                break;
            case 31:
                toPut=Icon.cold;
                break;
            case 32:
                toPut=Icon.windy;
                break;
            case 33:
            case 34:
                toPut=Icon.moon;
                break;
            case 35:
            case 36:
            case 38:
                toPut=Icon.cloudy_moon;
                break;
            case 37:
                toPut=Icon.foggy_moon;
                break;
            case 39:
            case 40:
                toPut=Icon.cloudy_moon;
                break;
            case 41:
                toPut=Icon.storm_moon;
                break;
            case 43:
            case 44:
                toPut=Icon.snow_moon;
                break;

        }

        toReturn.put(Forecast.icon.name(), toPut.name());
        toReturn.put(Forecast.temp.name(), object.getJSONObject("Temperature").getJSONObject("Metric").getString("Value"));

        return toReturn;
    }
}
