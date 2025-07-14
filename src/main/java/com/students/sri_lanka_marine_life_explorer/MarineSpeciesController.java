/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.students.sri_lanka_marine_life_explorer;

//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//
///**
// * FXML Controller class
// *
// * @author thareesha
// */
//public class MarineSpeciesController implements Initializable {
//
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }    
//    
//    @FXML
//    private void goHome() throws IOException{
//        App.setRoot("home");
//    }
//    
//}


//package com.students.sri_lanka_marine_life_explorer;
//
//import javafx.animation.FadeTransition;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.ListView;
//import javafx.scene.control.Label;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.text.Text;
//import javafx.scene.layout.VBox;
//import javafx.util.Duration;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.ResourceBundle;
//









































import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
//
public class MarineSpeciesController implements Initializable {
//
//    private final String[] speciesNames = {
//            "Clownfish", "Sea Turtle", "Blue Whale", "Dolphin",
//            "Seahorse", "Jellyfish", "Octopus", "Starfish",
//            "Coral", "Manta Ray", "Lionfish", "Crab"
//    };
//
//    private final String[] descriptions = {
//            "Clownfish are small, brightly colored fish known for their symbiotic relationship with sea "
//            + "anemones. Sea turtles are ancient reptiles that have lived in the oceans for over 100 million"
//            + " years Blue whales are the largest animals to have ever existed, found in all major ocea Dolphins a"
//            + "re intelligent marine mammals known for their playful behavior and communication skills Seahorses are "
//            + "tiny fish with horse-like heads and a unique mode of swimming uprigh Jellyfish are gelatinous creatures "
//            + "that drift through the oceans, some with a painful sting Octopuses are highly intelligent invertebrates capable "
//            + "of camouflage and problem-solving Starfish, or sea stars, are echinoderms with remarkable regeneration abilities.",
//            "Corals are marine invertebrates that build massive reef structures supporting ocean life.",
//            "Manta rays are large, graceful rays often seen gliding through tropical waters.",
//            "Lionfish are venomous predators with distinctive striped bodies and fan-like fins.",
//            "Crabs are crustaceans with a hard exoskeleton and a sideways walk."
//    };
//
//    @FXML private ImageView image1;
//    @FXML private ImageView image2;
//    @FXML private ImageView image3;
//    @FXML private ImageView image4;
//    @FXML private ImageView image5;
//    @FXML private ImageView image6;
//    @FXML private ImageView image7;
//    @FXML private ImageView image8;
//    @FXML private ImageView image9;
//    @FXML private ImageView image10;
//    @FXML private ImageView image11;
//    @FXML private ImageView image12;
//
//    @FXML private Label label1;
//    @FXML private Label label2;
//    @FXML private Label label3;
//    @FXML private Label label4;
//    @FXML private Label label5;
//    @FXML private Label label6;
//    @FXML private Label label7;
//    @FXML private Label label8;
//    @FXML private Label label9;
//    @FXML private Label label10;
//    @FXML private Label label11;
//    @FXML private Label label12;
//    
//    @FXML private Pane infoPane;
//    @FXML private GridPane grid;
//
//    private ImageView[] imageViews;
//    private Label[] labels;
//
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        imageViews = new ImageView[] {
//                image1, image2, image3, image4, image5, image6,
//                image7, image8, image9, image10, image11, image12
//        };
//
//        labels = new Label[] {
//                label1, label2, label3, label4, label5, label6,
//                label7, label8, label9, label10, label11, label12
//        };
//
//        for (int i = 0; i < 1; i++) {
//            int index = i;
//            labels[index].setText(speciesNames[index]);
//
//            // Use dummy placeholder image for each
//            imageViews[i].setImage(new Image("https://via.placeholder.com/100"));
//
//            // Hover animation
//          //  addHoverAnimation(imageViews[i]);
//          
//            // Click event to show species info
//         //   imageViews[i].setOnMouseClicked(e -> showSpeciesInfo(speciesNames[index], descriptions[index]));
//        }
//    }
//    @FXML private TextFlow fullLabel;
//    @FXML private ImageView fullImage;
//    
//
//    @FXML
//    private void showSpecies(ActionEvent event){//String name, String description) {
//        System.out.println("clickedd");
//        animateTransition(grid,infoPane);
//       // fullLabel.sett(descriptions[0]);
//        fullLabel.getChildren().add(new Text(descriptions[0]));
//
//        fullImage.setImage(new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQv2X53B7GZW52F1qjLvPQVTct3mjg0GxtRWA&s"));
//        
//    }
//    @FXML 
//    void hideSpcies(ActionEvent event){
//        animateTransition(infoPane,grid);
//    }
//    
//    
// 
//    
//    private void animateTransition(Node from, Node to) {
//        FadeTransition fadeOut = new FadeTransition(Duration.millis(300), from);
//        fadeOut.setFromValue(1.0);
//        fadeOut.setToValue(0.0);
//
//        FadeTransition fadeIn = new FadeTransition(Duration.millis(300), to);
//        fadeIn.setFromValue(0.0);
//        fadeIn.setToValue(1.0);
//
//        fadeOut.setOnFinished(event -> {
//            from.setVisible(false);
//            to.setVisible(true);
//            fadeIn.play();
//        });
//
//        to.setVisible(true);
//        fadeOut.play();
//    }
//    
//    
//    
//    
//    
//    
//    
//    
//    
//
//    
//
//    @FXML
//    private void goHome() throws IOException {
//        App.setRoot("home");
//    }
    
    
    
    @FXML private TextFlow fullLabel;
@FXML private ImageView fullImage;
@FXML private GridPane grid , grid1;
@FXML private Pane infoPane;

// These should be connected to your FXML
@FXML private ImageView image1, image2, image3, image4, image5, image6,
                       image7, image8, image9, image10, image11, image12;

@FXML private Label label1, label2, label3, label4, label5, label6,
                   label7, label8, label9, label10, label11, label12;

@FXML private Button button1, button2, button3, button4, button5, button6,
                    button7, button8, button9, button10, button11, button12;

private ImageView[] imageViews;
private Label[] labels;
private Button[] buttons;

private String[] speciesNames = {
    "Blue Whale", "Clownfish", "Manta Ray", "Dolphin",
    "Sea Turtle", "Coral Reef", "Starfish", "Octopus",
    "Hammerhead Shark", "Seahorse", "Moray Eel", "Anglerfish"
};

private String[] descriptions = {
    "Blue whales are the largest animals ever known to have lived on Earth. They can reach lengths of up to 100 feet and weigh as much as 200 tons. Despite their massive size, they feed almost exclusively on tiny krill. Blue whales communicate using deep, low-frequency sounds that can travel long distances underwater. These giants are found in oceans around the globe, often migrating great distances between feeding and breeding grounds. Their populations were decimated by commercial whaling in the 20th century, but conservation efforts have helped protect them. Blue whales are a symbol of ocean conservation and resilience.",
    
    "Clownfish are small, brightly colored fish that live among the tentacles of sea anemones in warm, shallow waters of the Indo-Pacific region. Their vibrant orange bodies with white stripes make them easily recognizable. They have a unique symbiotic relationship with sea anemones—clownfish are immune to the anemone's sting and gain protection, while the anemone benefits from food scraps. Clownfish are sequential hermaphrodites, meaning they can change sex during their lives. They are highly territorial and live in social groups led by a dominant female. The popularity of clownfish increased after the animated film 'Finding Nemo', raising awareness about marine life.",
    
    "Manta rays are among the largest rays in the ocean, with wingspans reaching up to 29 feet. They are filter feeders, gracefully swimming through the water while consuming plankton. Manta rays have flattened bodies with triangular pectoral fins that resemble wings, allowing them to glide effortlessly. They are highly intelligent and display complex behaviors, such as coordinated group feeding. Manta rays are found in tropical and subtropical oceans worldwide and often visit cleaning stations where small fish remove parasites. They are vulnerable to overfishing and habitat loss, prompting global conservation efforts. Their majestic presence symbolizes the beauty of marine biodiversity.",
    
    "Dolphins are highly intelligent marine mammals known for their social nature, acrobatic behavior, and complex communication. They belong to the cetacean family, which includes whales and porpoises. Dolphins use echolocation to navigate and hunt, emitting sound waves and interpreting the echoes that bounce back. They live in pods, often cooperating during hunting or protecting injured individuals. Some species, like the bottlenose dolphin, are commonly found near coastlines, while others inhabit deeper ocean waters. Dolphins have been studied for their cognitive abilities and emotional intelligence. Their friendly appearance and playful antics make them beloved by people around the world.",
    
    "Sea turtles are ancient reptiles that have roamed the oceans for over 100 million years. There are seven species, including the green, leatherback, and hawksbill turtles. They are known for their long migrations between feeding grounds and nesting beaches. Female sea turtles return to the beaches where they were born to lay eggs. These creatures are adapted for life at sea, with streamlined shells and powerful flippers. Sea turtles face many threats, including plastic pollution, habitat destruction, and illegal trade. Conservation programs focus on protecting nesting sites and reducing bycatch. Sea turtles are iconic ambassadors for ocean conservation.",
    
    "Coral reefs are vibrant underwater ecosystems built by colonies of tiny coral polyps. These reefs provide habitat for over a quarter of all marine species despite covering less than 1% of the ocean floor. Coral reefs thrive in warm, shallow, and clear waters. They are made of calcium carbonate skeletons secreted by corals, forming structures over thousands of years. Reefs offer food, shelter, and breeding grounds for countless marine organisms. However, they are highly sensitive to climate change, ocean acidification, and pollution. Coral bleaching, caused by rising sea temperatures, threatens their survival. Efforts are underway worldwide to protect and restore reefs.",
    
    "Starfish, or sea stars, are fascinating marine invertebrates belonging to the class Asteroidea. They have a central disc and typically five arms, although some species have more. Starfish use a water vascular system to move and operate hundreds of tiny tube feet located on their underside. They can regenerate lost arms and even entire bodies in some cases. Starfish are predators, often feeding on bivalves like clams and mussels by prying open their shells. They lack a brain and blood but have a nerve ring and seawater-based circulatory system. Found in oceans worldwide, they play a crucial role in marine ecosystems.",
    
    "Octopuses are remarkable marine animals known for their intelligence, camouflage abilities, and unique body structure. They have eight flexible arms lined with suction cups, which they use to explore, manipulate objects, and capture prey. Octopuses can change color and texture to blend into their surroundings using specialized skin cells called chromatophores. They have a soft, boneless body that allows them to squeeze through tight spaces. Octopuses are solitary and often hide in dens or crevices. They exhibit problem-solving skills, use tools, and display complex behaviors. These cephalopods are found in oceans worldwide, from shallow reefs to deep-sea environments.",
    
    "Hammerhead sharks are easily recognized by their distinctive, flattened, T-shaped heads called cephalofoils. This unique head structure enhances their vision and helps detect prey hidden under the sand. Hammerheads are highly mobile predators found in warm coastal waters and open oceans. They hunt in groups during the day and often disperse at night to feed. Their diet includes fish, squid, and stingrays. There are several species of hammerheads, including the great hammerhead and scalloped hammerhead. Despite their fearsome appearance, they are not typically dangerous to humans. Hammerhead sharks are threatened by overfishing and are protected in many marine sanctuaries.",
    
    "Seahorses are small, delicate marine fish with horse-like heads and curled tails. They belong to the genus Hippocampus and are known for their unique body structure and upright swimming posture. Unlike most fish, seahorses do not have scales but instead have bony plates covering their bodies. They are poor swimmers and rely on camouflage to avoid predators. One of the most fascinating aspects of seahorses is their reproductive process: males carry the fertilized eggs in a specialized brood pouch and give birth to fully formed young. Found in shallow seagrass beds and coral reefs, they are often threatened by habitat loss.",
    
    "Moray eels are elongated, snake-like fish that inhabit reefs and rocky crevices in tropical and subtropical oceans. They are known for their sharp teeth, powerful jaws, and fearsome appearance. Moray eels are mostly nocturnal, emerging at night to hunt fish, octopuses, and crustaceans. They have poor eyesight but rely on a keen sense of smell to detect prey. Some species have a second set of jaws called pharyngeal jaws that help capture and swallow prey. While they may appear aggressive, moray eels are generally shy and will only bite if threatened. They play an important role in reef ecosystems.",
    
    "Anglerfish are deep-sea dwellers known for their bizarre appearance and unique hunting strategy. The most iconic feature is the bioluminescent lure that extends from the head of females, used to attract prey in the darkness of the deep ocean. Anglerfish have large mouths with sharp teeth and can swallow prey nearly as large as themselves. They exhibit extreme sexual dimorphism: males are much smaller and often fuse to the female's body for reproduction. Adapted to life under extreme pressure and darkness, anglerfish symbolize the mysteries of the deep sea. Their eerie look has made them popular in documentaries and fiction."
};

@Override
public void initialize(URL url, ResourceBundle rb) {
    imageViews = new ImageView[] {
        image1, image2, image3, image4, image5, image6,
        image7, image8, image9, image10, image11, image12
    };

    labels = new Label[] {
        label1, label2, label3, label4, label5, label6,
        label7, label8, label9, label10, label11, label12
    };

    buttons = new Button[] {
        button1, button2, button3, button4, button5, button6,
        button7, button8, button9, button10, button11, button12
    };

    for (int i = 0; i < speciesNames.length; i++) {
        final int index = i;

        labels[index].setText(speciesNames[index]);
        //imageViews[index].setImage(new Image("https://via.placeholder.com/180x120.png?text=" + speciesNames[index].replace(" ", "+")));
        
        String imagePath = "/Images/MarineSpecies/" + speciesNames[index] + ".jpeg";
        imageViews[index].setImage(new Image(getClass().getResourceAsStream(imagePath)));
        
        // Bind button click with argument
        buttons[index].setOnAction(e -> showSpecies(speciesNames[index], descriptions[index]));
    }
}

@FXML
private void showSpecies(String name, String description) {
    System.out.println("Showing: " + name);
    animateTransition(grid, infoPane);

    fullLabel.getChildren().clear();
    fullLabel.getChildren().add(new Text(description));

    // For now, just use a generic image or you can map name -> image
  //  fullImage.setImage(new Image("https://via.placeholder.com/800x250.png?text=" + name.replace(" ", "+")));
  String imagePath = "/Images/MarineSpecies/" + name + ".jpeg";
    fullImage.setImage(new Image(getClass().getResourceAsStream(imagePath)));
    System.out.println("Image Loaded");
}

@FXML 
void hideSpcies(ActionEvent event){
    animateTransition(infoPane, grid);
}

@FXML
private void goNext(){
    System.out.println("Next clicked......");
    animateTransition(grid,grid1);
}

@FXML
private void goPrevious(){
    System.out.println("Next clicked......");
    animateTransition(grid1,grid);
}

private void animateTransition(Node from, Node to) {
    // Prepare the 'to' node
    to.setOpacity(0);
    to.setVisible(true);
    to.setDisable(false); // make sure buttons work
    to.toFront();         // bring to top if overlapping

    // Fade out current
    FadeTransition fadeOut = new FadeTransition(Duration.millis(400), from);
    fadeOut.setFromValue(1.0);
    fadeOut.setToValue(0.0);

    // Fade in next
    FadeTransition fadeIn = new FadeTransition(Duration.millis(400), to);
    fadeIn.setFromValue(0.0);
    fadeIn.setToValue(1.0);

    fadeOut.setOnFinished(event -> {
        from.setVisible(false);  // hide the old one
        from.setDisable(true);   // prevent ghost clicks
        fadeIn.play();
    });

    fadeOut.play();
}


@FXML
    private void goHome() throws IOException {
        App.setRoot("home");
    }

}

















