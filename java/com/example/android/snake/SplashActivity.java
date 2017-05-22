package com.example.android.snake;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by chris on 2/23/2017.
 */

public class SplashActivity extends Activity {

    OurView v;
    Bitmap snakeSprite, rock1;
    Sprite mSprite;
    float x, y;
    int i = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = new OurView(SplashActivity.this);
        snakeSprite = BitmapFactory.decodeResource(getResources(), R.drawable.spritesheet_ver);
        Resources res = getResources();



        x = 0;
        y = 0;
        setContentView(v);


        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                finish();
                startActivity(new Intent(SplashActivity.this,UserNameAndDateActivity.class));
            }
        };

        Timer opening = new Timer();
        opening.schedule(timerTask,10000);

    }

    @Override
    protected void onPause() {
        super.onPause();
        v.pause();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        v.resume();
    }

    public class OurView extends SurfaceView implements Runnable {
        Thread t = null;
        SurfaceHolder mHolder;
        boolean isItOk = false;


        public OurView(Context context) {
            super(context);
            mHolder = getHolder();


        }

        @Override
        public void run() {

            mSprite = new Sprite(OurView.this, snakeSprite);

            while (isItOk == true) {
                if (!mHolder.getSurface().isValid()) {
                    continue;
                }


                Canvas c = mHolder.lockCanvas();
                onDraw(c);
                mHolder.unlockCanvasAndPost(c);

            }
        }

        final CountDownTimer mCountDownTimer = new CountDownTimer(7000,800) {
            @Override
            public void onTick(long l) {
                i++;
                if (i >= 4) {
                    i = 1;
                }
                if (i == 1) {
                    rock1 = BitmapFactory.decodeResource(getResources(), R.drawable.rock1);
                } else if (i == 2) {
                    rock1 = BitmapFactory.decodeResource(getResources(), R.drawable.rock2);

                } else if (i == 3) {
                    rock1 = BitmapFactory.decodeResource(getResources(), R.drawable.rock3);

                } else if (i == 4) {
                    rock1 = BitmapFactory.decodeResource(getResources(), R.drawable.rock4);
                }
            }

            @Override
            public void onFinish() {

            }
        }.start();


        protected void onDraw(Canvas canvas) {


            canvas.drawARGB(255, 255, 255, 255);
            canvas.drawBitmap(rock1, 50, 110, new Paint());

            mSprite.onDraw(canvas);

        }


        public void pause() { // handles if we get a phone call or something
            isItOk = false; // used in run() to see if we are in a running state
            while (true) { /* infinite loop to make sure that we are still in thread once we get a
             pause we set the thread back to null state after using join() to finish thread*/
                try {
                    t.join();// blocks current thread until receiver finishes
                } catch (InterruptedException e) {// catch usually just handles the error message
                    e.printStackTrace();
                }
                break;
            }
            t = null;

        }

        public void resume() {// handles resuming application after phone call
            isItOk = true; // used in run() to see if we are in a running state
            t = new Thread(this);// if we are able to run start thread * this is run() method*
            t.start();// starting thread
        }

    }

}


