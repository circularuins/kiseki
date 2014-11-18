package com.circularuins.miracledoor;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class KisekiActivity extends Activity {
    private boolean bgm = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_kiseki);
        setContentView(R.layout.activity_snow);

        Button btnBGM = (Button)findViewById(R.id.btnBGM);
        Button btnCredit = (Button)findViewById(R.id.btnCredit);
        final SnowView view = (SnowView)findViewById(R.id.snowView);

        //ボタンを半透明に
        btnBGM.setAlpha(0.3f);
        btnCredit.setAlpha(0.3f);

        //BGMのON/OFF
        btnBGM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bgm == true) {
                    view.stopBGM();
                    bgm = false;
                } else {
                    view.startBGM();
                    bgm = true;
                }
            }
        });

        //字幕のクリア
        btnCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.clearCredit();
            }
        });
    }
}
