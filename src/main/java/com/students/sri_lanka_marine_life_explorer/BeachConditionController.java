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
//public class BeachConditionController implements Initializable {
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



import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

import org.json.JSONArray;
import org.json.JSONObject;

public class BeachConditionController {

    @FXML
    private ComboBox<String> beachSelector;

    @FXML
    private Button fetchDataButton;

    @FXML
    private LineChart<Number, Number> tideChart;
    
    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private TextArea tideInfoArea;

    private final String API_KEY = "ffec43e4-9027-4225-a668-4333f9634121";

    @FXML
    public void initialize() {
        beachSelector.getItems().addAll("Hikkaduwa", "Mirissa", "Arugam Bay","Colombo", "Galle", "Hikkaduwa", "Mirissa", "Bentota", "Beruwala",
    "Negombo", "Trincomalee", "Batticaloa", "Jaffna");
        fetchDataButton.setOnAction(e -> fetchTideData());
    }

    private void fetchTideData() {
        String selectedBeach = beachSelector.getValue();
        if (selectedBeach == null) {
            tideInfoArea.setText("Please select a beach.");
            return;
        }

        double lat = 0, lon = 0;
        switch (selectedBeach) {
            case "Hikkaduwa": lat = 6.1406; lon = 80.0982; break;
            case "Mirissa": lat = 5.9476; lon = 80.4572; break;
            case "Arugam Bay": lat = 6.8390; lon = 81.8313; break;
            case "Colombo": lat = 6.9271; lon = 79.8612; break;
            case "Galle": lat = 6.0535; lon = 80.2210; break;
            case "Bentota": lat = 6.4227; lon = 80.0059; break;
            case "Beruwala": lat = 6.4788; lon = 79.9827; break;
            case "Negombo": lat = 7.2083; lon = 79.8358; break;
            case "Trincomalee": lat = 8.5874; lon = 81.2152; break;
            case "Batticaloa": lat = 7.7102; lon = 81.6924; break;
            case "Jaffna": lat = 9.6615; lon = 80.0255; break;
        }

        double finalLat = lat, finalLon = lon;
        new Thread(() -> {
            try {
                String apiUrl = String.format(
                        "https://www.worldtides.info/api/v3?heights&extremes&lat=%f&lon=%f&key=%s",
                        finalLat, finalLon, API_KEY
                );

                HttpURLConnection conn = (HttpURLConnection) new URL(apiUrl).openConnection();
                conn.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();

                JSONObject json = new JSONObject(content.toString());
                JSONArray heights = json.getJSONArray("heights");
                JSONArray extremes = json.getJSONArray("extremes");

                XYChart.Series<Number, Number> tideSeries = new XYChart.Series<>();
                tideSeries.setName("Tide Height");

                StringBuilder infoBuilder = new StringBuilder("Tide Extremes:\n");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm").withZone(ZoneId.of("Asia/Colombo"));

                for (int i = 0; i < heights.length(); i++) {
                    JSONObject h = heights.getJSONObject(i);
                    long timestamp = h.getLong("dt");
                    double height = h.getDouble("height");
                   // String timeLabel = Instant.ofEpochSecond(timestamp).atZone(ZoneId.of("Asia/Colombo")).format(DateTimeFormatter.ofPattern("HH:mm"));
                    //tideSeries.getData().add(new XYChart.Data<>(timeLabel, height));
                    
                   // int hour = Instant.ofEpochSecond(timestamp).atZone(ZoneId.of("Asia/Colombo")).getHour();
                    //tideSeries.getData().add(new XYChart.Data<>(hour, height));
                    xAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(xAxis) {
                    @Override
                    public String toString(Number object) {
                        double decimalTime = object.doubleValue();
                        int hour = (int) decimalTime;
                        int minute = (int) Math.round((decimalTime - hour) * 60);
                        return String.format("%02d:%02d", hour, minute);
                    }
                });

                    
                    
                    ZonedDateTime zdt = Instant.ofEpochSecond(timestamp).atZone(ZoneId.of("Asia/Colombo"));
                    int hour = zdt.getHour();
                    int minute = zdt.getMinute();
                    double timeAsDecimal = hour + minute / 60.0;

                    tideSeries.getData().add(new XYChart.Data<>(timeAsDecimal, height));

                    
                    
                }

                for (int i = 0; i < extremes.length(); i++) {
                    JSONObject e = extremes.getJSONObject(i);
                    String type = e.getString("type");
                    long timestamp = e.getLong("dt");
                    double height = e.getDouble("height");
                    infoBuilder.append(String.format("%s: %.2fm at %s\n", type, height, formatter.format(Instant.ofEpochSecond(timestamp))));
                }

                Platform.runLater(() -> {
                    tideChart.getData().clear();
                    tideChart.getData().add(tideSeries);
                    tideInfoArea.setText(infoBuilder.toString());
                });

            } catch (Exception e) {
                Platform.runLater(() -> tideInfoArea.setText("Error fetching data: " + e.getMessage()));
            }
        }).start();
    }
    
    
    @FXML
    private void goToMainMenu() throws IOException{
        App.setRoot("home");
    }
}
