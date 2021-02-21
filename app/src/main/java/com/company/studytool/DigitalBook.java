package com.company.studytool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.barteksc.pdfviewer.PDFView;

public class DigitalBook extends AppCompatActivity {

    private PDFView pdfView;
    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital_book);
        pdfView= (PDFView)findViewById(R.id.pdf);
        pdfView.fromAsset("digital-systems-design.pdf").load();
        text = findViewById(R.id.txtview);
        Button button1 = findViewById(R.id.buttn);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int number= Integer.parseInt(String.valueOf(text.getText()));
                    pdfView.jumpTo(number);
                }catch (NumberFormatException e){
                    System.out.println("not a number");
                }
            }
        });


    }
}