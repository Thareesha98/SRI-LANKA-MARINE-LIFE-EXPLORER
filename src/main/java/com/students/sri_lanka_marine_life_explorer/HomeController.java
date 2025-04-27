/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.students.sri_lanka_marine_life_explorer;
import java.io.IOException;
import javafx.fxml.FXML;


/**
 *
 * @author thareesha
 */
public class HomeController {
    
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
        App.setRoot("Fisheries");
    }
    
    @FXML
    private void goToMap() throws IOException{
        App.setRoot("Map");
    }
    
    @FXML
    private void goToMarine() throws IOException{
        App.setRoot("MarineSpecies");
    }
    
}
