/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */

package EduApp;

import javafx.application.Application;
import javafx.geometry.Pos;  // Import for setting the alignment
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FunFactsView extends Application {

    @Override
    public void start(Stage stage) {
        ListView<String> funFactsList = new ListView<>();

        
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

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> new MainApp().start(stage));

        
        HBox buttonBox = new HBox(backButton);
        buttonBox.setAlignment(Pos.BASELINE_LEFT);  

        VBox root = new VBox(15, funFactsList, buttonBox);
        root.setAlignment(Pos.CENTER);  
        root.setStyle("-fx-padding: 20;");
        
        Scene scene = new Scene(root, 500, 400);

        stage.setTitle("Fun Facts");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

