package com.example.kotlindemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class TestViewJ extends View {
    Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path=new Path();
    private float radius=200f;
    public TestViewJ(Context context) {
        super(context);
    }

    public TestViewJ(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path,paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        path.addCircle(getWidth()/2f,getHeight()/2f,radius, Path.Direction.CW);
    }
}
