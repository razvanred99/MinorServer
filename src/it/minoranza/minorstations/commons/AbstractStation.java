package it.minoranza.minorstations.commons;

import it.minoranza.commons.Station;
import org.apache.commons.io.IOUtils;

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
    protected final String urlMain,urlLocation;

    protected AbstractStation(final Station station, final String API_KEY,final String urlMain,final String urlLocation){
        this.API_KEY=API_KEY;
        this.station=station;
        this.urlLocation=urlLocation;
        this.urlMain=urlMain;
    }

    public final Station getStation() throws MalformedURLException{
        return station;
    }

    public String requestWeather(final String city) throws IOException {

        final StringBuilder build=new StringBuilder();
        build.append(urlMain);
        build.append(API_KEY);
        build.append(urlLocation);
        build.append(city);

        URL url=new URL(build.toString().replaceAll(" ","%20"));
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        if(connection.getResponseCode()!=200) //città non trovata
            throw new IOException();

        StringWriter reader= new StringWriter();
        InputStream in=connection.getInputStream();

        IOUtils.copy(in,reader);

        return reader.toString();
    }



}
