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
//public class LicenseController implements Initializable {
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

//
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import com.fasterxml.jackson.databind.SerializationFeature;
//
//
//import com.students.sri_lanka_marine_life_explorer.models.License;
//import com.students.sri_lanka_marine_life_explorer.models.Boat;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.List;
//
//
//public class LicenseController {
//    
//    private final ObjectMapper mapper = new ObjectMapper()
//        .registerModule(new JavaTimeModule())
//        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//
//
//    @FXML private ComboBox<Boat> boatCombo;
//    @FXML private ComboBox<String> typeCombo;
//    @FXML private DatePicker issueDatePicker, expiryDatePicker;
//    @FXML private TextField regionField;
//
//    private final ObjectMapper mapper = new ObjectMapper();
//
//    @FXML
//    public void initialize() {
//        
//
//        loadBoats();
//        typeCombo.getItems().addAll("Coastal", "Deep Sea", "Lagoon", "Recreational");
//     //   boatCombo.getItems().addAll("Bay" , "Jon" , "Canoes","Uru","IMUL","IDAY","OFRP","MTRB");
//    }
//
//    private void loadBoats() {
//        try {
//            URL url = new URL("http://localhost:8080/api/boats");
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//
//            List<Boat> boats = mapper.readValue(conn.getInputStream(), new TypeReference<>() {});
//            boatCombo.getItems().addAll(boats);
//
//            conn.disconnect();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    private void applyLicense() {
//        try {
//            Boat selectedBoat = boatCombo.getValue();
//            if (selectedBoat == null) return;
//
//            License license = new License();
//            license.setBoat(selectedBoat);
//            license.setFishingType(typeCombo.getValue());
//            license.setIssueDate(issueDatePicker.getValue());
//            license.setExpiryDate(expiryDatePicker.getValue());
//            license.setRegion(regionField.getText());
//            
//
//            URL url = new URL("http://localhost:8080/api/licenses");
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Content-Type", "application/json");
//            conn.setDoOutput(true);
//
//            String json = mapper.writeValueAsString(license);
//            OutputStream os = conn.getOutputStream();
//            os.write(json.getBytes());
//            os.close();
//
//            if (conn.getResponseCode() == 201 || conn.getResponseCode() == 200) {
//                // Clear form
//                boatCombo.getSelectionModel().clearSelection();
//                typeCombo.getSelectionModel().clearSelection();
//                issueDatePicker.setValue(null);
//                expiryDatePicker.setValue(null);
//                regionField.clear();
//            }
//
//            conn.disconnect();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}




import com.students.sri_lanka_marine_life_explorer.models.License;
import com.students.sri_lanka_marine_life_explorer.models.Boat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class LicenseController {

    // ✅ Correctly configured ObjectMapper for LocalDate
    private final ObjectMapper mapper = new ObjectMapper()
        .registerModule(new JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    @FXML private ComboBox<Boat> boatCombo;
    @FXML private ComboBox<String> typeCombo;
    @FXML private DatePicker issueDatePicker, expiryDatePicker;
    @FXML private TextField regionField;

    @FXML
    public void initialize() {
        loadBoats();
        typeCombo.getItems().addAll("Coastal", "Deep Sea", "Lagoon", "Recreational");
    }

    private void loadBoats() {
        try {
            URL url = new URL("http://13.201.93.127:8080/api/boats");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            List<Boat> boats = mapper.readValue(conn.getInputStream(), new TypeReference<>() {});
            boatCombo.getItems().clear();
            boatCombo.getItems().addAll(boats);

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void applyLicense() {
        try {
            Boat selectedBoat = boatCombo.getValue();
            if (selectedBoat == null || typeCombo.getValue() == null ||
                issueDatePicker.getValue() == null || expiryDatePicker.getValue() == null ||
                regionField.getText().isBlank()) {
                showAlert("Error", "Please fill in all fields.");
                return;
            }

            License license = new License();
            license.setBoat(selectedBoat);
            license.setFisherman(selectedBoat.getOwner());
            license.setFishingType(typeCombo.getValue());
            license.setIssueDate(issueDatePicker.getValue());
            license.setExpiryDate(expiryDatePicker.getValue());
            license.setRegion(regionField.getText());

            URL url = new URL("http://13.201.93.127:8080/api/licenses");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String json = mapper.writeValueAsString(license);
            try (OutputStream os = conn.getOutputStream()) {
                os.write(json.getBytes());
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == 201 || responseCode == 200) {
                showAlert("Success", "License submitted successfully.");
                clearForm();
            } else {
                showAlert("Error", "Failed to submit license. Code: " + responseCode);
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Exception", "An error occurred: " + e.getMessage());
        }
    }

    private void clearForm() {
        boatCombo.getSelectionModel().clearSelection();
        typeCombo.getSelectionModel().clearSelection();
        issueDatePicker.setValue(null);
        expiryDatePicker.setValue(null);
        regionField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    
    @FXML
    private void goMainMenu() throws IOException{
        App.setRoot("home");
    }
}

