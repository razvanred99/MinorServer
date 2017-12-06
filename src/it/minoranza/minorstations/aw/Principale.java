package it.minoranza.minorstations.aw;

import java.io.IOException;

public class Principale {

    public static void main(String[] args){
        try{
            System.out.println(new AccuWeather().requestWeather("san "));
        }catch(IOException io){
            io.printStackTrace();
        }
    }
}
