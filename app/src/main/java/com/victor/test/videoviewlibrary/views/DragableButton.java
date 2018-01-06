package com.victor.test.videoviewlibrary.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.victor.test.videoviewlibrary.R;
import com.victor.test.videoviewlibrary.utils.MyUtils;


/**
 * Created by victorpalmacarrasco on 6/1/18.
 * ${APP_NAME}
 */

public class DragableButton extends FrameLayout implements GestureDetector.OnGestureListener {
    private GestureDetectorCompat mDetector;
    private float scrolledDistanceX = 0;
    private float initialX = 0;
    private float finalX = 0;
    private ButtonPositionListener buttonPositionListener;



    // --------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------ CONSTRUCTORS ------------------------------------------------------------
    public DragableButton(@NonNull Context context) {
        super(context);
        buildWidget(context);
    }

    public DragableButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        buildWidget(context);
    }

    public DragableButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        buildWidget(context);
    }


    // --------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------ TOUCH INTERFACE ---------------------------------------------------------
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDetector.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;

            case MotionEvent.ACTION_MOVE:

                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                scrolledDistanceX = 0;

                if (buttonPositionListener != null) {
                    buttonPositionListener.onButtonPositionChanged(this.getX());
                }
                break;
        }

        return true;
    }



    // --------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------ GESTURE DETECTOR INTERFACE ----------------------------------------------
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        scrolledDistanceX = scrolledDistanceX + v;

        float xToSet = getX() - scrolledDistanceX;

        if (xToSet >= 0 && xToSet <= (finalX - this.getWidth())) {
            this.setX(xToSet);
        }

        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }



    // --------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------ METHODS -----------------------------------------------------------------
    public void setButtonPositionListener(ButtonPositionListener buttonPositionListener) {
        this.buttonPositionListener = buttonPositionListener;
    }

    private void buildWidget(Context context) {
        mDetector = new GestureDetectorCompat(context, this);
        int buttonDim = MyUtils.getDpFromValue(context, 30);
        LayoutParams buttonLP = new LayoutParams(buttonDim, buttonDim);
        this.setLayoutParams(buttonLP);
        this.setBackground(ContextCompat.getDrawable(context, R.drawable.button_background));
    }

    public void configureWithProgressBar(float initialX, float finalX) {
        MyUtils.setLog(this, "configureWithProgressBar - initial final :: " + initialX + " | " + finalX);
        this.initialX = initialX;
        this.finalX = finalX;
    }



    // --------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------ INTERFACES --------------------------------------------------------------
    public interface ButtonPositionListener {
        void onButtonPositionChanged(float xCoordinate);
    }
}
