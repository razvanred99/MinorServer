package it.minoranza.minorserver;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Principale extends Application {

    private final int PORT=7025;

    @Override
    public void start(final Stage primaryStage) throws IOException{

        final FXMLLoader loader=new FXMLLoader(getClass().getResource("view/main.fxml"));
        primaryStage.setScene(new Scene(loader.load(), 300, 275));
        //final Main controller=loader.<Main>getController();


        //final Thread thread=
        //controller.attach(thread);

        primaryStage.setTitle("Cruscotto - MinorServer");
        primaryStage.show();

        primaryStage.onCloseRequestProperty().addListener(new ChangeListener<EventHandler<WindowEvent>>() {
            @Override
            public void changed(ObservableValue<? extends EventHandler<WindowEvent>> observable, EventHandler<WindowEvent> oldValue, EventHandler<WindowEvent> newValue) {
                System.exit(0);
            }
        });

    }


    public static void main(String[] args) {
        launch(args);
    }
}
