

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */


package EduApp;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class ArticlesView {

    public void start(Stage stage) {
        
        Map<String, Article> articles = new LinkedHashMap<>();
        articles.put("Article 1", new Article("/resources/articles1.txt", "/resources/images1.jpg"));
        articles.put("Article 2", new Article("/resources/articles2.txt", "/resources/images2.jpg"));
        articles.put("Article 3", new Article("/resources/articles3.txt", "/resources/images3.jpg"));

        
        ListView<String> articleList = new ListView<>();
        articleList.getItems().addAll(articles.keySet());
        articleList.setPrefWidth(150);

        ImageView articleImage = new ImageView();
        articleImage.setFitWidth(300);
        articleImage.setPreserveRatio(true);

        TextArea articleContent = new TextArea();
        articleContent.setWrapText(true);
        articleContent.setEditable(false);
        VBox.setVgrow(articleContent, Priority.ALWAYS);

        
        VBox contentBox = new VBox(15, articleImage, articleContent);
        contentBox.setAlignment(Pos.TOP_CENTER);
        contentBox.setPrefWidth(600);

        
        ScrollPane scrollPane = new ScrollPane(contentBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        HBox.setHgrow(scrollPane, Priority.ALWAYS);

       
        articleList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                Article selected = articles.get(newVal);
                articleContent.setText(loadTextFile(selected.textPath));
                articleImage.setImage(new Image(getClass().getResourceAsStream(selected.imagePath)));
            }
        });

        
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> new MainApp().start(stage));
        HBox backContainer = new HBox(backButton);
        backContainer.setAlignment(Pos.CENTER_LEFT);

       
        HBox mainLayout = new HBox(20, articleList, scrollPane);
        mainLayout.setAlignment(Pos.CENTER);
        VBox root = new VBox(15, mainLayout, backContainer);
        root.setStyle("-fx-padding: 20;");
        VBox.setVgrow(mainLayout, Priority.ALWAYS);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Educational Articles");
        stage.show();
    }

    private String loadTextFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream(filePath)))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
                content.append(line).append("\n");
            return content.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Unable to load content.";
        }
    }

    private static class Article {
        String textPath;
        String imagePath;

        Article(String textPath, String imagePath) {
            this.textPath = textPath;
            this.imagePath = imagePath;
        }
    }
}







