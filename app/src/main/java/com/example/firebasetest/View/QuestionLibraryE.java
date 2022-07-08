package com.example.firebasetest.View;

public class QuestionLibraryE {
    private String mQuestion [] = {
            "CORRECT RESULT IS: 23*4-20 = ?",
            "CORRECT RESULT IS: 11*7-15 = ?",
            "CORRECT RESULT IS: 32+7*7 = ?",
            "CORRECT RESULT IS: 39/13+6*12 = ?",
            "CORRECT RESULT IS: 56/4-27/3 = ?",
            "CORRECT RESULT IS: 66-44/4 = ?",
            "CORRECT RESULT IS: 45/15+8*3 = ?",
            "CORRECT RESULT IS: 42/14+12*8 = ?",
            "CORRECT RESULT IS: 6*5+28/4 = ?",
            "CORRECT RESULT IS: 14*6-51/3 = ?"
    };

    private String mChoices [] [] = {
            {"72","73","74"},
            {"61","62","63"},
            {"79","80","81"},
            {"75","76","77"},
            {"4","5","6"},
            {"53","54","55"},
            {"27","28","29"},
            {"98","99","100"},
            {"35","36","37"},
            {"67","68","69"}
    };
    private String mCorrectAnswers [] = {"72","62","81","75","5","55","27","99","37","67"};

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
