package com.nullexcom.uikit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class UIFrameLayout extends FrameLayout {

    private int radius;


    public UIFrameLayout(@NonNull Context context) {
        super(context);
    }

    public UIFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        obtainValues(context, attrs);
        init();
    }

    public UIFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainValues(context, attrs);
        init();
    }

    public UIFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        obtainValues(context, attrs);
        init();
    }

    private void obtainValues(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.UIFrameLayout);
        radius = ta.getDimensionPixelOffset(R.styleable.UIFrameLayout_android_radius, 0);
        ta.recycle();
    }

    private void init() {
        Drawable background = getBackground();
        if (background == null) {
            return;
        }
        float[] radii = new float[]{radius, radius, radius, radius, radius, radius, radius, radius};
        setBackground(UIKit.round(getContext(), background, radii));
    }
}
