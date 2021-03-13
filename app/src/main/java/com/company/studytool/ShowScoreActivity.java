package com.company.studytool;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;



    public class ShowScoreActivity extends AppCompatActivity {
        TextView score;
        Button done;

        @SuppressLint("SetTextI18n")
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.show_score_activity);
            score = findViewById(R.id.scoreView);
            done = findViewById(R.id.done);
            Intent intent = getIntent();
            String scoreConverted = intent.getStringExtra("score");
            System.out.println(scoreConverted);
            score.setText("You high score " + scoreConverted);

            done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }


    public ArrayList<Integer> getScore(String filePath) {
        ArrayList<Integer> scores = new ArrayList<Integer>();
        FileInputStream fis = null;
        try {
            fis = openFileInput(filePath + ".text");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {

                scores.add(Integer.parseInt(String.valueOf(sb.append(text))));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return scores;
    }
}
