package it.minoranza.minorstations.owm;

import it.minoranza.commons.Station;
import it.minoranza.minorstations.commons.AbstractStation;

public class OpenWeatherMap extends AbstractStation {

    protected OpenWeatherMap() {
        super(Station.OWM, "86b0ab1880d7ae578438074239305b4d","http://api.openweathermap.org/data/2.5/weather?appid=","&q=");
    }

}
