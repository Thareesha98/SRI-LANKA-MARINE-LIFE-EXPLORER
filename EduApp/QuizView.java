package EduApp;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class QuizView extends Application {

    private List<Question> questions = new ArrayList<>();
    private int currentIndex = 0;
    private int score = 0;

    private Label questionLabel = new Label();
    private RadioButton optionA = new RadioButton();
    private RadioButton optionB = new RadioButton();
    private RadioButton optionC = new RadioButton();
    private RadioButton optionD = new RadioButton();
    private ToggleGroup optionsGroup = new ToggleGroup();
    private Button nextButton = new Button("Next");
    private Button backButton = new Button("Back");
    private Label scoreLabel = new Label();

    private BorderPane root = new BorderPane(); 

    @Override
    public void start(Stage stage) {
        loadQuestions();

        
        optionA.setToggleGroup(optionsGroup);
        optionB.setToggleGroup(optionsGroup);
        optionC.setToggleGroup(optionsGroup);
        optionD.setToggleGroup(optionsGroup);

        
        questionLabel.setWrapText(true);
        questionLabel.setStyle("-fx-font-size: 16px;");


        VBox optionsBox = new VBox(15, optionA, optionB, optionC, optionD);
        optionsBox.setAlignment(Pos.CENTER_LEFT);

        
        HBox buttonBox = new HBox(20, backButton, nextButton);
        buttonBox.setAlignment(Pos.CENTER);

        
        VBox contentBox = new VBox(30, questionLabel, optionsBox, buttonBox, scoreLabel);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setStyle("-fx-padding: 30;");
        contentBox.setMaxWidth(600);

    
        root.setCenter(contentBox);

    
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

        
        backButton.setOnAction(e -> new MainApp().start(stage));

        
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Marine Biodiversity Quiz");
        stage.setScene(scene);
        stage.show();
    }

    private void displayQuestion() {
        Question q = questions.get(currentIndex);
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
        
        Label completedLabel = new Label("Quiz Completed!");
        completedLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Label finalScore = new Label("Final Score: " + score + " out of " + questions.size());
        finalScore.setStyle("-fx-font-size: 16px;");


        HBox finalButtonBox = new HBox(backButton);
        finalButtonBox.setAlignment(Pos.CENTER);

        
        VBox finalBox = new VBox(20, completedLabel, finalScore, finalButtonBox);
        finalBox.setAlignment(Pos.CENTER);

    
        root.setCenter(finalBox);
    }

    private void loadQuestions() {
        questions.add(new Question(
                "1) Which marine animal is known for its hard shell and slow movement?",
                "Octopus", "Sea Turtle", "Shark", "Dolphin", "Sea Turtle"));

        questions.add(new Question(
                "2) What ecosystem is made of calcium carbonate and supports biodiversity?",
                "Mangroves", "Coral Reefs", "Open Ocean", "Sandy Beach", "Coral Reefs"));

        questions.add(new Question(
                "3) What type of plant grows in salty coastal waters and protects shorelines?",
                "Seagrass", "Algae", "Mangroves", "Kelp", "Mangroves"));

        questions.add(new Question(
                "4) Which marine creature filters water to feed and has a soft body?",
                "Turtle", "Jellyfish", "Clam", "Coral", "Clam"));

        questions.add(new Question(
                "5) Which of these is a threat to marine life?",
                "Plastic Pollution", "Coral Reefs", "Tides", "Mangroves", "Plastic Pollution"));
    }

    public static void main(String[] args) {
        launch();
    }
}







