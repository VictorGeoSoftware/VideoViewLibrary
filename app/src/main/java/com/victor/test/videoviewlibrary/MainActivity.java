package com.victor.test.videoviewlibrary;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.victor.test.videoviewlibrary.views.VideoProgressBar;

public class MainActivity extends AppCompatActivity {
    private VideoProgressBar videoProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoProgressBar = findViewById(R.id.videoProgressBar);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                videoProgressBar.setButtonBarByPercentage(40);
            }
        }, 1000);

    }
}
