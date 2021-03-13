package com.company.studytool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.barteksc.pdfviewer.PDFView;

public class DigitalBook extends AppCompatActivity {

    private PDFView pdfView;
    private EditText text;
    String courseName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital_book);
        Intent intent = getIntent();
        courseName = intent.getStringExtra("course");
        pdfView = (PDFView) findViewById(R.id.pdf);
        switch (courseName) {
            case "Digital System Design":
                pdfView.fromAsset("digital-systems-design.pdf").load();
                break;
            case "Data Structure":
                pdfView.fromAsset("Data_Structures_Algorithm.pdf").load();
                break;
            case "Data Communication":
                pdfView.fromAsset("Computer_Networks_and_Internets.pdf").load();
                break;
            case "Operating System":
                pdfView.fromAsset("operating-system-concepts.pdf").load();
                break;
        }
        text = findViewById(R.id.txtview);
        Button button1 = findViewById(R.id.buttn);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int number = Integer.parseInt(String.valueOf(text.getText()));
                    pdfView.jumpTo(number);
                } catch (NumberFormatException e) {
                    System.out.println("not a number");
                }
            }
        });


    }
}