package com.company.studytool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edmt.dev.videoplayer.VideoPlayerRecyclerView;
import edmt.dev.videoplayer.adapter.VideoPlayerRecyclerAdapter;
import edmt.dev.videoplayer.model.MediaObject;
import edmt.dev.videoplayer.utils.VerticalSpacingItemDecorator;

public class DigitalVideo extends AppCompatActivity {
    RecyclerView recyclerView;

    ArrayList<DataSetList> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital_video);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        arrayList = new ArrayList<DataSetList>();

        DataSetList dataSetList = new DataSetList("https://www.youtube.com/embed/Z6Nds10n7rs");
        arrayList.add(dataSetList);
        dataSetList = new DataSetList("https://www.youtube.com/embed/aLUY-s7LSns");
        arrayList.add(dataSetList);
        dataSetList = new DataSetList("https://www.youtube.com/embed/RK3P9L2ZXk4");
        arrayList.add(dataSetList);
        dataSetList = new DataSetList("https://www.youtube.com/embed/NO7Gt8IDSGA");
        arrayList.add(dataSetList);
        dataSetList = new DataSetList("https://www.youtube.com/embed/9O7OJi7RCLQ");
        arrayList.add(dataSetList);
        dataSetList = new DataSetList("https://www.youtube.com/embed/FKvnmxte98A");
        arrayList.add(dataSetList);
        YoutubeAdapter youtubeAdapter = new YoutubeAdapter(arrayList,getApplicationContext());
        recyclerView.setAdapter(youtubeAdapter);
    }

}