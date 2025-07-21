/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.students.sri_lanka_marine_life_explorer;



import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.sql.*;


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
import java.io.File;
import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;


import javafx.scene.layout.GridPane;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


//import java.net.URL as JavaURL;


/**
 * FXML Controller class
 *
 * @author thareesha
 */


public class WeatherController implements Initializable {
    
    @FXML
    private GridPane grid1;
    
    @FXML
    private GridPane grid2;

    private static final String API_KEY = "b32fdca139d66e539071ca294fdb6105"; // Replace with your real OpenWeatherMap API key

//    private final String[] cities = {
//        "Colombo", "Galle", "Trincomalee", "Negombo", "Jaffna", "Hambantota",
//        "Kalutara", "Matara", "Batticaloa"
//    };
//    
    
    private final String[] cities = {
    "Colombo",        // West coast
    "Galle",          // South
    "Trincomalee",    // East
    "Negombo",        // Near airport, west
    "Jaffna",         // North coast
    "Hambantota",     // Southeast
    "Kalutara",       // Southwest
    "Matara",         // South
    "Batticaloa",     // East
    "Beruwala",       // Southwest
    "Puttalam",
    "Ampara",
    "Bentota",        // Southwest
    "Weligama",       // Southern surf town
    "Chilaw",  
    "Vavuniya", "Kilinochchi",
    "Mannar",
// North-west coast
    "Pasikuda"        // Popular east coast beach
};
    

    

    @FXML private Label cityLabel1, temperatureLabel1 ,humidityLabel1, windLabel1,sealevelLabel1,descriptionLabel1 ;
    @FXML private Label cityLabel2, temperatureLabel2 ,humidityLabel2, windLabel2,sealevelLabel2,descriptionLabel2;
    @FXML private Label cityLabel3, temperatureLabel3,humidityLabel3, windLabel3,sealevelLabel3,descriptionLabel3;
    @FXML private Label cityLabel4, temperatureLabel4,humidityLabel4, windLabel4,sealevelLabel4,descriptionLabel4;
    @FXML private Label cityLabel5, temperatureLabel5,humidityLabel5, windLabel5,sealevelLabel5,descriptionLabel5;
    @FXML private Label cityLabel6, temperatureLabel6, humidityLabel6, windLabel6,sealevelLabel6,descriptionLabel6;
    @FXML private Label cityLabel7, temperatureLabel7 ,humidityLabel7, windLabel7,sealevelLabel7,descriptionLabel7 ;
    @FXML private Label cityLabel8, temperatureLabel8, humidityLabel8, windLabel8,sealevelLabel8,descriptionLabel8;
    @FXML private Label cityLabel9, temperatureLabel9, humidityLabel9, windLabel9,sealevelLabel9,descriptionLabel9;
    @FXML private Label cityLabel10, temperatureLabel10 ,humidityLabel10, windLabel10,sealevelLabel10,descriptionLabel10 ;
    @FXML private Label cityLabel11, temperatureLabel11 ,humidityLabel11, windLabel11,sealevelLabel11,descriptionLabel11 ;
    @FXML private Label cityLabel12, temperatureLabel12 ,humidityLabel12, windLabel12,sealevelLabel12,descriptionLabel12 ;
    @FXML private Label cityLabel13, temperatureLabel13 ,humidityLabel13, windLabel13,sealevelLabel13,descriptionLabel13 ;
    @FXML private Label cityLabel14, temperatureLabel14 ,humidityLabel14, windLabel14,sealevelLabel14,descriptionLabel14 ;
    @FXML private Label cityLabel15, temperatureLabel15 ,humidityLabel15, windLabel15,sealevelLabel15,descriptionLabel15 ;
    @FXML private Label cityLabel16, temperatureLabel16 ,humidityLabel16, windLabel16,sealevelLabel16,descriptionLabel16 ;
    @FXML private Label cityLabel17, temperatureLabel17 ,humidityLabel17, windLabel17,sealevelLabel17,descriptionLabel17 ;
    @FXML private Label cityLabel18, temperatureLabel18 ,humidityLabel18, windLabel18,sealevelLabel18,descriptionLabel18 ;


    private Label[] cityLabels;
    private Label[] temperatureLabels;
    private Label[] humidityLabels;
    private Label[] windLabels;
    private Label[] sealevelLabels;
    private Label[] descriptionLabels;
    private Label[] weatherImageLabels;
    
    @FXML
    private ImageView weatherImage1,weatherImage2,weatherImage3,weatherImage4,weatherImage5,weatherImage6,weatherImage7,weatherImage8,weatherImage9,weatherImage10;
    
    @FXML
    private ImageView weatherImage11,weatherImage12,weatherImage13,weatherImage14,weatherImage15,weatherImage16,weatherImage17,weatherImage18;
    
    @FXML
    private ImageView[] weatherImages;
    
    @FXML
    private void goToNextPage() {
        animateTransition(grid1, grid2);
    }

    @FXML
    private void goToPreviousPage() {
        animateTransition(grid2, grid1);
    }
    
    private void animateTransition(Node from, Node to) {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(300), from);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(300), to);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        fadeOut.setOnFinished(event -> {
            from.setVisible(false);
            to.setVisible(true);
            fadeIn.play();
        });

        to.setVisible(true);
        fadeOut.play();
    }

    @FXML
    private AnchorPane weatherHomeAnchor;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        String imageUrl = getClass().getResource("/Images/myHome.png").toExternalForm();
        weatherHomeAnchor.setStyle(
            "-fx-background-image: url('" + imageUrl + "');" +
            "-fx-background-size: cover;" +
            "-fx-background-repeat: no-repeat;" +
            "-fx-background-position: center center;"
        );
        
        
        cityLabels = new Label[] {
            cityLabel1, cityLabel2, cityLabel3, cityLabel4, cityLabel5,
            cityLabel6, cityLabel7, cityLabel8, cityLabel9,cityLabel10,cityLabel11, cityLabel12, cityLabel13, cityLabel14, cityLabel15,
            cityLabel16, cityLabel17, cityLabel18
        };

        temperatureLabels = new Label[] {
            temperatureLabel1, temperatureLabel2, temperatureLabel3, temperatureLabel4,
            temperatureLabel5, temperatureLabel6, temperatureLabel7, temperatureLabel8,
            temperatureLabel9,temperatureLabel10,temperatureLabel11, temperatureLabel12, temperatureLabel13, temperatureLabel14,
            temperatureLabel15, temperatureLabel16, temperatureLabel17, temperatureLabel18
        };
        
        humidityLabels = new Label[] {
            humidityLabel1,humidityLabel2,humidityLabel3,humidityLabel4,humidityLabel5,humidityLabel6,humidityLabel7,humidityLabel8,humidityLabel9,humidityLabel10, 
            humidityLabel11,humidityLabel12,humidityLabel13,humidityLabel14,humidityLabel15,humidityLabel16,humidityLabel17,humidityLabel18

        };
        
        windLabels = new Label[]{
            windLabel1,windLabel2,windLabel3,windLabel4,windLabel5 , windLabel6,windLabel7,windLabel8,windLabel9,windLabel10,
            windLabel11,windLabel12,windLabel13,windLabel14,windLabel15 , windLabel16,windLabel17,windLabel18
        };
        
        sealevelLabels = new Label[]{
            sealevelLabel1,sealevelLabel2,sealevelLabel3,sealevelLabel4,sealevelLabel5,sealevelLabel6,sealevelLabel7,sealevelLabel8,sealevelLabel9,sealevelLabel10,
            sealevelLabel11,sealevelLabel12,sealevelLabel13,sealevelLabel14,sealevelLabel15,sealevelLabel16,sealevelLabel17,sealevelLabel18
        };
        
        descriptionLabels = new Label[]{
            descriptionLabel1,descriptionLabel2,descriptionLabel3,descriptionLabel4,descriptionLabel5,descriptionLabel6,descriptionLabel7,descriptionLabel8,descriptionLabel9,descriptionLabel10,
            descriptionLabel11,descriptionLabel12,descriptionLabel13,descriptionLabel14,descriptionLabel15,descriptionLabel16,descriptionLabel17,descriptionLabel18

        };
        
       
        
        weatherImages = new ImageView[] {
            weatherImage1,
            weatherImage2,
            weatherImage3,
            weatherImage4,
            weatherImage5,
            weatherImage6,
            weatherImage7,
            weatherImage8,
            weatherImage9,
            weatherImage10,
             weatherImage11,
            weatherImage12,
            weatherImage13,
            weatherImage14,
            weatherImage15,
            weatherImage16,
            weatherImage17,
            weatherImage18
        };

        
        try {
            // updateWeather();
            loadWeatherData();
        } catch (SQLException ex) {
            Logger.getLogger(WeatherController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    
    
    
    
    private void updateWeather() {
        System.out.println("update weather called");
        for(int i=0;i<18;++i){
            final int index = i;
            String city = cities[index];
            String json = fetchWeatherData(city);
            if(json !=null){
                String[] weatherInformation = parseWeatherInformation(json);
                Platform.runLater(() -> {
                    cityLabels[index].setText(city);
                    temperatureLabels[index].setText(weatherInformation[0] +  "°C");
                    humidityLabels[index].setText("Humidity: "+weatherInformation[1]);
                    windLabels[index].setText("Wind: "+weatherInformation[2]);
                 //   sealevelLabels[index].setText("SeaLevel: " + weatherInformation[3]);
                    descriptionLabels[index].setText(weatherInformation[4]);
                    
                    Image weatherIconImage = fetchWeatherIcon(weatherInformation[4]);
                    
                 //  Image image1 = new Image(getClass().getResource("/Images/sea.png").toExternalForm());
                   // Image image1 = new Image(new File("/home/thareesha/NetBeansProjects/Sri_Lanka_Marine_Life_Explorer/src/main/java/com/students/sri_lanka_marine_life_explorer/sun.jpg").toURI().toString());

                  //  Image image2 = new Image("https://lh3.googleusercontent.com/d/1R4ncFIIHI8-agsmr1FRZVNJkJKuNMIJI");
                   

                   weatherImages[index].setImage(weatherIconImage);
//                  URL imageUrl = getClass().getResource("/Images/sea.png");
//                if (imageUrl == null) {
//                    System.out.println("Image not found!");
//                } else {
//                    Image image1 = new Image(imageUrl.toExternalForm());
//                    weatherImages[index].setImage(image1);
//                }

                    
                    
                });
            }else{
                Platform.runLater(() -> {
                    cityLabels[index].setText(city);
                    temperatureLabels[index].setText("Error");
                             
                });
            }
        }
        Platform.runLater(() -> {
            try {
                saveWeatherData();
            } catch (SQLException ex) {
                Logger.getLogger(WeatherController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
           
    }
    
//    private boolean checkEmptyTable(Connection conn,String tableName) throws SQLException{
//        DatabaseMetaData dbMeta = conn.getMetaData();
//        ResultSet tables = dbMeta.getTables(null, null, tableName, new String[] {"TABLE"});
//
//        boolean exists = tables.next(); // true if there's at least one result
//        tables.close();
//        return exists;
//    }
    
//    private boolean checkEmptyTable(Connection conn, String tableName) throws SQLException {
//        String query = "SELECT 1 FROM " + tableName + " LIMIT 1";
//        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
//            return !rs.next(); // returns true if there's at least one row
//        }
//    }
    
    private boolean doesTableExist(Connection conn, String tableName) throws SQLException {
    String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = ? AND table_name = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, conn.getCatalog());  // current database name
        stmt.setString(2, tableName);          // exact table name (case-sensitive)
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
    }
    return false;
}





    
    
private void loadWeatherData() throws SQLException {
    Connection conn = DatabaseConnector.getConnection();
    //System.out.println(checkEmptyTable(conn,"weathertable"));
    boolean x = doesTableExist(conn,"weathertable");
    System.out.println("Table Exist ? " + x);
   if(!doesTableExist(conn,"weathertable")){
       updateWeather();
    }else{
 
        String selectSQL = "SELECT * FROM weathertable ORDER BY id"; // Ensure consistent order
        System.out.println("Startd loading");
        try (PreparedStatement stmt = conn.prepareStatement(selectSQL);
             ResultSet rs = stmt.executeQuery()) {


            int i = 0;
            while (rs.next() && i < 18) {
                final int index = i; // for lambda capture
                final String city = rs.getString("cityname");
                final String temp = rs.getString("temp");
                final String hum = rs.getString("humidity");
                final String wind = rs.getString("windspeed");
                final String desc = rs.getString("weatherdescription");

                 Image weatherIconImage = fetchWeatherIcon(desc);
                
                Platform.runLater(() -> {
                    cityLabels[index].setText(city);
                    temperatureLabels[index].setText(temp);
                    humidityLabels[index].setText(hum);
                    windLabels[index].setText(wind);
                    descriptionLabels[index].setText(desc);
                   

                    weatherImages[index].setImage(weatherIconImage);
                });

                i++;
            }   

            System.out.println("finished loading");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
   }
}
    
    private void saveWeatherData() throws SQLException{
        System.out.println("Start saving to sql");
        Connection conn = DatabaseConnector.getConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS weathertable ( id char(2) PRIMARY KEY , cityname varchar(20),temp varchar(20),humidity varchar(20),windspeed varchar(20),sealevel varchar(20),weatherdescription varchar(30))";
        String insertTable = "INSERT INTO weathertable values(?,?,?,?,?,?,?)";
        String updateTable = "UPDATE weathertable set temp=?,humidity=?,windspeed=?,weatherdescription=? where id=?";
        boolean existTable = doesTableExist(conn,"weathertable");
        
        PreparedStatement create = conn.prepareStatement(createTable);
        PreparedStatement insert = conn.prepareStatement(insertTable);
        PreparedStatement update = conn.prepareStatement(updateTable);
        
        if(existTable){
            for(int i=0;i<18;i++){
                int index = i;
                
           //     System.out.println("Inserting row: " + cityLabels[i].getText());

                
                update.setString(1, temperatureLabels[index].getText());
                update.setString(2, humidityLabels[index].getText());
                update.setString(3, windLabels[index].getText());
            //    update.setString(4, sealevelLabels[index].getText());
                update.setString(4, descriptionLabels[index].getText());
                update.setString(5,String.valueOf(index+10));
                int rows = update.executeUpdate();
            }
        }else{
            create.execute();
            for(int i=0;i<18;i++){
                int index = i;
                System.out.println("Inserting row: " + cityLabels[i].getText());
                insert.setString(1, String.valueOf(index+10
                ));
                insert.setString(2, cityLabels[index].getText());
                insert.setString(3, temperatureLabels[index].getText());
                insert.setString(4, humidityLabels[index].getText());
                insert.setString(5, windLabels[index].getText());
               insert.setString(6, "null value");//sealevelLabels[index].getText());
                insert.setString(7, descriptionLabels[index].getText());
                int rows = insert.executeUpdate();
            }
        }
        loadWeatherData();
        System.out.println("Update finished");
  
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
    
    private Image fetchWeatherIcon(String weatherCondition){
        Image weatherIcon=null;
        switch(weatherCondition){
            case "overcast clouds":
                weatherIcon =  new Image(getClass().getResource("/Images/overcast_cloud.png").toExternalForm());
                break;
            case "broken clouds":
                weatherIcon =  new Image(getClass().getResource("/Images/broken_clouds.png").toExternalForm());
                break;
            case "clear sky":
                weatherIcon =  new Image(getClass().getResource("/Images/clear_sky.png").toExternalForm());
                break;
            case "few clouds":
                weatherIcon =  new Image(getClass().getResource("/Images/few_clouds.png").toExternalForm());
                break;
            case "scattered clouds":
                weatherIcon =  new Image(getClass().getResource("/Images/scatted_clouds.png").toExternalForm());
                break;
            case "light rain":
                weatherIcon =  new Image(getClass().getResource("/Images/light_rain.png").toExternalForm());
                break;
            case "moderate rain":
                weatherIcon =  new Image(getClass().getResource("/Images/moderate_rain.png").toExternalForm());
                break;
            case "heavy intensity rain":
                weatherIcon =  new Image(getClass().getResource("/Images/heavy_intensity.png").toExternalForm());
                break;
        
        }
        
        return weatherIcon;
    }
    
    
    private String[] parseWeatherInformation(String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        String temperature = String.format("%d", jsonObject.getAsJsonObject("main").get("temp").getAsInt());
        String humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsString();
        String windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsString();
        String seaLevel = jsonObject.getAsJsonObject("main").get("sea_level").getAsString();

        String weatherDescription= jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("description").getAsString();
        System.out.println("Disss: "+weatherDescription);
        return new String[]{temperature, humidity,windSpeed,seaLevel,weatherDescription};
    }
    
    @FXML
    private void refreshData() throws IOException{
        updateWeather();
    }
 
    @FXML
    private void goHome() throws IOException{
        App.setRoot("home");
    }
}