package com.students.sri_lanka_marine_life_explorer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddArticleController {

    private Runnable onArticleSaved;

    public void setOnArticleSaved(Runnable onArticleSaved) {
        this.onArticleSaved = onArticleSaved;
    }

    @FXML
    private Button saveButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField titleField;

    @FXML
    private TextField topicField;

    @FXML
    private TextArea contentArea;

    @FXML
    private void handleSave() {
        String title = titleField.getText().trim();
        String topic = topicField.getText().trim();
        String content = contentArea.getText().trim();

        if (!title.isEmpty() && !topic.isEmpty() && !content.isEmpty()) {
           
            System.out.println("Article saved: " + title);

            if (onArticleSaved != null) {
                onArticleSaved.run();
            }

            closeWindow();
        } else {
            System.out.println("Validation failed: Fill all fields.");
        }
    }

    @FXML
    private void handleBack() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }
}


