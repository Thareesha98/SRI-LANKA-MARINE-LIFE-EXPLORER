package com.students.sri_lanka_marine_life_explorer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import com.fasterxml.jackson.core.type.TypeReference;
import com.students.sri_lanka_marine_life_explorer.models.Boat;
import com.students.sri_lanka_marine_life_explorer.models.License;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javafx.stage.FileChooser;



/**
 * FXML Controller class
 *
 * @author thareesha
 */



public class LicenseTableController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private TableView<License> licenseTable;
    @FXML private TableColumn<License, String> boatColumn;
    @FXML private TableColumn<License, String> typeColumn;
    @FXML private TableColumn<License, LocalDate> issueDateColumn;
    @FXML private TableColumn<License, LocalDate> expiryDateColumn;
    @FXML private TableColumn<License, String> regionColumn;
    @FXML private TableColumn<License, String> statusColumn;


    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

        loadLicenses();
    }    
    
    
    private void loadLicenses() {
        try {
            URL url = new URL("http://localhost:8080/api/licenses");
           //URL url = new URL("http://13.201.93.127:8080/api/licenses");
          // URL url = new URL("http://13.201.93.127:8080/api/boats");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());


            List<License> licenses = mapper.readValue(conn.getInputStream(), new TypeReference<>() {});
            ObservableList<License> licenseList = FXCollections.observableArrayList(licenses);
            licenseTable.setItems(licenseList);

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Failed to load licenses: " + e.getMessage());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    @FXML
    private void goToMainMenu() throws IOException{
        App.setRoot("FishingManagement");
    }
    
    @FXML
private void downloadSelectedLicense() {
    License selected = licenseTable.getSelectionModel().getSelectedItem();
    if (selected == null) {
        showAlert("Please select a license to download.");
        return;
    }

    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Save License PDF");
    fileChooser.setInitialFileName("license_" + selected.getId() + ".pdf");
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
    File file = fileChooser.showSaveDialog(licenseTable.getScene().getWindow());

    if (file != null) {
        String downloadUrl = "http://localhost:8080/api/licenses/download/" + selected.getId();
        try (InputStream in = new URL(downloadUrl).openStream();
             OutputStream out = new FileOutputStream(file)) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("License PDF downloaded successfully.");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Failed to download PDF: " + e.getMessage());
        }
    }
}

    
}
