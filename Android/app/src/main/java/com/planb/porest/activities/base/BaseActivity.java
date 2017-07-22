package com.planb.porest.activities.base;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by dsm2016 on 2017-07-22.
 */

public class BaseActivity extends AppCompatActivity {
    private final long FINISH_INTERVAL_TIME = 2000;
    private long lastBackPressedTime;

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();
        long interval = curTime - lastBackPressedTime;

        if(0 <= interval && FINISH_INTERVAL_TIME >= interval) {
            super.onBackPressed();
        } else {
            lastBackPressedTime = curTime;
            Toast.makeText(getApplicationContext(), "'뒤로' 버튼을 한번 더 누르면 종료합니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
