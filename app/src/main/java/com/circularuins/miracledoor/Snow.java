package com.circularuins.miracledoor;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by wake on 2014/11/15.
 */
public class Snow {
    protected float drawX, drawY;
    protected float dx, dy;
    protected int color;
    protected float radius;
    protected float ax, ay; //瞬間の傾き加速度

    public Snow(float drawX, float drawY, float dx, float dy, int color, float radius) {
        this.drawX = drawX;
        this.drawY = drawY;
        this.dx = dx;
        this.dy = dy;
        this.color = color;
        this.radius = radius;
    }

    public void draw(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(color);
        canvas.drawCircle(drawX, drawY, radius, p);
    }

    public float getDrawX() {
        return drawX;
    }

    public float getDrawY() {
        return drawY;
    }

    public float getDx() {
        return dx;
    }

    public float getDy() {
        return dy;
    }

    public int getColor() {
        return color;
    }

    public float getRadius() {
        return radius;
    }

    public void setDrawX(float drawX) {
        this.drawX = drawX;
    }

    public void setDrawY(float drawY) {
        this.drawY = drawY;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getAx() {
        return ax;
    }

    public float getAy() {
        return ay;
    }

    public void setAx(float ax) {
        this.ax = ax;
    }

    public void setAy(float ay) {
        this.ay = ay;
    }
}
