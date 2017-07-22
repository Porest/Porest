package com.planb.porest.activities;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;

import com.planb.porest.R;
import com.planb.porest.support.db.DBHelper;

public class Splash extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                judge();
            }
        }, 2000);
    }

    private void judge() {
        DBHelper helper = DBHelper.getInstance(getApplicationContext(), "check.db", null, 1);
    }

    private boolean isLogined(DBHelper helper) {
        if(helper.getId() == null) {
            return false;
        } else {
            return true;
        }
    }
}
