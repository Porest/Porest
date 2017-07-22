package com.planb.porest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.planb.porest.R;
import com.planb.porest.activities.base.BaseActivity;
import com.planb.porest.support.db.DBHelper;

public class Splash extends BaseActivity {
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
        if(isLogined()) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            startActivity(new Intent(this, Login.class));
        }
    }

    private boolean isLogined() {
        DBHelper helper = DBHelper.getInstance(getApplicationContext(), "check.db", null, 1);
        if(helper.getId() == null) {
            return false;
        } else {
            return true;
        }
    }
}
