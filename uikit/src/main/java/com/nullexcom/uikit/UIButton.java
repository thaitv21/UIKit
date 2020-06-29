package com.nullexcom.uikit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.nullexcom.uikit.R;

public class UIButton extends androidx.appcompat.widget.AppCompatButton {

    private int radius;

    public UIButton(Context context) {
        super(context);
    }

    public UIButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        obtainValues(context, attrs);
        init();
    }

    public UIButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainValues(context, attrs);
        init();
    }

    private void obtainValues(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.UIButton);
        radius = ta.getDimensionPixelOffset(R.styleable.UIButton_android_radius, 0);
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
