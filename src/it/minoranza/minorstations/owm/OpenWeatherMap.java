package it.minoranza.minorstations.owm;

import it.minoranza.commons.City;
import it.minoranza.commons.Station;
import it.minoranza.minorstations.commons.AbstractStation;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

public class OpenWeatherMap extends AbstractStation {

    private final JSONArray cities;
    private final static int MAX_SUGG = 10;

    protected OpenWeatherMap() throws IOException {
        super(Station.OWM, "86b0ab1880d7ae578438074239305b4d", "http://api.openweathermap.org/data/2.5/weather?lang=it&appid=", "&id=");

        final InputStream in = getClass().getResourceAsStream("/city.list.json");
        final StringWriter reader = new StringWriter();

        IOUtils.copy(in, reader);
        cities = new JSONArray(reader.toString());

        in.close();
        reader.close();
    }

    @Override
    public JSONArray getSugg(final String query) {

        final JSONArray result=new JSONArray();

        for(int i=0;i<cities.length();i++) {
            final JSONObject obj=cities.getJSONObject(i);
            if (new String(obj.getString("name")+" "+obj.getString("country")).toLowerCase().startsWith(query.trim().replaceAll(" +"," ").toLowerCase()))
                result.put(obj);

            if(result.length()>MAX_SUGG)
                break;
        }
        return stardardizePlaces(result);
    }

    @Override
    public JSONArray stardardizePlaces(final JSONArray array) {

        final JSONArray ar=new JSONArray();

        for(int i=0;i<array.length();i++){
            final JSONObject object=new JSONObject();
            final JSONObject piece=ar.getJSONObject(i);
            object.put(City.key.name(),piece.getString("id"));
            object.put(City.city_name.name(),piece.getString("name"));
            object.put(City.country.name(),piece.getString("country"));

            ar.put(object);
        }

        return ar;
    }
}
