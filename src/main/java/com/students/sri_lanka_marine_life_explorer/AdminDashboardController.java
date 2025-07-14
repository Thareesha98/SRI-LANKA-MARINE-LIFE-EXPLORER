/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.students.sri_lanka_marine_life_explorer;

import java.io.IOException;
import javafx.fxml.FXML;

/**
 *
 * @author thareesha
 */
public class AdminDashboardController {
    
    
    
    
    @FXML
    private void goToApprove() throws IOException{
        App.setRoot("license_approval");
    }
    
    @FXML
    private void goToBack() throws IOException{
        App.setRoot("AdminLogin");
    }
    
    
    
   
}
