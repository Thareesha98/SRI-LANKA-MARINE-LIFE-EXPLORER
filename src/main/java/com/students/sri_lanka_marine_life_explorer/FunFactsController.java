/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.students.sri_lanka_marine_life_explorer;




import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class FunFactsController {

    @FXML
    private ListView<String> funFactsList;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        funFactsList.getItems().addAll(
            "* Coral reefs support 25% of all marine life!",
            "🐢 Sea turtles can live up to 100 years!",
            "🌊 The ocean produces over 50% of the world’s oxygen.",
            "🐬 Dolphins use unique whistles to call each other by name.",
            "🌱 Mangroves help protect coastlines from storms.",
            "🐙 An octopus has three hearts and blue blood!",
            "🦈 Sharks have been around longer than dinosaurs.",
            "🐠 Clownfish can change gender — males can become females!",
            "🪼 Jellyfish have no brain, heart, or bones.",
            "🐋 The blue whale is the largest animal to have ever lived."
        );

        backButton.setOnAction(event -> {
            try {
                
                App.setRoot("educationSection"); 
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
