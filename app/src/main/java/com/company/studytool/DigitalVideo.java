package com.company.studytool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;

public class DigitalVideo extends AppCompatActivity {
    RecyclerView recyclerView;
    String courseName = "";
    ArrayList<DataSetList> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital_video);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        arrayList = new ArrayList<DataSetList>();
        Intent intent = getIntent();
        courseName = intent.getStringExtra("course");
        switch (courseName) {
            case "Digital System Design":
                readDdYoutubeLinks();
                break;
            case "Data Structure":
                readDsYoutubeLinks();
                break;
            case "Data Communication":
                readDcYoutubeLinks();
                break;
            case "Operating System":
                readOsYoutubeLinks();
                break;
        }
//        DataSetList dataSetList = new DataSetList("https://www.youtube.com/embed/Z6Nds10n7rs");
//        arrayList.add(dataSetList);
//        dataSetList = new DataSetList("https://www.youtube.com/embed/aLUY-s7LSns");
//        arrayList.add(dataSetList);
//        dataSetList = new DataSetList("https://www.youtube.com/embed/RK3P9L2ZXk4");
//        arrayList.add(dataSetList);
//        dataSetList = new DataSetList("https://www.youtube.com/embed/NO7Gt8IDSGA");
//        arrayList.add(dataSetList);
//        dataSetList = new DataSetList("https://www.youtube.com/embed/9O7OJi7RCLQ");
//        arrayList.add(dataSetList);
//        dataSetList = new DataSetList("https://www.youtube.com/embed/FKvnmxte98A");
//        arrayList.add(dataSetList);
        YoutubeAdapter youtubeAdapter = new YoutubeAdapter(arrayList, getApplicationContext());
        recyclerView.setAdapter(youtubeAdapter);
    }

    private void readDdYoutubeLinks() {
        String[] links = getResources().getStringArray(R.array.DdVideos);
        for (int i = 0; i < links.length; i++) {
            DataSetList dataSetList = new DataSetList(links[i]);
            arrayList.add(dataSetList);
        }
    }

    private void readDsYoutubeLinks() {
        String[] links = getResources().getStringArray(R.array.DsVideos);
        for (int i = 0; i < links.length; i++) {
            DataSetList dataSetList = new DataSetList(links[i]);
            arrayList.add(dataSetList);
        }
    }

    private void readDcYoutubeLinks() {
        String[] links = getResources().getStringArray(R.array.DcVideos);
        for (int i = 0; i < links.length; i++) {
            DataSetList dataSetList = new DataSetList(links[i]);
            arrayList.add(dataSetList);
        }
    }

    private void readOsYoutubeLinks() {
        String[] links = getResources().getStringArray(R.array.OsVideos);
        for (int i = 0; i < links.length; i++) {
            DataSetList dataSetList = new DataSetList(links[i]);
            arrayList.add(dataSetList);
        }
    }
}