package com.planb.porest.support.activities;

import android.app.Activity;
import android.os.Bundle;

import com.planb.porest.R;

public class Splash extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
    }
}
