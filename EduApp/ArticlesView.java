

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package EduApp;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;
import java.nio.file.*;

public class ArticlesView {

    private final Map<String, Article> articleMap = new LinkedHashMap<>();
    private final ListView<String> articleList = new ListView<>();
    private final Label topicLabel = new Label();
    private final TextArea articleContent = new TextArea();

    public void start(Stage stage) {
        loadArticlesFromDB();

        articleList.getItems().addAll(articleMap.keySet());
        articleList.setPrefWidth(150);

        topicLabel.setFont(Font.font("System", FontWeight.BOLD, 20));
        articleContent.setWrapText(true);
        articleContent.setEditable(false);
        articleContent.setStyle("-fx-font-size: 10;");
        VBox.setVgrow(articleContent, Priority.ALWAYS);

        VBox contentBox = new VBox(10, topicLabel, articleContent);
        contentBox.setPrefWidth(600);

        ScrollPane scrollPane = new ScrollPane(contentBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        HBox.setHgrow(scrollPane, Priority.ALWAYS);

        articleList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                Article article = articleMap.get(newVal);
                displayArticle(article);
            }
        });

        Button updateButton = new Button("Add Article");
        updateButton.setOnAction(e -> openUpdateWindow(stage));

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> stage.close());

        HBox topButtons = new HBox(10, updateButton, backButton);

        HBox mainLayout = new HBox(20, articleList, scrollPane);
        VBox root = new VBox(15, topButtons, mainLayout);
        root.setStyle("-fx-padding: 20;");
        VBox.setVgrow(mainLayout, Priority.ALWAYS);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Educational Articles");
        stage.show();

        if (!articleList.getItems().isEmpty()) {
            articleList.getSelectionModel().selectFirst();
        }
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
            Class.forName("com.mysql.jdbc.Driver");
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
                Path file = Paths.get(System.getProperty("user.dir"), "saved_articles", filePath);
                if (Files.exists(file)) {
                    return Files.readString(file);
                }
                return "Error: File not found: " + filePath;
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String line;
                while ((line = br.readLine()) != null) {
                    content.append(line).append("\n");
                }
            }
        } catch (Exception e) {
            return "Error reading file: " + filePath + " - " + e.getMessage();
        }
        return content.toString();
    }

    private void openUpdateWindow(Stage ownerStage) {
        Stage updateStage = new Stage();
        updateStage.initOwner(ownerStage);
        updateStage.initModality(Modality.APPLICATION_MODAL);
        updateStage.setTitle("Add New Article");

        TextField titleField = new TextField();
        TextField topicField = new TextField();
        TextArea contentArea = new TextArea();

        titleField.setPromptText("Enter article title");
        topicField.setPromptText("Enter article topic");
        contentArea.setPromptText("Enter article content");
        contentArea.setPrefRowCount(15);

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            String title = titleField.getText().trim();
            String topic = topicField.getText().trim();
            String content = contentArea.getText().trim();

            if (title.isEmpty() || topic.isEmpty() || content.isEmpty()) {
                showAlert("All fields must be filled.");
                return;
            }

            String fileName = title.replaceAll("\\s+", "_") + ".txt";

            if (saveContentToFile(fileName, content) && insertArticleIntoDB(title, topic, fileName)) {
                Article newArticle = new Article(title, topic, fileName);
                articleMap.put(title, newArticle);
                articleList.getItems().add(title);
                articleList.getSelectionModel().select(title);
                updateStage.close();
            } else {
                showAlert("Failed to save the article.");
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> updateStage.close());

        VBox form = new VBox(10,
                new Label("Title:"), titleField,
                new Label("Topic:"), topicField,
                new Label("Content:"), contentArea,
                new HBox(10, saveButton, backButton));
        form.setStyle("-fx-padding: 20;");
        form.setPrefSize(400, 500);

        updateStage.setScene(new Scene(form));
        updateStage.show();
    }

    private boolean saveContentToFile(String fileName, String content) {
        try {
            String userDir = System.getProperty("user.dir");
            Path articlesDir = Paths.get(userDir, "saved_articles");

            if (!Files.exists(articlesDir)) {
                Files.createDirectories(articlesDir);
            }

            Path filePath = articlesDir.resolve(fileName);
            Files.write(filePath, content.getBytes());

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean insertArticleIntoDB(String title, String topic, String filepath) {
        String sql = "INSERT INTO articles (title, topic, filepath) VALUES (?, ?, ?)";
        try (Connection conn = connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, topic);
            pstmt.setString(3, filepath);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
