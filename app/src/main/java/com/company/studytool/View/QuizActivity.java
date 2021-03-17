package com.company.studytool.View;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.company.studytool.Model.PrepareQuiz;
import com.company.studytool.Model.QuestionModel;
import com.company.studytool.R;
import com.company.studytool.Model.StudentDetails;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.PrintStream;
import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton choice1;
    private RadioButton choice2;
    private RadioButton choice3;
    private RadioButton choice4;
    private RadioButton checkedButton;
    private ImageButton nextQuestion;
    private TextSwitcher question;
    private TextView questionNum;
    private TextView triesTextView;
    private TextView timer;
    private TextView score;
    private String rightAnswer;
    private int time = 15; // for testing purpose
    private int scoreNum = 0;
    private int position = 0;
    private int questionNumber = 1;
    private int tries = 2;
    private final ArrayList<QuestionModel> quizList = new ArrayList<>();
    String courseName = "";
    Drawable pressedButton;
    Drawable disabledButton;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        pressedButton = getResources().getDrawable(R.drawable.button_pressed);
        disabledButton = getResources().getDrawable(R.drawable.button_disabled);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);
        radioGroup = findViewById(R.id.radioGroup);
        choice1 = findViewById(R.id.choice1);
        choice2 = findViewById(R.id.choice2);
        choice3 = findViewById(R.id.choice3);
        choice4 = findViewById(R.id.choice4);
        question = findViewById(R.id.question);
        question.setInAnimation(this, android.R.anim.slide_in_left);
        question.setOutAnimation(this, android.R.anim.slide_out_right);
        nextQuestion = findViewById(R.id.nextQuestion);
        questionNum = findViewById(R.id.questionNum);
        timer = findViewById(R.id.timer);
        score = findViewById(R.id.score);
        triesTextView = findViewById(R.id.tries);
        Intent intent = getIntent();
        courseName = intent.getStringExtra("course");
        StudentDetails.setCurrentCourse(courseName);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            checkedButton = findViewById(checkedId);
            if (tries == 0) {
                showRightAnswer();
                position++;
                tries = 2;
                setNextQuestion();
            } else {
                checkAnswer(checkedButton, quizList);
            }
        });
        nextQuestion.setOnClickListener(v -> {
            position++;
            tries = 2;
            setNextQuestion();
        });
        switch (courseName) {
            case "Data Structure":
                PrepareQuiz.prepareDsQuiz(quizList);
                initializerQuiz();
                break;
            case "Data Communication":
                PrepareQuiz.prepareDcQuiz(quizList);
                initializerQuiz();
                break;
            case "Operating System":
                PrepareQuiz.prepareOsQuiz(quizList);
                initializerQuiz();
                break;
        }
    }

    private void checkAnswer(Button clickedButton, ArrayList<QuestionModel> courseQuiz) {
        if (checkedButton.getText() == null) {
            showRightAnswer();
            setNextQuestion();
        } else if (checkedButton.getText().equals(courseQuiz.get(position).rightAnswer)) {
            checkedButton.setEnabled(false);
            scoreNum++;
            countDownTimer.cancel();
            score.setText("Score : " + scoreNum);
            Toast.makeText(QuizActivity.this, "RightAnswer", Toast.LENGTH_SHORT).show();
            tries = 2;
        } else {
            tries--;
            triesTextView.setText("Remaining attempts :" + (tries));
        }
    }

    public void showRightAnswer() {
        rightAnswer = quizList.get(position).rightAnswer;
        Toast.makeText(QuizActivity.this, "i think >> " + rightAnswer.toUpperCase() + " << is the right answer ..", Toast.LENGTH_LONG).show();
    }

    private void initializerQuiz() {
        rightAnswer = quizList.get(position).rightAnswer;
        questionNum.setText("Questions : " + (questionNumber) + "/" + quizList.size());
        triesTextView.setText("Remaining attempts :" + (tries));
        question.setText(quizList.get(position).question);
        choice1.setText(quizList.get(position).choice1);
        choice2.setText(quizList.get(position).choice2);
        choice3.setText(quizList.get(position).choice3);
        choice4.setText(quizList.get(position).choice4);
        startTimer();
    }

    public void setNextQuestion() {
        countDownTimer.cancel();
        if (position < quizList.size()) {
            questionNumber++;
            triesTextView.setText("Remaining attempts :" + (tries));
            question.setText(quizList.get(position).question);
            choice1.setText(quizList.get(position).choice1);
            choice2.setText(quizList.get(position).choice2);
            choice3.setText(quizList.get(position).choice3);
            choice4.setText(quizList.get(position).choice4);
            questionNum.setText("Questions : " + (questionNumber) + "/" + quizList.size());
            enableAllChoices();
            time = 15;
            countDownTimer.start();
        } else {
            try {
                alertDialog();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(15000, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText("0:" + time);
                time--;
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                timer.setText("0:0");
                disableAllChoices();
                rightAnswer = quizList.get(position).rightAnswer;
                Toast.makeText(QuizActivity.this, "i think >>" + rightAnswer + "<< is the right answer ..", Toast.LENGTH_LONG).show();
            }
        }.start();
    }

    private void alertDialog() throws FileNotFoundException {
        saveScore(scoreNum, courseName);
        AlertDialog alertDialog = new AlertDialog.Builder(QuizActivity.this)
                .setMessage("Do you wanna repeat this quiz ?").setTitle("Quiz Complete").setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        position = 0;
                        questionNumber = 0;
                        scoreNum = 0;
                        setNextQuestion();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(QuizActivity.this, ShowScoreActivity.class).putExtra("score", scoreNum);
                        startActivity(intent);
                    }
                }).show();
    }

    private void enableAllChoices() {
        choice1.setEnabled(true);
        choice2.setEnabled(true);
        choice3.setEnabled(true);
        choice4.setEnabled(true);
    }

    private void disableAllChoices() {
        choice1.setEnabled(false);
        choice2.setEnabled(false);
        choice3.setEnabled(false);
        choice4.setEnabled(false);
    }


    public void saveScore(int number, String filePath) {
        File directory = getFilesDir();
        directory.mkdirs();
        File file = new File(directory, filePath + ".txt");
        try {
            FileOutputStream fos = new FileOutputStream(file, true);

            PrintStream printstream = new PrintStream(fos);
            printstream.print(number + "\n");
            fos.close();
        } catch (Exception e) {
            Toast.makeText(QuizActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}


