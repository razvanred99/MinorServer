package it.minoranza.minorstations.commons;

import org.json.JSONException;
import org.json.JSONObject;

import it.minoranza.commons.Request;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class RunServer {

    private final AbstractStation abstractStation;
    private final int PORT;

    public RunServer(final AbstractStation abstractStation,final int PORT){
        this.abstractStation=abstractStation;
        this.PORT=PORT;
    }

    public void run() throws IOException{
        final ServerSocket ss=new ServerSocket(PORT);

        while(true){
            final Socket s=ss.accept();
            final InputStream in=s.getInputStream();
            final OutputStream out=s.getOutputStream();
            final Scanner scanner=new Scanner(in);
            final PrintWriter printWriter=new PrintWriter(out,true);

            final JSONObject request=new JSONObject(scanner.nextLine());

            try{
                printWriter.println(abstractStation.getSugg(request.getString(Request.values()[0].name())));
            }catch(JSONException exc){
                printWriter.println(abstractStation.getWeather(request.getString(Request.values()[1].name())));
            }

            printWriter.println();

            scanner.close();
            s.close();
            in.close();
            out.close();
        }
    }
}
