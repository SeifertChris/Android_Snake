package com.example.android.snake;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by chris on 2/26/2017.
 */

public class Sprite {

    int x , y;
    int xSpeed,ySpeed;
    int height, width;
    Bitmap snakeSprite;
    SplashActivity.OurView ov;
    int currentFrame = 0;
    int direction;


    public Sprite(SplashActivity.OurView ourView, Bitmap spriteSheet){

        snakeSprite = spriteSheet;
        ov = ourView;
        height = snakeSprite.getHeight();
        width = snakeSprite.getWidth() / 4;
        x = 0; // x axis
        y = 0;// y axis
        xSpeed =5;
        ySpeed = 0;
    }

    private void update(){
        // row 0 =
        if (x > ov.getWidth() - width - xSpeed){
            x =0;
            xSpeed = 5;
            direction = 0;
        }

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        currentFrame = ++ currentFrame % 4;

            x = x + xSpeed;
            y = y + ySpeed;

    }

    public void onDraw (Canvas canvas){
        update();
        int srcX = currentFrame * width;
        Rect src = new Rect(srcX,0, srcX + width, height); // the actual bitmap image we are cutting
        Rect dst = new Rect(x,y, x + width, y + height); // where it should be painted on in terms of screen also scales images be careful

        canvas.drawBitmap(snakeSprite,src,dst,null);

    }
}
