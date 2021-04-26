package com.company.studytool.View;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import com.company.studytool.Model.PrepareQuiz;
import com.company.studytool.Model.QuestionModel;
import com.company.studytool.Model.StudentDetails;
import com.company.studytool.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link quiz#newInstance} factory method to
 * create an instance of this fragment.
 */
public class quiz extends Fragment {
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
    private String courseName = "";
    private Drawable pressedButton;
    private Drawable disabledButton;
    private CountDownTimer countDownTimer;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public quiz() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment quiz.
     */
    // TODO: Rename and change types and number of parameters
    public static quiz newInstance(String param1, String param2) {
        quiz fragment = new quiz();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

   View root = inflater.inflate(R.layout.fragment_facebook, container, false);

        // Inflate the layout for this fragment
        pressedButton = getResources().getDrawable(R.drawable.button_pressed);
        disabledButton = getResources().getDrawable(R.drawable.button_disabled);
        super.onCreate(savedInstanceState);
        radioGroup = root.findViewById(R.id.radioGroup);
        choice1 = root.findViewById(R.id.choice1);
        choice2 = root.findViewById(R.id.choice2);
        choice3 = root.findViewById(R.id.choice3);
        choice4 = root.findViewById(R.id.choice4);
        question = root.findViewById(R.id.question);
        question.setInAnimation(getActivity(), android.R.anim.slide_in_left);
        question.setOutAnimation(getActivity(), android.R.anim.slide_out_right);
        nextQuestion = root.findViewById(R.id.nextQuestion);
        questionNum = root.findViewById(R.id.questionNum);
        timer = root.findViewById(R.id.timer);
        score = root.findViewById(R.id.score);
        triesTextView = root.findViewById(R.id.tries);
        Intent intent = getActivity().getIntent();
        courseName = intent.getStringExtra("course");
        StudentDetails.setCurrentCourse(courseName);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            checkedButton = root.findViewById(checkedId);
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
        return inflater.inflate(R.layout.fragment_quiz, container, false);
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
            Toast.makeText(getActivity(), "RightAnswer", Toast.LENGTH_SHORT).show();
            tries = 2;
        } else {
            tries--;
            triesTextView.setText("Remaining attempts :" + (tries));
        }
    }

    public void showRightAnswer() {
        rightAnswer = quizList.get(position).rightAnswer;
        Toast.makeText(getActivity(), "i think >> " + rightAnswer.toUpperCase() + " << is the right answer ..", Toast.LENGTH_LONG).show();
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
                Toast.makeText(getActivity(), "i think >>" + rightAnswer + "<< is the right answer ..", Toast.LENGTH_LONG).show();
            }
        }.start();
    }

    private void alertDialog() throws FileNotFoundException {
        saveScore(scoreNum, courseName);
        AlertDialog alertDialog = new AlertDialog.Builder(Objects.requireNonNull(getActivity()))
                .setMessage("Do you wanna repeat this quiz ?").setTitle("Quiz Complete").setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        position = 0;
                        questionNumber = 0;
                        scoreNum = 0;
                        score.setText("Score : " + scoreNum);
                        setNextQuestion();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getActivity(), ShowScoreActivity.class).putExtra("score", scoreNum);
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
        File directory = Objects.requireNonNull(getActivity()).getFilesDir();
        directory.mkdirs();
        File file = new File(directory, filePath + ".txt");
        try {
            FileOutputStream fos = new FileOutputStream(file, true);

            PrintStream printstream = new PrintStream(fos);
            printstream.print(number + "\n");
            fos.close();
        } catch (Exception e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}