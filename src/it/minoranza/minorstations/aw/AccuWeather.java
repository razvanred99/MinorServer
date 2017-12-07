package it.minoranza.minorstations.aw;

import it.minoranza.commons.City;
import it.minoranza.commons.Station;
import it.minoranza.minorstations.commons.AbstractStation;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class AccuWeather extends AbstractStation {

    protected AccuWeather() {
        super(Station.AW, "NpCeiuIbLXG2IxlcvqixN1okgTeRilNt","http://dataservice.accuweather.com/locations/v1/cities/autocomplete?language=it-it&apikey=","&q=");

    }

    @Override
    public JSONArray stardardizePlaces(final JSONArray array) {

        final JSONArray ar=new JSONArray();

        for(int i=0;i<array.length();i++){
            final JSONObject object=new JSONObject();
            final JSONObject piece=ar.getJSONObject(i);
            object.put(City.key.name(),piece.getString("Key"));
            object.put(City.city_name.name(),piece.getString("LocalizedName")+" "+piece.getJSONObject("AdministrativeArea").getString("ID"));
            object.put(City.country.name(),piece.getJSONObject("Country").getString("LocalizedName"));

            ar.put(object);
        }

        return ar;
    }

    @Override
    public JSONArray getSugg(final String query) throws IOException{

        /*final JSONArray array=new JSONArray(new AccuWeather().request("san polo"));

        for(int i=0;i<array.length();i++){
            JSONObject obj= array.getJSONObject(i);
            System.out.println(obj.getString("LocalizedName")+" "+obj.getJSONObject("AdministrativeArea").getString("ID")+" - "+obj.getJSONObject("Country").getString("LocalizedName"));
        }*/

        return stardardizePlaces(new JSONArray(request(query)));
    }
}
