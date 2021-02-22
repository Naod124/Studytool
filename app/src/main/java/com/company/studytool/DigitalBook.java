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
        if (courseName.equals("Digital System Design")) {
            pdfView.fromAsset("digital-systems-design.pdf").load();
        } else if (courseName.equals("Data Structure")) {
            //book missing
//            pdfView.fromAsset("operating-system-concepts.pdf").load();
        } else if (courseName.equals("Data Communication")) {
            //book missing
//            pdfView.fromAsset("operating-system-concepts.pdf").load();
        } else if (courseName.equals("Operating System")) {
            pdfView.fromAsset("operating-system-concepts.pdf").load();
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