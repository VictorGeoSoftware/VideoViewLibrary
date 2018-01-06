package com.victor.test.videoviewlibrary;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.victor.test.videoviewlibrary.views.CustomVideoView;
import com.victor.test.videoviewlibrary.views.VideoProgressBar;

public class MainActivity extends AppCompatActivity implements  CustomVideoView.VideoListener {
    private CustomVideoView customVideoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customVideoView = findViewById(R.id.customVideoView);

//        String vr1 = "https://fpdl.vimeocdn.com/vimeo-prod-skyfire-std-us/01/4802/8/224011526/784502090.mp4?token=1515278165-0xfa0d753f925e0a58071ed5ac16da8bb6e7b111a5";
//        String videoResource = "vnd.youtube://" + "C7caKdREkiM";
        String uriPath = "android.resource://" + getPackageName() + "/" + R.raw.local_demo;
        customVideoView.setVideoListener(this);
        customVideoView.setUpVideoResource(uriPath);


    }

    @Override
    public void onVideoResourceReady(MediaPlayer mediaPlayer) {
        customVideoView.playVideo();
    }

    @Override
    public void onVideoStart(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onVideoStop(MediaPlayer mediaPlayer) {

    }
}
