/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.students.sri_lanka_marine_life_explorer;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;

//import java.net.URL as JavaURL;




/**
 * FXML Controller class
 *
 * @author thareesha
 */


public class WeatherController implements Initializable {

    private static final String API_KEY = "b32fdca139d66e539071ca294fdb6105"; // Replace with your real OpenWeatherMap API key

    private final String[] cities = {
        "Colombo", "Galle", "Trincomalee", "Negombo", "Jaffna", "Hambantota",
        "Kalutara", "Matara", "Batticaloa"
    };

    @FXML private Label cityLabel1, temperatureLabel1;
    @FXML private Label cityLabel2, temperatureLabel2;
    @FXML private Label cityLabel3, temperatureLabel3;
    @FXML private Label cityLabel4, temperatureLabel4;
    @FXML private Label cityLabel5, temperatureLabel5;
    @FXML private Label cityLabel6, temperatureLabel6;
    @FXML private Label cityLabel7, temperatureLabel7;
    @FXML private Label cityLabel8, temperatureLabel8;
    @FXML private Label cityLabel9, temperatureLabel9;

    private Label[] cityLabels;
    private Label[] temperatureLabels;

    
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cityLabels = new Label[] {
            cityLabel1, cityLabel2, cityLabel3, cityLabel4, cityLabel5,
            cityLabel6, cityLabel7, cityLabel8, cityLabel9
        };

        temperatureLabels = new Label[] {
            temperatureLabel1, temperatureLabel2, temperatureLabel3, temperatureLabel4,
            temperatureLabel5, temperatureLabel6, temperatureLabel7, temperatureLabel8,
            temperatureLabel9
        };

        updateWeather();
    }
    
    
    private void updateWeather() {
        

//        cityLabel1.setText("Colombo");
//        temperatureLabel1.setText("32 c");
//        
//        cityLabel2.setText("Gampaha");
//        temperatureLabel2.setText("40 c c");

//        String city1 = cities[6];
//        String json1 = fetchWeatherData(city1);
//        
//       // String temperature = parseTemperature(json1);
//        System.out.println(temperature);
//        cityLabels[0].setText(city1);
//        temperatureLabels[0].setText(temperature);
        
        
        for(int i=0;i<cities.length;++i){
            final int index = i;
            String city = cities[index];
            String json = fetchWeatherData(city);
            if(json !=null){
                String[] weatherInformation = parseWeatherInformation(json);
                Platform.runLater(() -> {
                    cityLabels[index].setText(city);
                    temperatureLabels[index].setText(weatherInformation[0] +  " °C");
                    
                });
            }else{
                Platform.runLater(() -> {
                    cityLabels[index].setText(city);
                    temperatureLabels[index].setText("Error");
                    
                });
            }
        }
        
        
        
    }
    
    private String fetchWeatherData(String city){
        try{
            String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY + "&units=metric";
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
        
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );
            
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = reader.readLine()) !=null){
                sb.append(line);
            }
            System.out.println(sb.toString());
            reader.close();
            return sb.toString();

            
        }catch(Exception e){
            System.out.println("Error fetching for "+ city);
            e.printStackTrace();
            return null;
        }
        
    }
    
    
    
    private String[] parseWeatherInformation(String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        String temperature = String.format("%.1f", jsonObject.getAsJsonObject("main").get("temp").getAsDouble());
        String humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsString();
        String windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsString();
        String seaLevel = jsonObject.getAsJsonObject("main").get("sea_level").getAsString();

        String weatherDescription= jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("description").getAsString();
        return new String[]{temperature, humidity,windSpeed,seaLevel,weatherDescription};
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

   /* @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set default values when the screen loads
        cityLabel.setText("Loading...");
        temperatureLabel.setText("--°C");

        // Set action for the refresh button
        refreshButton.setOnAction(event -> {
            updateWeather();
        });

        // Also load weather initially
        updateWeather();
    }

    private void updateWeather() {
        // Normally you would fetch real data here
        // For now, just dummy example
        
        String fakeCity = "New York";
        String fakeTemp = "23°C";

        cityLabel.setText(fakeCity);
        temperatureLabel.setText(fakeTemp);
    }


    */
    
    @FXML
    private void goHome() throws IOException{
        App.setRoot("home");
    }
}