package com.example.bmmo;

public class Quiz {
    public String Questions[] = {
            "question1",
            "question2",
            "question3",
            "question4",
            "question5",
            "question6",
            "question7",

    };
    private String Choices[][] = {
            {"choice1", "choice2", "choice3", "choice4"},
            {"choice1", "choice2", "choice3", "choice4"},
            {"choice1", "choice2", "choice3", "choice4"},
            {"choice1", "choice2", "choice3", "choice4"},
            {"choice1", "choice2", "choice3", "choice4"},
            {"choice1", "choice2", "choice3", "choice4"},
            {"choice1", "choice2", "choice3", "choice4"},

    };
    private String correctAnswers[] = {"choice1", "choice2", "choice3", "choice4", "choice1", "choice2", "choice3"};

    public String getQuestion(int quest) {
        String question = Questions[quest];
        return question;
    }
    public String getAnswer(int quest) {
        String choice = Choices[quest][0];
        return choice;
    }
}
