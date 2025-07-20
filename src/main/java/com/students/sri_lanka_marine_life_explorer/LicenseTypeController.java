/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.students.sri_lanka_marine_life_explorer;

/**
 *
 * @author thareesha
 */


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.students.sri_lanka_marine_life_explorer.models.License;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.layout.BorderPane;

public class LicenseTypeController {

    @FXML
    private BarChart<String, Number> licenseTypeBarChart;

    @FXML
    private BorderPane rootPane;
    
    @FXML
    public void initialize() {
        rootPane.setStyle("-fx-background-color: linear-gradient(to bottom right, #b2ebf2, #e0f7fa);");

        loadLicenseTypeChart();
    }

    private void loadLicenseTypeChart() {
        try {
            URL url = new URL("http://localhost:8080/api/licenses");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            List<License> licenses = mapper.readValue(conn.getInputStream(), new TypeReference<>() {});

            // Count license types
            Map<String, Integer> licenseTypeCounts = new HashMap<>();
            for (License license : licenses) {
                if (license.getFishingType() != null) {
                    String type = license.getFishingType();
                    licenseTypeCounts.put(type, licenseTypeCounts.getOrDefault(type, 0) + 1);
                }
            }

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            for (Map.Entry<String, Integer> entry : licenseTypeCounts.entrySet()) {
                series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
            }

            licenseTypeBarChart.getData().setAll(FXCollections.observableArrayList(series));
            licenseTypeBarChart.setTitle("License Type Distribution");

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Failed to load license type chart: " + e.getMessage());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Data Loading Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void goToBack() throws IOException {
        App.setRoot("AdminDasshboard");
    }
}
