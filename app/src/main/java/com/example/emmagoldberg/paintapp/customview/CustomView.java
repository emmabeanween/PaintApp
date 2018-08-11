package com.example.emmagoldberg.paintapp.customview;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.emmagoldberg.paintapp.R;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class CustomView extends View {

    private float mStartX;
    private float mStartY;
    private float mEndX;
    private float mEndY;
    private boolean doClear = false;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    public Paint mPaint = new Paint();
    private Path mPath = new Path();
    int index = 0;



    //todo: try to download canvas(fail probs), make ui pretty(ish), round buttons


    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        // default style will be pencil - 10f (with pen being 5f and paintbrush being 20f)
        mPaint.setStrokeWidth(10f);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setErase() {
        // paint over color with white, thus erasing
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.GhostWhite));
        mPath.reset();
        invalidate();

    }


    public void setColor(int newColor) {

        mPaint.setColor(newColor);
        // after resetting the color, reset the path to reflect update in new color
        mPath.reset();
        invalidate();
    }


    public void setStrokeWidth(float width) {

        mPaint.setStrokeWidth(width);
        // after resetting the stroke width, reset the path to reflect update of new stroke width
        mPath.reset();
        invalidate();

    }


    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {


        switch (event.getAction())

        {
            case MotionEvent.ACTION_DOWN:
                mStartX = event.getX();
                mStartY = event.getY();
                mPath.moveTo(mStartX, mStartY);


                break;
            case MotionEvent.ACTION_MOVE:
                mEndX = event.getX();
                mEndY = event.getY();
                mPath.lineTo(mEndX, mEndY);


                invalidate();


                break;
            case MotionEvent.ACTION_UP:
                // keep line upon up
                mEndX = event.getX();
                mEndY = event.getY();
                break;

            // add to list


        }


        return true;


    }



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }


    public void clearCanvas() {

        doClear = true;
        mPath.reset();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i("color in ondraw", "paint color is " + Integer.toHexString(mPaint.getColor()));
        Log.i("TAG", "value of" + doClear);

        if (doClear == true) {

            mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            doClear = false;
        }

        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        mCanvas.drawPath(mPath, mPaint);


    }

    public void downloadCanvas() {






        
        try {
            String fileName = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    + "/untitled" + index + ".png";
            OutputStream stream = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.PNG, 80, stream);
            stream.close();
            Toast.makeText(getContext(), "canvas downloaded", Toast.LENGTH_SHORT).show();
            // increase index by one for next photo download so not overwritten

            index = index + 1;


        } catch (IOException mException) {

            mException.printStackTrace();


        }

    }

}