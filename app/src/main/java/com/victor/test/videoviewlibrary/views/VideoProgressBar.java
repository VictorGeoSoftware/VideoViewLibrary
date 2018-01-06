package com.victor.test.videoviewlibrary.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.victor.test.videoviewlibrary.R;
import com.victor.test.videoviewlibrary.utils.MyUtils;

/**
 * Created by victorpalmacarrasco on 6/1/18.
 * ${APP_NAME}
 */

public class VideoProgressBar extends FrameLayout implements DragableButton.ButtonPositionListener {
    private View barView;
    private DragableButton button;
    private ButtonChangedListener buttonChangedListener;


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
    // ------------------------------------------------------------ VIEW INTERFACE ----------------------------------------------------------
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        MyUtils.setLog(this, "onLayout " + barView.getX() + " | " + (barView.getX() + barView.getWidth()));
        button.configureWithProgressBar(barView.getX(), barView.getX() + barView.getWidth());
    }



    // --------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------ BUTTON INTERFACE --------------------------------------------------------
    @Override
    public void onButtonPositionChanged(float xCoordinate) {
        int percentage = getButtonPercentageByPosition(xCoordinate);

        if (buttonChangedListener != null) {
            buttonChangedListener.onButtonBarPositionChanged(percentage);
        }
    }



    // --------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------ METHODS -----------------------------------------------------------------
    private void buildWidget(Context context) {

        // ------ 1º Progress bar building
        int viewHeight = MyUtils.getDpFromValue(context, 5);
        barView = new View(context);
        LayoutParams viewLP = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, viewHeight);
        viewLP.gravity = Gravity.CENTER;
        barView.setLayoutParams(viewLP);
        barView.setBackground(ContextCompat.getDrawable(context, R.drawable.progress_bar_background));


        // ------ 2º Dragable button building
        button = new DragableButton(context);
        button.setButtonPositionListener(this);


        // ------ 3º Add views to parent layout
        this.addView(barView);
        this.addView(button);
    }

    public int getButtonPercentageByPosition(float xPosition) {
        int buttonWidth = button.getWidth();
        int barWidth = barView.getWidth() - buttonWidth; // Its necessary to Remove button width for optimal calculation

        return (int) (xPosition * 100 / barWidth);
    }

    public void setButtonBarByPercentage(int percentage) {
        int buttonWidth = button.getWidth();
        int barWidth = barView.getWidth() - buttonWidth;
        MyUtils.setLog(this, "barWidth :: " + barWidth);
        int positionToSet = (int) (barView.getX() + barWidth * percentage / 100);
        MyUtils.setLog(this, "positionToSet :: " + positionToSet);
        button.setX(positionToSet);

        if (buttonChangedListener != null) {
            buttonChangedListener.onButtonBarPositionChanged(percentage);
        }
    }

    public void setButtonChangedListener(ButtonChangedListener buttonChangedListener) {
        this.buttonChangedListener = buttonChangedListener;
    }


    // --------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------ METHODS -----------------------------------------------------------------
    public interface ButtonChangedListener {
        void onButtonBarPositionChanged(int barPercentage);
    }
}
