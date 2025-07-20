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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.layout.BorderPane;



public class BoatTypeController {

    @FXML
    private PieChart boatTypePieChart;
    
    @FXML
    private BorderPane rootPane;

    @FXML
    public void initialize() {
         rootPane.setStyle("-fx-background-color: linear-gradient(to bottom right, #b2ebf2, #e0f7fa);");

        loadBoatTypeChart();
    }

    private void loadBoatTypeChart() {
        try {
            URL url = new URL("http://localhost:8080/api/licenses");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            List<License> licenses = mapper.readValue(conn.getInputStream(), new TypeReference<>() {});

            // Count boat types
            Map<String, Integer> boatTypeCounts = new HashMap<>();
            for (License license : licenses) {
                if (license.getBoat() != null && license.getBoat().getType() != null) {
                    String type = license.getBoat().getType();
                    boatTypeCounts.put(type, boatTypeCounts.getOrDefault(type, 0) + 1);
                }
            }

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            for (Map.Entry<String, Integer> entry : boatTypeCounts.entrySet()) {
                pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
            }

            boatTypePieChart.setData(pieChartData);
            boatTypePieChart.setLegendVisible(true);
            boatTypePieChart.setLabelsVisible(true);
            boatTypePieChart.setTitle("Boat Type Distribution");

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Failed to load boat type chart: " + e.getMessage());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Data Loading Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    
    @FXML
    private void goToBack() throws IOException{
        App.setRoot("AdminDashboard");
    }
}
