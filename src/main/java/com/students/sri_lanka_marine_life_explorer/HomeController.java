/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.students.sri_lanka_marine_life_explorer;
import java.io.IOException;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.util.Duration;


/**
 *
 * @author thareesha
 */
public class HomeController {

    private Node myButton;
    
    @FXML
    private void goToWeather() throws IOException{
        App.setRoot("Weather");
    }
    
    @FXML
    private void goToEducation() throws IOException{
        App.setRoot("EducationSection");
    }
    
    @FXML
    private void goToFisheries() throws IOException{
        App.setRoot("BeachCondition");
    }
    
    @FXML
    private void goToMap() throws IOException{
        App.setRoot("News");
    }
    
    @FXML
    private void goToMarine() throws IOException{
        App.setRoot("MarineSpecies");
    }
    
    @FXML
    private void goToRegistration() throws IOException{
        App.setRoot("Fishering");
    }
    
    @FXML
    private void goToLicense() throws IOException{
        App.setRoot("license");
    }
    
    @FXML
    private void goToBoatRegistration() throws IOException{
        App.setRoot("boat");
    }
    
    @FXML
    private void goToLicenseList() throws IOException{
       // App.setRoot("LicenseTable");
        App.setRoot("FishingManagement");
    }
    
    @FXML
    private void goToLicesneManagement() throws IOException{
        App.setRoot("FishingManagement");
    }
    
     @FXML
    private void goToAdminLogin() throws IOException{
        App.setRoot("AdminLogin");
    }
    
    

    
    
    
}
