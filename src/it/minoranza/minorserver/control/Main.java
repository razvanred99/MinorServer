package it.minoranza.minorserver.control;

import javafx.fxml.Initializable;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;

import static it.minoranza.minorclient.Principale.PORT;

public class Main implements Initializable {

    private Thread thread;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ChangeListener change = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println("Blup");
            }
        };

        thread = new Thread(() -> {
            try{
                final ServerSocket ss=new ServerSocket(PORT);

                System.out.println("HI");
                //change.notify();
                new Thread(() -> {
                    try {
                        while (true) {
                            new RunVirtualCommunication(ss.accept()).start();
                        }
                    } catch (IOException ioexc) {
                        ioexc.printStackTrace();
                    }
                }).start();
            } catch(IOException ioexc){
                ioexc.printStackTrace();
            }
        });
    }

    public void showAlert() {
        System.err.println("hi");
    }

    public void runServer() {
        thread.start();

    }

}
