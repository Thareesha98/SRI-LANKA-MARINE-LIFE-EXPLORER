package com.students.sri_lanka_marine_life_explorer;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.sql.*;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ArticleController {

    @FXML
    private ListView<String> articleList;

    @FXML
    private Label topicLabel;

    @FXML
    private TextArea articleContent;

    @FXML
    private Button addArticleButton;

    @FXML
    private Button backButton;

    private final Map<String, Article> articleMap = new LinkedHashMap<>();
    
        @FXML
    private void goEducationSection() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/students/sri_lanka_marine_life_explorer/fxml/EducationSection.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    public void initialize() {
        loadArticlesFromDB();
        ObservableList<String> titles = FXCollections.observableArrayList(articleMap.keySet());
        articleList.setItems(titles);

        articleList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                Article article = articleMap.get(newVal);
                displayArticle(article);
            }
        });

        if (!titles.isEmpty()) {
            articleList.getSelectionModel().selectFirst();
        }

        
        addArticleButton.setOnAction(e -> openAddArticleWindow());

 
    backButton.setOnAction(e -> {
            
            goBackToEducationSection();
        });
    
    }
    
    
    private void goBackToEducationSection() {
        try {
           
            App.setRoot("educationSection");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goAddArticleView(ActionEvent event) {
            openAddArticleWindow();
    }

    public void openAddArticleWindow() {
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/students/sri_lanka_marine_life_explorer/fxml/addArticle.fxml"));

            
            System.out.println("FXML URL = " + getClass().getResource("/com/students/sri_lanka_marine_life_explorer/fxml/addArticle.fxml"));

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Add Article");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    
    @FXML
    private void goAddArticleView() throws IOException {
        App.setRoot("addArticle"); 
    }

    private void displayArticle(Article article) {
        topicLabel.setText(article.topic);
        articleContent.setText(readContentFromFile(article.filePath));
    }

    private void loadArticlesFromDB() {
        try (Connection conn = connectDB();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT title, topic, filepath FROM articles")) {

            while (rs.next()) {
                String title = rs.getString("title");
                String topic = rs.getString("topic");
                String filepath = rs.getString("filepath");
                articleMap.put(title, new Article(title, topic, filepath));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/eduapp", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String readContentFromFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (InputStream is = getClass().getResourceAsStream(filePath)) {
            if (is == null) {
                return "File not found in resources: " + filePath;
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String line;
                while ((line = br.readLine()) != null) {
                    content.append(line).append("\n");
                }
            }
        } catch (Exception e) {
            return "Error reading file: " + e.getMessage();
        }
        return content.toString();
    }

    private static class Article {
        String title;
        String topic;
        String filePath;

        public Article(String title, String topic, String filePath) {
            this.title = title;
            this.topic = topic;
            this.filePath = filePath;
        }
    }
}




