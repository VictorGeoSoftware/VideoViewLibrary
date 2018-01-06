package com.victor.test.videoviewlibrary.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.victor.test.videoviewlibrary.R;
import com.victor.test.videoviewlibrary.utils.MyUtils;

/**
 * Created by victorpalmacarrasco on 6/1/18.
 * ${APP_NAME}
 */

public class VideoProgressBar extends FrameLayout implements GestureDetector.OnGestureListener {
    private View barView;
    private View button;
    private ButtonChangedListener buttonChangedListener;
    private GestureDetectorCompat mDetector;


    // --------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------ CONSTRUCTORS ------------------------------------------------------------
    public VideoProgressBar(@NonNull Context context) {
        super(context);
        buildWidget(context);
    }

    public VideoProgressBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        buildWidget(context);
    }

    public VideoProgressBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        buildWidget(context);
    }



    // --------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------ TOUCH EVENT INTERFACE ---------------------------------------------------
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                CONSUMIDO POR EL INTERCEPT TOUCH EVENT
                break;

            case MotionEvent.ACTION_MOVE:

                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:

                break;
        }

        return super.onTouchEvent(event);
    }



    // --------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------ GESTURE INTERFACE -------------------------------------------------------
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        MyUtils.setLog(this, "onDown");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        MyUtils.setLog(this, "onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        MyUtils.setLog(this, "onSingleTapUp");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        MyUtils.setLog(this, "onScroll");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        MyUtils.setLog(this, "onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        MyUtils.setLog(this, "onFling");
        return false;
    }






    // --------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------ METHODS -----------------------------------------------------------------
    private void buildWidget(Context context) {
        mDetector = new GestureDetectorCompat(context, this);

        // ------ 1º Progress bar building
        int viewHeight = MyUtils.getDpFromValue(context, 5);
        barView = new View(context);
        LayoutParams viewLP = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, viewHeight);
        viewLP.gravity = Gravity.CENTER;
        barView.setLayoutParams(viewLP);
        barView.setBackground(ContextCompat.getDrawable(context, R.drawable.progress_bar_background));


        // ------ 2º Dragable button building
        int buttonDim = MyUtils.getDpFromValue(context, 30);
        button = new View(context);
        LayoutParams buttonLP = new LayoutParams(buttonDim, buttonDim);
        button.setLayoutParams(buttonLP);
        button.setBackground(ContextCompat.getDrawable(context, R.drawable.button_background));


        // ------ 3º Add views to parent layout
        this.addView(barView);
        this.addView(button);
    }

    public void setButtonBarByPercentage(int percentage) {
        int barWidth = barView.getWidth();
        MyUtils.setLog(this, "barWidth :: " + barWidth);
        int positionToSet = (int) (barView.getX() + barWidth * percentage / 100);
        MyUtils.setLog(this, "positionToSet :: " + positionToSet);
        button.setX(positionToSet);

        if (buttonChangedListener != null) {
            buttonChangedListener.onButtonChanged(percentage);
        }
    }

    public void setButtonChangedListener(ButtonChangedListener buttonChangedListener) {
        this.buttonChangedListener = buttonChangedListener;
    }

    // TODO :: gesture para mover el boton sobre la barra



    public interface ButtonChangedListener {
        void onButtonChanged(int barPercentage);
    }
}
