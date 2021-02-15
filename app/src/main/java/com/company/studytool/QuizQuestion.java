package com.company.studytool;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class QuizQuestion extends AppCompatActivity {
    TextView mainQuestion;
    Button nextButton;
    ToggleButton option1;
    ToggleButton option2;
    ToggleButton option3;
    ToggleButton option4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question);
        mainQuestion = findViewById(R.id.textView);
        nextButton = findViewById(R.id.nextQuiz);
        option1 = findViewById(R.id.choiceButton1);
        option2 = findViewById(R.id.choiceButton2);
        option3 = findViewById(R.id.choiceButton3);
        option4 = findViewById(R.id.choiceButton4);


    }
    }

