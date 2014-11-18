package com.circularuins.miracledoor;

/**
 * Created by wake on 2014/11/12.
 */
public class Plot {
    private int imageId; //画像ID
    private String[] textArray; //セリフ文字列用の配列
    private String bgmName; //BGMファイル名

    public Plot() {
    }

    public Plot(int imageId, String[] textArray) {
        this.imageId = imageId;
        this.textArray = textArray;
        this.bgmName = "";
    }

    public int getImageId() {
        return imageId;
    }

    public String[] getTextArray() {
        return textArray;
    }

    public String getBgmName() {
        return bgmName;
    }

    public void setBgmName(String bgmName) {
        this.bgmName = bgmName;
    }

    /*
    public Plot(int imageId, String[] textArray, String bgmName) {
        this.imageId = imageId;
        this.textArray = textArray;
        this.bgmName = bgmName;
    }
*/
}
