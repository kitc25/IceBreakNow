package com.purplecorn.icebreaknow.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.purplecorn.icebreaknow.R;

/**
 * TODO: document your custom view class.
 */
public class ColorSquareImageView extends ImageView {

    public ColorSquareImageView(Context context) {
        super(context);
        if(isInEditMode())
            return;
    }

    public ColorSquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if(isInEditMode())
            return;
    }

    public ColorSquareImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if(isInEditMode())
            return;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
    }
}
