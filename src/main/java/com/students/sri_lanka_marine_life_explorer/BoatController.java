package com.students.sri_lanka_marine_life_explorer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author thareesha
 */
//public class BoatController implements Initializable {
//
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }    
//    
//}





import com.students.sri_lanka_marine_life_explorer.models.Boat;
import com.students.sri_lanka_marine_life_explorer.models.Fisherman;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;




public class BoatController {

    @FXML private ComboBox<Fisherman> fishermanCombo ;
    @FXML private ComboBox  boatTypeCombo;
    @FXML private TextField nameField, typeField, regField,sizeField,engineTypeField;

    private final ObjectMapper mapper = new ObjectMapper();

    @FXML
    public void initialize() {
        boatTypeCombo.getItems().addAll("Uru","FRP" , "Pilar" , "IDAY" , "IMUL","NTRB","NBSB", "Bay" , "Flat-Bottom" , "Canoes" , "Trawlers");
        loadFishermen();
    }

    private void loadFishermen() {
        try {
         //  URL url = new URL("http://localhost:8080/api/fishermen");
         URL url = new URL("http://13.201.93.127:8080/api/fishermen");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            List<Fisherman> fishermen = mapper.readValue(conn.getInputStream(), new TypeReference<>() {});
            fishermanCombo.getItems().addAll(fishermen);

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void registerBoat() {
        try {
            Fisherman selectedFisherman = fishermanCombo.getValue();
            if (selectedFisherman == null) return;

            Boat boat = new Boat();
            boat.setName(nameField.getText());
            boat.setType((String) boatTypeCombo.getValue());
            boat.setRegistrationNumber(regField.getText());
            boat.setOwner(selectedFisherman);
            boat.setSize(Integer.parseInt(sizeField.getText()));
            boat.setEngine_type(engineTypeField.getText());
            

            URL url = new URL("http://13.201.93.127:8080/api/boats");
           // URL url = new URL("http://43.204.114.234:8080/api/boats");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String json = mapper.writeValueAsString(boat);
            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes());
            os.close();

            if (conn.getResponseCode() == 200 || conn.getResponseCode() == 201) {
                showAlert("Success", "Boat registered successfully");
                nameField.clear(); typeField.clear(); regField.clear(); fishermanCombo.getSelectionModel().clearSelection();
            }

            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void goMainMenu() throws IOException{
        App.setRoot("home");
    }

    private void showAlert(String success, String boat_registered_successfully) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
