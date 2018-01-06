package com.victor.test.videoviewlibrary.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by victor on 26/7/17.
 * Mshop Spain.
 */

public class MyUtils {
    public static void setLog(Object source, String content) {
        Log.i("Delivery", source.getClass().getSimpleName() + " - " + content);
    }

    public static int getDpFromValue(Context context, int value) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics()));
    }

    public static String convertImageToEncodedString(Bitmap imageBitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    public static Date getDateFromString(String dateString) {
        Date convertedDate = null;
        DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        dateFormatter.setLenient(false);

        try {
            convertedDate = dateFormatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return convertedDate;
    }
}
