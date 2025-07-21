/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.students.sri_lanka_marine_life_explorer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class EducationSectionController implements Initializable {

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }   
    
    @FXML
    private void goHome() throws IOException{
        App.setRoot("home");
    }
    

    
    @FXML
    private void goArticles() throws IOException{
        App.setRoot("articles");
    }
    
    @FXML
    private void goAddArticleView() throws IOException{
        App.setRoot("addArticle");
    }
    
    @FXML
    private void goQuizes() throws IOException{
        App.setRoot("quizes");
    }
    
    @FXML
    private void goFunfacts() throws IOException{
        App.setRoot("funFacts");
    }
    
    
}
