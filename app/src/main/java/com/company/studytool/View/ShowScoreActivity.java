package com.company.studytool.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.company.studytool.R;
import com.company.studytool.Model.StudentDetails;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class ShowScoreActivity extends AppCompatActivity {
    private TextView score;
    private Button done;
    LineGraphSeries<DataPoint> series;

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_score_activity);
        score = findViewById(R.id.scoreView);
        done = findViewById(R.id.done);
        String scoreConverted = StudentDetails.getCurrentCourse();
        ArrayList<Integer> myScore = null;
        try {
            myScore = getScore(scoreConverted);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert myScore != null;
        int maxScore = Collections.max(myScore);
        score.setText("Your high score " + maxScore);


        double y, x;
        x = 0;

        GraphView graph = (GraphView) findViewById(R.id.graph1);
        series = new LineGraphSeries<>();
        for (int i = 0; i < myScore.size(); i++) {
            x = x + 1;
            y = myScore.get(i);
            series.appendData(new DataPoint(x, y), true,maxScore );
        }
        graph.addSeries(series);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowScoreActivity.this, CoursesActivity.class);
                startActivity(intent);
            }
        });


    }


    public ArrayList<Integer> getScore(String filePath) throws FileNotFoundException {
        ArrayList<Integer> scores = new ArrayList<>();
        File directory = getFilesDir();
        File file = new File(directory, filePath + ".txt");

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextInt()) {
            scores.add(scanner.nextInt());
        }
        System.out.println(scores.size());
        return scores;
    }
}
