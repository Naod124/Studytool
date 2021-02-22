package com.company.studytool;

import java.util.ArrayList;

public class QuizModel {
    private ArrayList<QuestionModel> questions ;

    public QuizModel(ArrayList<QuestionModel> questions) {
        this.questions = questions;
    }

    public ArrayList<QuestionModel> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<QuestionModel> questions) {
        this.questions = questions;
    }
}
