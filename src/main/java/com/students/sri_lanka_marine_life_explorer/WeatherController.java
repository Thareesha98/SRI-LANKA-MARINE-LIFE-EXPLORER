/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.students.sri_lanka_marine_life_explorer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


/**
 * FXML Controller class
 *
 * @author thareesha
 */


public class WeatherController implements Initializable {

    @FXML
    private Label cityLabel;
    
    @FXML
    private Label temperatureLabel;
    
    @FXML
    private Button refreshButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set default values when the screen loads
        cityLabel.setText("Loading...");
        temperatureLabel.setText("--°C");

        // Set action for the refresh button
        refreshButton.setOnAction(event -> {
            updateWeather();
        });

        // Also load weather initially
        updateWeather();
    }

    private void updateWeather() {
        // Normally you would fetch real data here
        // For now, just dummy example
        
        String fakeCity = "New York";
        String fakeTemp = "23°C";

        cityLabel.setText(fakeCity);
        temperatureLabel.setText(fakeTemp);
    }
    
    @FXML
    private void goHome() throws IOException{
        App.setRoot("home");
    }
}