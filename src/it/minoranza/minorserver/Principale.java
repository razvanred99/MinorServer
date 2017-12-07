package it.minoranza.minorserver;

import it.minoranza.minorserver.control.RunVirtualCommunication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;

public class Principale extends Application {

    private final int PORT=1024;

    @Override
    public void start(final Stage primaryStage) throws IOException{

        final Parent root = FXMLLoader.load(getClass().getResource("view/main.fxml"));
        primaryStage.setTitle("Cruscotto - MinorServer");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        final Thread thread=new Thread(() -> {
            try {

                ServerSocket ss=new ServerSocket(PORT);

                new Thread(() -> {
                    try{
                        while (true) {
                            new RunVirtualCommunication(ss.accept()).start();
                        }
                    }catch(IOException ioexc){
                        ioexc.printStackTrace();
                    }
                }).start();


            } catch (IOException exc) {
                System.err.println("Errore di creazione del server");
                exc.printStackTrace();
            }
        });

        thread.start();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
