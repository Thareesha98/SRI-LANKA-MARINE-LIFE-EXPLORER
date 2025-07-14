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
//public class FisheringController implements Initializable {
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



import com.students.sri_lanka_marine_life_explorer.models.Fisherman;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FisheringController{

    @FXML private TextField nameField, nicField, districtField, contactField;
    @FXML private TableView<Fisherman> fishermanTable;
    @FXML private TableColumn<Fisherman, String> nameCol, nicCol, districtCol, contactCol;

    private final ObservableList<Fisherman> fishermanList = FXCollections.observableArrayList();
    
    //@FXML
    public void initialize()  {
        nameCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getName()));
        nicCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getNic()));
        districtCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getDistrict()));
        contactCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getContact()));

        fishermanTable.setItems(fishermanList);
    }

    @FXML
    private void registerFisherman() {
        try {
            Fisherman f = new Fisherman();
            f.setName(nameField.getText());
            f.setNic(nicField.getText());
            f.setDistrict(districtField.getText());
            f.setContact(contactField.getText());

          //  URL url = new URL("http://localhost:8080/api/fishermen");
          //URL url = new URL("http://43.204.114.234:8080/api/fishermen");
          URL url = new URL("http://13.201.93.127:8080/api/fishermen");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(f);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(json.getBytes());
                os.flush();
            }

            if (conn.getResponseCode() == 200 || conn.getResponseCode() == 201) {
                fishermanList.add(f);
                nameField.clear(); nicField.clear(); districtField.clear(); contactField.clear();
            }
            System.out.println("Addedd");

            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to add");
        }
    }
    
    @FXML
    private void goToMainMenu() throws IOException{
        App.setRoot("home");
    }
}
