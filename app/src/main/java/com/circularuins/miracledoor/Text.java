package com.circularuins.miracledoor;

/**
 * Created by wake on 2014/11/18.
 */
public class Text {
    private String text; //テキスト文字列
    private int color; //テキストカラー
    private float size; //フォントサイズ

    public Text(String text, int color, float size) {
        this.text = text;
        this.color = color;
        this.size = size;
    }

    public String getText() {
        return text;
    }

    public int getColor() {
        return color;
    }

    public float getSize() {
        return size;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setSize(float size) {
        this.size = size;
    }
}
