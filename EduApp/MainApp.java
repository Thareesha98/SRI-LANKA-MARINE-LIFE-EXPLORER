package EduApp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */


/**
 *
 * @author Dell
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button btnArticles = new Button("Articles");
        Button btnQuiz = new Button("Quiz");
        Button btnFunFacts = new Button("Fun Facts");

        // Navigation
        btnArticles.setOnAction(e -> new ArticlesView().start(primaryStage));
        btnQuiz.setOnAction(e -> new QuizView().start(primaryStage));
        btnFunFacts.setOnAction(e -> new FunFactsView().start(primaryStage));

        VBox menu = new VBox(15, btnArticles, btnQuiz, btnFunFacts);
        menu.setAlignment(Pos.CENTER);
        Scene scene = new Scene(menu, 400, 300);

        primaryStage.setTitle("Sri Lankan Marine Biodiversity - Education Section");
        primaryStage.setScene(scene);
        primaryStage.show(); 
    }

    public static void main(String[] args) {
        launch(args);
    }
}

