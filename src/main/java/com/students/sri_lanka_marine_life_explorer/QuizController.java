package com.students.sri_lanka_marine_life_explorer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.*;

public class QuizController implements Initializable {

    @FXML private Label questionLabel;
    @FXML private RadioButton optionA;
    @FXML private RadioButton optionB;
    @FXML private RadioButton optionC;
    @FXML private RadioButton optionD;
    @FXML private Button nextButton;
    @FXML private Button backButton;
    @FXML private Label scoreLabel;

    private ToggleGroup optionsGroup;

    private List<QuestionController> questions = new ArrayList<>();
    private int currentIndex = 0;
    private int score = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        optionsGroup = new ToggleGroup();
        optionA.setToggleGroup(optionsGroup);
        optionB.setToggleGroup(optionsGroup);
        optionC.setToggleGroup(optionsGroup);
        optionD.setToggleGroup(optionsGroup);

        loadQuestions();
        displayQuestion();

        nextButton.setOnAction(e -> {
            checkAnswer();
            currentIndex++;
            if (currentIndex < questions.size()) {
                displayQuestion();
            } else {
                showCompletionScreen();
            }
        });
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

    private void loadQuestions() {
        questions.add(new QuestionController("1) Which marine animal is known for its hard shell and slow movement?", "Octopus", "Sea Turtle", "Shark", "Dolphin", "Sea Turtle"));
        questions.add(new QuestionController("2) What ecosystem is made of calcium carbonate and supports biodiversity?", "Mangroves", "Coral Reefs", "Open Ocean", "Sandy Beach", "Coral Reefs"));
        questions.add(new QuestionController("3) What type of plant grows in salty coastal waters and protects shorelines?", "Seagrass", "Algae", "Mangroves", "Kelp", "Mangroves"));
        questions.add(new QuestionController("4) Which marine creature filters water to feed and has a soft body?", "Turtle", "Jellyfish", "Clam", "Coral", "Clam"));
        questions.add(new QuestionController("5) Which of these is a threat to marine life?", "Plastic Pollution", "Coral Reefs", "Tides", "Mangroves", "Plastic Pollution"));
    }

    private void displayQuestion() {
        QuestionController q = questions.get(currentIndex);
        questionLabel.setText(q.getQuestion());
        optionA.setText(q.getOptionA());
        optionB.setText(q.getOptionB());
        optionC.setText(q.getOptionC());
        optionD.setText(q.getOptionD());
        optionsGroup.selectToggle(null);

        optionA.setVisible(true);
        optionB.setVisible(true);
        optionC.setVisible(true);
        optionD.setVisible(true);
        nextButton.setDisable(false);
        backButton.setDisable(false);
    }

    private void checkAnswer() {
        RadioButton selected = (RadioButton) optionsGroup.getSelectedToggle();
        if (selected != null) {
            String answer = selected.getText();
            if (answer.equals(questions.get(currentIndex).getCorrectAnswer())) {
                score++;
            }
        }
    }

    private void showCompletionScreen() {
        questionLabel.setText("🎉 Quiz Completed!");
        optionA.setVisible(false);
        optionB.setVisible(false);
        optionC.setVisible(false);
        optionD.setVisible(false);
        nextButton.setDisable(true);
        scoreLabel.setText("Your Score: " + score + " out of " + questions.size());
    }
}

