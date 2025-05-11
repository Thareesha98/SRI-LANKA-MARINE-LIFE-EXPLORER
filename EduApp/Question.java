/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EduApp;

/**
 *
 * @author Dell
 */
public class Question {
    String question, optionA, optionB, optionC, optionD, correctAnswer;

    public Question(String question, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
    }

    // Optional: Add getters if needed
    public String getQuestion() 
    { 
        return question; 
    }
    public String getOptionA() 
    { 
        return optionA; 
    }
    public String getOptionB()
    {
        return optionB;
    }
    public String getOptionC()
    {
        return optionC; 
    }
    public String getOptionD() 
    {
        return optionD; 
    }
    public String getCorrectAnswer()
    {
        return correctAnswer; 
    }
}

