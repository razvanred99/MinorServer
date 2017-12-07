package it.minoranza.minorstations.aw;

import it.minoranza.minorstations.commons.RunServer;

import java.io.IOException;

public class Principale {

    private final static int PORT=1026;

    public static void main(String[] args){
        try{

            new RunServer(new AccuWeather(),PORT).run();

        }catch(IOException io){
            io.printStackTrace();
        }
    }
}
