package it.minoranza.minorclient.control;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import it.minoranza.commons.Station;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Main implements Initializable {

    @FXML
    private JFXTextField txfCity;

    @FXML
    private BorderPane container;

    @FXML
    private JFXComboBox comboStations;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        comboStations.getItems().addAll(Station.OWM.getDescrizione(),Station.AW.getDescrizione());
        comboStations.getSelectionModel().selectFirst();

    }

    public void search(){

    }

}
