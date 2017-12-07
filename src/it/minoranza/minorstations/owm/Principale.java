package it.minoranza.minorstations.owm;

import it.minoranza.minorstations.commons.RunServer;

import java.io.IOException;

public class Principale {

    private static final int PORT=1025;

    public static void main(String[] args){

        try {
            new RunServer(new OpenWeatherMap(),PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
