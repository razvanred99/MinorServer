package it.minoranza.minorserver.model;

import it.minoranza.commons.Station;

import java.net.InetAddress;
import java.net.UnknownHostException;

public enum LocationStation{

    OWM(Station.OWM,1025,"192.168.1.33"),
    AW(Station.AW,1026,"192.168.1.34");

    private final Station station;
    private final String ip;
    private final int port;

    LocationStation(final Station station,final int port, final String ip){
        this.station=station;
        this.ip=ip;
        this.port=port;
    }

    public final int getPort(){
        return port;
    }

    public final Station getStation(){
        return station;
    }

    public final InetAddress getIp() throws UnknownHostException{
        return InetAddress.getByName(ip);
    }

}
