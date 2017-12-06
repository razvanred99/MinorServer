package it.minoranza.minorstations.owm;

import java.io.IOException;

public class Principale {

    public static void main(String[] args){
        try {
            System.out.println(new OpenWeatherMap().requestWeather("veneto"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
