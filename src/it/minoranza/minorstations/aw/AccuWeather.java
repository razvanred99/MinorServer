package it.minoranza.minorstations.aw;

import it.minoranza.commons.Station;
import it.minoranza.minorstations.commons.AbstractStation;

public class AccuWeather extends AbstractStation {

    protected AccuWeather() {
        super(Station.AW, "NpCeiuIbLXG2IxlcvqixN1okgTeRilNt","http://dataservice.accuweather.com/locations/v1/cities/autocomplete?language=it-it&apikey=","&q=");

    }

}
