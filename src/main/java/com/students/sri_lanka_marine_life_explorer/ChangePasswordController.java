/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.students.sri_lanka_marine_life_explorer;

import java.net.HttpURLConnection;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

/**
 *
 * @author thareesha
 */
public class ChangePasswordController {
    @FXML private PasswordField newPasswordField;
    @FXML private Button changePasswordButton;

private String loggedInUsername; // Set this when admin logs in

public void setLoggedInUsername(String username) {
    this.loggedInUsername = username;
}

@FXML
public void initialize() {
    changePasswordButton.setOnAction(event -> changePassword());
}

private void changePassword() {
    String newPassword = newPasswordField.getText();
    if (newPassword.isEmpty()) {
        showAlert("Please enter a new password.");
        return;
    }

    try {
        URL url = new URL("http://localhost:8080/admin/" + loggedInUsername + "/password");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");

        try (var os = conn.getOutputStream()) {
            os.write(("\"" + newPassword + "\"").getBytes());
        }

        if (conn.getResponseCode() == 200) {
            showAlert("Password changed successfully.");
        } else {
            showAlert("Failed to change password.");
        }

        conn.disconnect();
    } catch (Exception e) {
        showAlert("Error: " + e.getMessage());
    }
}

private void showAlert(String msg) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Password Change");
    alert.setHeaderText(null);
    alert.setContentText(msg);
    alert.showAndWait();
}
    
    
}
