package com.victor.test.videoviewlibrary.views;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import com.victor.test.videoviewlibrary.R;
import com.victor.test.videoviewlibrary.utils.MyUtils;

/**
 * Created by victorpalmacarrasco on 6/1/18.
 * ${APP_NAME}
 */

public class CustomVideoView extends FrameLayout implements VideoProgressBar.ButtonChangedListener, MediaPlayer.OnPreparedListener {
    private VideoView videoView;
    private VideoProgressBar videoProgressBar;
    private MediaPlayer mediaPlayer;
    private VideoListener videoListener;



    // --------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------ CONSTRUCTORS ------------------------------------------------------------
    public CustomVideoView(@NonNull Context context) {
        super(context);
        buildWidget(context);
    }

    public CustomVideoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        buildWidget(context);
    }

    public CustomVideoView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        buildWidget(context);
    }



    // --------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------ VIDEO PROGRESS BAR LISTENER ---------------------------------------------
    @Override
    public void onButtonBarPositionChanged(int barPercentage) {
        // TODO :: poner el video en ese porcentaje de avance

        if (mediaPlayer != null) {
            int position = mediaPlayer.getDuration() * barPercentage / 100;
            videoView.seekTo(position);
        }
    }



    // --------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------ VIDEO SET UP LISTENER ---------------------------------------------------
    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
        videoProgressBar.setVisibility(View.VISIBLE);
        videoListener.onVideoResourceReady(this.mediaPlayer);
    }



    // --------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------ METHODS -----------------------------------------------------------------
    private void buildWidget(Context context) {

        // ------ 1º VideoView building
        videoView = new VideoView(context);
        LayoutParams videoViewLP = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        videoView.setLayoutParams(videoViewLP);

        // ------ 2º Children widgets building
        int marginSide = MyUtils.getDpFromValue(context, 50);
        int marginBottom = MyUtils.getDpFromValue(context, 50);
        videoProgressBar = new VideoProgressBar(context);
        LayoutParams videoProgressLP = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        videoProgressLP.gravity = Gravity.BOTTOM;
        videoProgressLP.setMargins(marginSide, 0, marginSide, marginBottom);
        videoProgressBar.setLayoutParams(videoProgressLP);

        videoProgressBar.setLayoutParams(videoProgressLP);
        videoProgressBar.setButtonChangedListener(this);

        // ------ 3º Add children wiews
        this.addView(videoView);
        this.addView(videoProgressBar);

        videoProgressBar.setVisibility(View.GONE);
    }

    public void setUpVideoResource(String uriPath) {
        Uri uri = Uri.parse(uriPath);

        try {
            videoView.setVideoURI(uri);

        } catch (Exception e) {
            e.printStackTrace();
        }

        videoView.setOnPreparedListener(this);
    }

    public void playVideo() {
        if (mediaPlayer != null) {
            mediaPlayer.start();

            if (videoListener != null) {
                videoListener.onVideoStart(mediaPlayer);
            }
        }
    }

    public void stopVideo() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();

            if (videoListener != null) {
                videoListener.onVideoStop(mediaPlayer);
            }
        }
    }

    public void pauseVideo() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public void setVideoListener(VideoListener videoListener) {
        this.videoListener = videoListener;
    }



    // --------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------ LISTENERS ---------------------------------------------------------------
    public interface VideoListener {
        void onVideoResourceReady(MediaPlayer mediaPlayer);
        void onVideoStart(MediaPlayer mediaPlayer);
        void onVideoStop(MediaPlayer mediaPlayer);
    }
}
