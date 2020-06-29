package com.nullexcom.uikit;

import android.content.Context;
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

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

class UIKit {
    static Drawable round(Context context, @NonNull Drawable drawable, float[] radii) {
        drawable.mutate();
        if (drawable instanceof GradientDrawable) {
            GradientDrawable newDrawable = (GradientDrawable) drawable;
            newDrawable.setCornerRadii(radii);
            return newDrawable;
        } else if (drawable instanceof ShapeDrawable) {
            ShapeDrawable newDrawable = (ShapeDrawable) drawable;
            Shape shape = newDrawable.getShape();
            if (shape instanceof RectShape) {
                RoundRectShape roundRectShape = new RoundRectShape(radii, null, null);
                newDrawable.setShape(roundRectShape);
                return newDrawable;
            }
        } else if (drawable instanceof ColorDrawable) {
            ColorDrawable newDrawable = (ColorDrawable) drawable;
            int color = newDrawable.getColor();
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(color);
            gradientDrawable.setCornerRadii(radii);
            return newDrawable;
        } else if (drawable instanceof VectorDrawable) {
            VectorDrawable newDrawable = (VectorDrawable) drawable;
            Bitmap bitmap = getBitmapFromVectorDrawable(newDrawable);
            RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), bitmap);
            roundedBitmapDrawable.setCornerRadius(radii[0]);
            return roundedBitmapDrawable;
        } else if (drawable instanceof BitmapDrawable) {
            BitmapDrawable newDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = newDrawable.getBitmap();
            RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), bitmap);
            roundedBitmapDrawable.setCornerRadius(radii[0]);
            return roundedBitmapDrawable;
        }

        return drawable;
    }

    private static Bitmap getBitmapFromVectorDrawable(VectorDrawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
