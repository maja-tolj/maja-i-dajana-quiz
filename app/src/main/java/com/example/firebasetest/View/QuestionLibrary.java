package com.example.firebasetest.View;

public class QuestionLibrary {
    private String mQuestion [] = {
            "CORRECT RESULT IS: 2*3+6 = ?",
            "CORRECT RESULT IS: 5*7-5 = ?",
            "CORRECT RESULT IS: 7+4*3 = ?",
            "CORRECT RESULT IS: 3+8*9 = ?",
            "CORRECT RESULT IS: 10-4/2 = ?",
            "CORRECT RESULT IS: 6-2/0 = ?",
            "CORRECT RESULT IS: 9/9+5*2 = ?",
            "CORRECT RESULT IS: 8/2-3*0 = ?",
            "CORRECT RESULT IS: 9*5+8/4 = ?",
            "CORRECT RESULT IS: 8*6-10/2 = ?"
    };
    private String mChoices [] [] = {
            {"12","13","14"},
            {"29","30","31"},
            {"17","18","19"},
            {"75","76","77"},
            {"7","8","9"},
            {"4","5","6"},
            {"11","12","13"},
            {"3","4","5"},
            {"45","46","47"},
            {"43","44","45"}
    };
    private String mCorrectAnswers [] = {"12","30","19","75","8","6","11","4","47","43"};

    public String getQuestion(int a){
        String question = mQuestion[a];
        return question;
    };
    public String getChoice1(int a){
        String choice0 = mChoices[a][0];
        return choice0;
    };
    public String getChoice2(int a){
        String choice1 = mChoices[a][1];
        return choice1;
    };
    public String getChoice3(int a){
        String choice2 = mChoices[a][2];
        return choice2;
    };
    public String getChoice4(int a){
        String choice3 = mChoices[a][0];
        return choice3;
    };
    public String getChoice5(int a){
        String choice4 = mChoices[a][1];
        return choice4;
    };
    public String getChoice6(int a){
        String choice5 = mChoices[a][2];
        return choice5;
    };
    public String getChoice7(int a){
        String choice6 = mChoices[a][0];
        return choice6;
    };
    public String getChoice8(int a){
        String choice7 = mChoices[a][1];
        return choice7;
    };
    public String getChoice9(int a){
        String choice8 = mChoices[a][2];
        return choice8;
    };
    public String getChoice10(int a){
        String choice9 = mChoices[a][0];
        return choice9;
    };

    public String getCorrectAnswer(int a){
        String answer = mCorrectAnswers[a];
        return answer;
    }
}
