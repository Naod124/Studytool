package com.company.studytool.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.company.studytool.R;

import java.util.ArrayList;

public class TruthTableActivity extends AppCompatActivity {

    boolean output = false;
    Spinner spinner;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truth_table);


        Button button1 = findViewById(R.id.out1);
        Button button2 = findViewById(R.id.out2);
        Button button3 = findViewById(R.id.out3);
        Button button4 = findViewById(R.id.out4);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (output==true){
                    button1.setText("1");
                    output=false;
                }else if(output==false){
                    button1.setText("0");
                    output = true;
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (output==true){
                    button2.setText("1");
                    output=false;
                }else if(output==false){
                    button2.setText("0");
                    output = true;
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (output==true){
                    button3.setText("1");
                    output=false;
                }else if(output==false){
                    button3.setText("0");
                    output = true;
                }
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (output==true){
                    button4.setText("1");
                    output=false;
                }else if(output==false){
                    button4.setText("0");
                    output = true;
                }
            }
        });

        spinner= findViewById(R.id.spinner);
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Select Logic gate");
        arrayList.add("AND");
        arrayList.add("OR");
        arrayList.add("XOR");
        arrayList.add("NOR");
        arrayList.add("XNOR");

        spinner.setAdapter(new ArrayAdapter<>(TruthTableActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayList));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position== 0){
                    Toast.makeText(getApplicationContext(), "Please select Logic gate",Toast.LENGTH_SHORT).show();
                }else{
                    String logicGate= parent.getItemAtPosition(position).toString();
                    Button submit = findViewById(R.id.submit);
                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (logicGate.equalsIgnoreCase("AND")){
                                if (!(button1.getText()=="0"&&button2.getText()=="0"&& button3.getText()=="0" &&button4.getText()=="1")){

                                    AlertDialog.Builder builder = new AlertDialog.Builder(TruthTableActivity.this);
                                    builder.setTitle("Wrong");
                                    builder.setMessage("Try Again");
                                    builder.setIcon(R.drawable.wrong);
                                    builder.show();

                                }
                                else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(TruthTableActivity.this);
                                    builder.setTitle("Correct");
                                    builder.setMessage("Great Job");
                                    builder.setIcon(R.drawable.correct);
                                    builder.show();

                                }
                            }if(logicGate.equalsIgnoreCase("OR")){
                                if(!(button1.getText()=="0"&&button2.getText()=="1"&& button3.getText()=="1" &&button4.getText()=="1")){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(TruthTableActivity.this);
                                    builder.setTitle("Wrong");
                                    builder.setMessage("Try Again");
                                    builder.setIcon(R.drawable.wrong);
                                    builder.show();
                                }else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(TruthTableActivity.this);
                                    builder.setTitle("Correct");
                                    builder.setMessage("Great Job");
                                    builder.setIcon(R.drawable.correct);
                                    builder.show();

                                }

                            }if(logicGate.equalsIgnoreCase("XOR")){
                                if(!(button1.getText()=="0"&&button2.getText()=="1"&& button3.getText()=="1" &&button4.getText()=="0")){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(TruthTableActivity.this);
                                    builder.setTitle("Wrong");
                                    builder.setMessage("Try Again");
                                    builder.setIcon(R.drawable.wrong);
                                    builder.show();
                                }else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(TruthTableActivity.this);
                                    builder.setTitle("Correct");
                                    builder.setMessage("Great Job");
                                    builder.setIcon(R.drawable.correct);
                                    builder.show();

                                }

                            }if(logicGate.equalsIgnoreCase("NOR")){
                                if(!(button1.getText()=="1"&&button2.getText()=="0"&& button3.getText()=="0" &&button4.getText()=="0")){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(TruthTableActivity.this);
                                    builder.setTitle("Wrong");
                                    builder.setMessage("Try Again");
                                    builder.setIcon(R.drawable.wrong);
                                    builder.show();
                                }else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(TruthTableActivity.this);
                                    builder.setTitle("Correct");
                                    builder.setMessage("Great Job");
                                    builder.setIcon(R.drawable.correct);
                                    builder.show();

                                }

                            }if(logicGate.equalsIgnoreCase("XNOR")){
                                if(!(button1.getText()=="1"&&button2.getText()=="0"&& button3.getText()=="0" &&button4.getText()=="1")){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(TruthTableActivity.this);
                                    builder.setTitle("Wrong");
                                    builder.setMessage("Try Again");
                                    builder.setIcon(R.drawable.wrong);
                                    builder.show();
                                }else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(TruthTableActivity.this);
                                    builder.setTitle("Correct");
                                    builder.setMessage("Great Job");
                                    builder.setIcon(R.drawable.correct);
                                    builder.show();

                                }

                            }
                        }
                    });

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


}