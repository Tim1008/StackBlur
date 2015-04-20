package com.tim.imageblurring;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.tim.blur.FastBlur;

public class MainActivity extends ActionBarActivity {

    ImageView mBG;
    ImageView mBlur;
    TextView mTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTime = (TextView) findViewById(R.id.tv_time);
        mBG = (ImageView) findViewById(R.id.imag_bg);
        mBG.setImageResource(R.mipmap.picture);
        mBG.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mBG.getViewTreeObserver().removeOnPreDrawListener(this);
                mBG.buildDrawingCache();

                Bitmap mBitmap = mBG.getDrawingCache();
                startBlur(mBitmap, false);
                return true;
            }
        });
        mBlur = (ImageView) findViewById(R.id.imag_blur);
        mBlur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBlur.setBackgroundDrawable(null);
            }
        });
        findViewById(R.id.btn_nomal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBG.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        mBG.getViewTreeObserver().removeOnPreDrawListener(this);
                        mBG.buildDrawingCache();

                        Bitmap mBitmap = mBG.getDrawingCache();
                        startBlur(mBitmap, false);
                        return true;
                    }
                });
            }
        });
        findViewById(R.id.btn_compress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBG.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        mBG.getViewTreeObserver().removeOnPreDrawListener(this);
                        mBG.buildDrawingCache();

                        Bitmap mBitmap = mBG.getDrawingCache();
                        startBlur(mBitmap, true);
                        return true;
                    }
                });
            }
        });
    }

    void startBlur(Bitmap bitmap, boolean compress) {
        long startMs = System.currentTimeMillis();
        float scaleFactor = 1;
        float radius = 20;
        if (compress) {
            scaleFactor = 4;
            radius = 2;
        }
        Bitmap overlay = Bitmap.createBitmap((int) (mBlur.getMeasuredWidth() / scaleFactor), (int) (mBlur.getMeasuredHeight() / scaleFactor), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(overlay);
        canvas.translate(-mBlur.getLeft() / scaleFactor, -mBlur.getTop() / scaleFactor);
        canvas.scale(1 / scaleFactor, 1 / scaleFactor);
        Paint paint = new Paint();
        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        overlay = FastBlur.doBlurJniArray(overlay, (int) radius, true);
        mBlur.setBackgroundDrawable(new BitmapDrawable(overlay));
        Log.d(this.getClass().getSimpleName(), System.currentTimeMillis() - startMs + "ms");
        mTime.setText("模糊用时 : "+ (System.currentTimeMillis() - startMs) + "ms");
    }
}
