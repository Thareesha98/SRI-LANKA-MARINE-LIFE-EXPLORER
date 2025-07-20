/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.students.sri_lanka_marine_life_explorer;

/**
 *
 * @author thareesha
 * 
 */


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.net.URL;

public class FishingManagementController {

    

    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded.
     */
    
    
    
    @FXML
    private void goToFisheries() throws IOException {
        App.setRoot("Fishering");
    }
    
    @FXML
    private void goToBoat() throws IOException {
        App.setRoot("boat");
    }
    
    @FXML
    private void goToLicense() throws IOException {
        App.setRoot("license");
    }
    
    @FXML
    private void goToLicenseList() throws IOException {
        App.setRoot("LicenseTable");
    }
    
        @FXML
    private void goToMainMenu() throws IOException {
        App.setRoot("home");
    }
    
    @FXML
    public void initialize() {
        // Optional: Any initialization logic for your dashboard
        // For example, you could dynamically set button text based on some data
        System.out.println("HomeController initialized.");
    }

    
}
