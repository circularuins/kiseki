package com.circularuins.miracledoor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by wake on 2014/11/13.
 */
public class SnowView extends SurfaceView implements SurfaceHolder.Callback, Runnable, SensorEventListener {
    private MediaPlayer mp;
    private ScheduledExecutorService service;
    private SurfaceHolder holder;
    private Thread snowThread; //雪アニメーションのスレッド
    private ArrayList<Snow> snows = new ArrayList<Snow>(); //雪配列
    private TextView pressText = null;
    private Handler hdlr = new Handler(); //スレッド通信のためのハンドラ
    private SensorManager manager;
    private float[] a_value;
    private ArrayList<Credits> credits; //字幕配列
    private ArrayList<Text> texts = new ArrayList<Text>();


    public SnowView(Context context) {
        super(context);
        initial();
    }

    public SnowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initial();
    }

    public SnowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initial();
    }

    //初期化関数
    private void initial() {
        holder = this.getHolder();
        holder.addCallback(this);

        //加速度センサー利用準備
        manager = (SensorManager)((Activity)getContext()).getSystemService(Activity.SENSOR_SERVICE);
        List<Sensor> a_sensors = manager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        manager.registerListener(SnowView.this, a_sensors.get(0), SensorManager.SENSOR_DELAY_FASTEST);

        //字幕用テキストの初期化
        texts.add(new Text("大好き", Color.rgb(255, 20, 147), 60)); //deeppink
        texts.add(new Text("好き", Color.rgb(255,192,203), 50)); //pink
        int white = Color.argb(195, 255, 255, 255);
        texts.add(new Text("ひとりぼっち", white, 30));
        texts.add(new Text("信じてる", white, 30));
        texts.add(new Text("気づいて欲しい", white, 30));
        texts.add(new Text("沈黙", white, 30));
        texts.add(new Text("いっしょにいたい", white, 30));
        texts.add(new Text("優しい言葉", white, 30));
        texts.add(new Text("好意", white, 30));
        texts.add(new Text("思い上がり", white, 30));
        texts.add(new Text("友達になって欲しい", white, 30));
        texts.add(new Text("嫌われたくない", white, 30));
        texts.add(new Text("見つめて欲しい", white, 30));
        texts.add(new Text("気になるの", white, 30));
        texts.add(new Text("眠れない", white, 30));
        texts.add(new Text("不安", white, 30));
        texts.add(new Text("思い過ごし？", white, 30));
        texts.add(new Text("夢", white, 30));
        texts.add(new Text("涙", white, 30));
        texts.add(new Text("好きになって欲しい", white, 30));
        texts.add(new Text("悲しいこと", white, 30));
        texts.add(new Text("話をしたい", white, 30));
        texts.add(new Text("素直な気持ち", white, 30));
    }

    //アニメーション描画関数
    public void draw(SurfaceHolder holder) {
        Canvas canvas = holder.lockCanvas();
        canvas.drawColor(Color.BLACK); //Canvasの背景色

        //雪配列の描画
        for (int i = 0; i < snows.size(); i++) {
            snows.get(i).draw(canvas);
        }
        //字幕配列の描画
        for (int i = 0; i < credits.size(); i++) {
            credits.get(i).draw(canvas);
        }

        //Log.v("TEST", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        holder.unlockCanvasAndPost(canvas);
    }

    public void startExecutor() {
        service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < snows.size(); i++) {
                    //各雪の座標と速度の取得
                    float drawX = snows.get(i).getDrawX();
                    float drawY = snows.get(i).getDrawY();
                    float dx = snows.get(i).getDx();
                    float dy = snows.get(i).getDy();
                    //描画範囲内での動き
                    if (drawX >= getWidth()) drawX = 0; //幅オーバー時
                    if (drawY >= getHeight()) drawY = 0; //高さオーバー時
                    if (drawX < 0) drawX = getWidth(); //幅マイナス時
                    drawX += dx; //Xを進める
                    drawY += dy; //yを進める
                    //各雪の座標と速度のセット
                    snows.get(i).setDrawX(drawX);
                    snows.get(i).setDrawY(drawY);
                    snows.get(i).setDx(dx);
                    snows.get(i).setDy(dy);
                }
                draw(getHolder());
            }
        }, 1000/60, 1000/60, TimeUnit.MILLISECONDS);
    }

    //ビューが表示された時に呼ばれる
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //雪の初期化
        float x, y, dx, dy, r;
        credits = new ArrayList<Credits>();
        Random rnd = new Random();
        for (int i = 0; i < 200; i++) {
            x = (float)rnd.nextInt(getWidth()); //x座標をランダム設定
            y = (float)rnd.nextInt(getHeight()); //y座標をランダム設定
            dx = (float)(rnd.nextInt(4) - rnd.nextInt(4)); //x速度を-3~3でランダム設定
            dy = (float)(rnd.nextInt(5) + 1); //y速度を1~5でランダム設定
            r = (float)rnd.nextInt(6); //半径をランダム設定
            snows.add(new Snow(x, y, dx, dy, Color.WHITE, r));
        }

        start(); //開始
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        stop();
    }

    //タッチで字幕を生成
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() != MotionEvent.ACTION_DOWN)
            return false;

        Random rnd = new Random();
        int num = rnd.nextInt(texts.size());
        float drawX = event.getX() - 60; //テキストの描画開始x座標をずらす
        float drawY = event.getY();
        float dx = 0;
        float dy = (float)(rnd.nextInt(10) + 1); //y軸加速度の初期値1~5
        int color = texts.get(num).getColor();
        float size = 20 + rnd.nextFloat() * 30;
        String text = texts.get(num).getText();
        //字幕配列への追加
        credits.add(new Credits(drawX, drawY, dx, dy, color, size, text, getHeight()));
        //クレジット数の上限を超えたら最初のものから削除
        if(credits.size() > 45) {
            credits.get(0).setThread(null);
            credits.remove(0);
        }

        return super.onTouchEvent(event);
    }

    //加速度が変化した時
    @Override
    public void onSensorChanged(SensorEvent event) {
        a_value = event.values.clone();
        if(a_value != null) {
            Random rnd = new Random();
            //雪の加速度を変化させる
            for (int i = 0; i < snows.size(); i++) {
                //各雪の加速度の取得
                float dx = snows.get(i).getDx() - snows.get(i).getAx();
                float dy = snows.get(i).getDy() - snows.get(i).getAy();
                //雪の加速度に変化分を加える
                dx = -a_value[0] + dx; //X方向は感覚と逆になる
                dy = a_value[1] + dy;
                //各雪の加速度のセット
                snows.get(i).setDx(dx);
                snows.get(i).setDy(dy);
                snows.get(i).setAx(-a_value[0]);
                snows.get(i).setAy(a_value[1]);
            }
        }
    }

    //加速度センサーの精度が変更された時
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    //アニメーション・BGMの開始
    public void start() {
        //スレッドを作成し雪アニメーション開始
        snowThread = new Thread(this);
        snowThread.start();

        //『Press Start...』の点滅アニメーション
        pressText = (TextView)((Activity)getContext()).findViewById(R.id.pressText); //"Activity"のfindViewByIdを呼ぶ必要がある
        new Thread(new Runnable() {
            @Override
            public void run() {
                hdlr.post(new Runnable() {
                    @Override
                    public void run() {
                        if(pressText != null) {
                            //アニメーションの設定
                            Animation fade = AnimationUtils.loadAnimation(getContext(), R.anim.fade);
                            pressText.setAnimation(fade);
                        }

                        //『Press Start』テキストをクリックすると、物語画面へ切り替わる
                        pressText.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //BGMの停止
                                /*
                                画面切り替え時に停止するが、若干タイミングが遅いため、
                                タッチイベント時に明示的に停止させる。
                                */
                                mp.stop();

                                //物語画面への切り替え
                                Intent intent = new Intent(getContext(), GameActivity.class);
                                getContext().startActivity(intent);
                            }
                        });
                    }
                });
            }
        }).start();

        //BGMの再生
        mp = MediaPlayer.create(getContext(), R.raw.kiseki);
        mp.setLooping(true); //ループ再生
        mp.start();

        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }*/
    }

    //アニメーション・BGMの停止
    public void stop(){
        snowThread = null; //雪スレッド停止
        snows.clear(); //雪オブジェクトをクリア

        for (int i = 0; i < credits.size(); i++) {
            credits.get(i).setThread(null); //字幕スレッドを停止
            //credits.remove(i);
        }
        credits = null; //字幕オブジェクトをクリア

        service = null;

        mp.stop(); //BGM停止
    }

    //SnowActivityのrunメソッド
    public void run() {
        while (snowThread != null) {
            try {
                draw(holder);
                startExecutor();
            } catch (Exception e){
                e.printStackTrace();
            } finally {

            }
        }
    }
}
