package com.circularuins.miracledoor;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by wake on 2014/11/17.
 */
public class Credits implements Runnable {
    private float drawX, drawY;
    private float dx, dy;
    private int color;
    private float size;
    private float windowHeight;
    private String text;
    private Thread thread; //内部スレッド用
    private Paint p = new Paint();

    public Credits(float drawX, float drawY, float dx, float dy, int color, float size, String text, float windowHeight) {
        this.drawX = drawX;
        this.drawY = drawY;
        this.dx = dx;
        this.dy = dy;
        this.color = color;
        this.size = size;
        this.text = text;
        this.windowHeight = windowHeight;

        p.setColor(color);
        p.setTextSize(size);

        thread = new Thread(this);
        thread.start();
    }

    public void draw(Canvas canvas) {
        canvas.drawText(text, drawX, drawY, p);
    }

    public void run() {
        while (thread != null) {
            drawY += -dy;
            try {
                Thread.sleep(1000/60); //60msec毎の描画
            } catch (Exception e) {

            }
            //画面からはみ出した時の処理
            if(drawY < 0) drawY = windowHeight * 1.5f;
        }
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }
}
