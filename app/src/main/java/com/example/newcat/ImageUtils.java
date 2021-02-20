package com.example.newcat;


import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageUtils {
    DisplayMetrics dismetric;
    public void setImageSize (Activity activity, ImageView image) {
        DisplayMetrics dismetric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dismetric);
        image.setAdjustViewBounds(true);
        ViewGroup.LayoutParams layoutprams = image.getLayoutParams();
        layoutprams.width = dismetric.widthPixels;
        layoutprams.height = dismetric.heightPixels;
        image.setLayoutParams(layoutprams);
    }

    public void calculateDisplay(Activity activity) {
        dismetric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dismetric);
    }

    public int getDisplayWidth() {
        return dismetric.widthPixels;
    }

    public int getDisplayHeight() {
        return dismetric.heightPixels;
    }
}
