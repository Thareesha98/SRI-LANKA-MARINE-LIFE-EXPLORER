/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.students.sri_lanka_marine_life_explorer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

import com.students.sri_lanka_marine_life_explorer.models.Admin;
import java.io.IOException;
import java.io.OutputStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author thareesha
 */
public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;
    
    private final ObjectMapper mapper = new ObjectMapper();

    
    @FXML
    private void handleLogin(ActionEvent event){
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        try {
            URL url = new URL("http://13.201.93.127:8080/login");  // Adjust port if needed
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            Admin inputAdmin = new Admin();
            inputAdmin.setUsername(username);
            inputAdmin.setPassword(password);

            OutputStream os = conn.getOutputStream();
            mapper.writeValue(os, inputAdmin);
            os.flush();
            os.close();

            if (conn.getResponseCode() == 200) {
                Admin loggedIn = mapper.readValue(conn.getInputStream(), Admin.class);
                if (loggedIn != null) {
                    // Login success – navigate to license approval view
                 //   FXMLLoader loader = new FXMLLoader(getClass().getResource("license_approval.fxml"));
                  //  Parent root = loader.load();
                 //   Stage stage = (Stage) usernameField.getScene().getWindow();
                 //   stage.setScene(new Scene(root));
                 //   stage.setTitle("License Approvals");
                 App.setRoot("AdminDashboard");
                } else {
                    errorLabel.setText("Invalid username or password.");
                }
            } else {
                errorLabel.setText("Login failed.");
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            errorLabel.setText("Invalid Username and Password");
        }
        
    }
    
    @FXML
    private void goToMainMenu() throws IOException{
        App.setRoot("home");
    }
}
