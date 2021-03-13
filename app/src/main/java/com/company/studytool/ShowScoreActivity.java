package com.company.studytool;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class ShowScoreActivity extends AppCompatActivity {
    TextView score;
    Button done;
    LineGraphSeries<DataPoint> series;

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_score_activity);
        score = findViewById(R.id.scoreView);
        done = findViewById(R.id.done);
        String scoreConverted = StudentDetails.getCurrentCourse();
        ArrayList<Integer> MyScore = null;
        try {
            MyScore = getScore(scoreConverted);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
int MazScore=Collections.max(MyScore);
        score.setText("Your high score " + MazScore);


        double y, x;
        x = 0;

        GraphView graph = (GraphView) findViewById(R.id.graph1);
        series = new LineGraphSeries<>();
        for (int i = 0; i < MyScore.size(); i++) {
            x=x+1;
            y = MyScore.get(i);
            series.appendData(new DataPoint(x, y), true, MazScore);
        }
        graph.addSeries(series);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }




    public ArrayList<Integer> getScore(String filePath) throws FileNotFoundException {
        ArrayList<Integer> scores = new ArrayList<>();
        File directory  = getFilesDir();
        File file = new File(directory ,filePath+".txt");

        Scanner scanner = new Scanner(file);

        while(scanner.hasNextInt())
        {
            scores.add(scanner.nextInt());
        }
        System.out.println(scores.size());
        return scores;
    }
}
