package com.students.sri_lanka_marine_life_explorer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */



import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.students.sri_lanka_marine_life_explorer.models.Boat;
import com.students.sri_lanka_marine_life_explorer.models.License;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LicenseApprovalController implements Initializable {

    @FXML private TableView<License> licenseTable;
    @FXML private TableColumn<License, String> boatColumn;
    @FXML private TableColumn<License, String> typeColumn;
    @FXML private TableColumn<License, LocalDate> issueDateColumn;
    @FXML private TableColumn<License, LocalDate> expiryDateColumn;
    @FXML private TableColumn<License, String> regionColumn;
    @FXML private TableColumn<License, String> statusColumn;
    @FXML private Button approveButton;
    @FXML private Button rejectButton;

    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        boatColumn.setCellValueFactory(data -> {
            Boat boat = data.getValue().getBoat();
            String display = (boat != null) ? boat.getName() + " (" + boat.getRegistrationNumber() + ")" : "Unknown";
            return new javafx.beans.property.SimpleStringProperty(display);
        });

        typeColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("fishingType"));
        issueDateColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("issueDate"));
        expiryDateColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("expiryDate"));
        regionColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("region"));
        statusColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("status"));
        approveButton.setOnAction(e -> approveSelectedLicense());
        rejectButton.setOnAction(e -> rejectSelectedLicense());

        loadPendingLicenses();
    }

    private void loadPendingLicenses() {
        try {
            URL url = new URL("http://13.201.93.127:8080/api/licenses/pending");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            List<License> licenses = mapper.readValue(conn.getInputStream(), new TypeReference<>() {});
            ObservableList<License> licenseList = FXCollections.observableArrayList(licenses);
            licenseTable.setItems(licenseList);

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Failed to load licenses: " + e.getMessage());
        }
    }

    private void approveSelectedLicense() {
        License selected = licenseTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Please select a license to approve.");
            return;
        }

        try {
            URL url = new URL("http://13.201.93.127:8080/api/licenses/approve/" + selected.getId());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.getResponseCode(); // Trigger request
            conn.disconnect();

            showAlert("License approved successfully.");
            loadPendingLicenses(); // Refresh table
        } catch (Exception e) {
            showAlert("Approval failed: " + e.getMessage());
        }
    }

    private void rejectSelectedLicense() {
        License selected = licenseTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Please select a license to reject.");
            return;
        }

        try {
            URL url = new URL("http://13.201.93.127:8080/api/licenses/reject/" + selected.getId());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.getResponseCode(); // Trigger request
            conn.disconnect();

            showAlert("License rejected successfully.");
            loadPendingLicenses(); // Refresh table
        } catch (Exception e) {
            showAlert("Rejection failed: " + e.getMessage());
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
    
     @FXML
private void logOut() throws IOException {
    System.out.println("Logout clicked.......");
    App.setRoot("AdminLogin");
}

    
     @FXML
    private void goToMainMenu() throws IOException{
        App.setRoot("home");
    }
    
    @FXML
    private void goToPasswordChange() throws IOException{
        App.setRoot("ChangePassword");
    }
    

}
