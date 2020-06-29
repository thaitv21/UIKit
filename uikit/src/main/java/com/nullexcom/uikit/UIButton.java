package com.nullexcom.uikit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.VectorDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

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
//        background.mutate();
//        if (background instanceof GradientDrawable) {
//            GradientDrawable drawable = (GradientDrawable) background;
//            drawable.setCornerRadii(radii);
//            setBackground(drawable);
//        } else if (background instanceof ShapeDrawable) {
//            ShapeDrawable drawable = (ShapeDrawable) background;
//            Shape shape = drawable.getShape();
//            if (shape instanceof RectShape) {
//                RoundRectShape roundRectShape = new RoundRectShape(radii, null, null);
//                drawable.setShape(roundRectShape);
//                setBackground(drawable);
//            }
//        } else if (background instanceof ColorDrawable) {
//            ColorDrawable drawable = (ColorDrawable) background;
//            int color = drawable.getColor();
//            GradientDrawable gradientDrawable = new GradientDrawable();
//            gradientDrawable.setColor(color);
//            gradientDrawable.setCornerRadii(radii);
//            setBackground(gradientDrawable);
//        } else if (background instanceof VectorDrawable) {
//            VectorDrawable drawable = (VectorDrawable) background;
//            Bitmap bitmap = getBitmapFromVectorDrawable(drawable);
//            RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
//            roundedBitmapDrawable.setCornerRadius(radius);
//            setBackground(roundedBitmapDrawable);
//        } else if (background instanceof BitmapDrawable) {
//            BitmapDrawable drawable = (BitmapDrawable) background;
//            Bitmap bitmap = drawable.getBitmap();
//            RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
//            roundedBitmapDrawable.setCornerRadius(radius);
//            setBackground(roundedBitmapDrawable);
//        }
    }

    public static Bitmap getBitmapFromVectorDrawable(VectorDrawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
