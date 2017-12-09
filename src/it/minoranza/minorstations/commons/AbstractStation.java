package it.minoranza.minorstations.commons;

import it.minoranza.commons.Station;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class AbstractStation {

    protected final Station station;
    protected final String API_KEY;
    //protected final String urlMain,urlLocation;

    protected AbstractStation(final Station station, final String API_KEY){
        this.API_KEY=API_KEY;
        this.station=station;

    }

    public final Station getStation(){
        return station;
    }

    protected String request(final String param,final String urlMain,final String urlTail) throws IOException {

        final StringBuilder build=new StringBuilder();
        build.append(urlMain);
        build.append(API_KEY);
        build.append(urlTail);
        build.append(param);

        final URL url=new URL(build.toString().trim().replaceAll(" +", " ").replaceAll(" ","%20"));

        System.out.println(build.toString());

        final HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        if(connection.getResponseCode()!=200) //città non trovata
            throw new IOException();

        final StringWriter reader= new StringWriter();
        final InputStream in=connection.getInputStream();

        IOUtils.copy(in,reader);
        in.close();

        final String buffer=reader.toString();
        reader.close();
        connection.disconnect();

        return buffer;
    }

    public abstract JSONArray stardardizePlaces(final JSONArray array);

    public abstract JSONArray getSugg(final String query) throws IOException;

    public abstract JSONObject getWeather(final String id) throws IOException;

}
