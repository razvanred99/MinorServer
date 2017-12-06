package it.minoranza.commons;

public enum Station {

    OWM("OpenWeatherMap"),
    AW("AccuWeather");

    private final String descrizione;

    Station(final String descrizione){
        this.descrizione=descrizione;
    }

    public final String getDescrizione(){
        return descrizione;
    }

}
