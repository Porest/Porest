package com.planb.porest.activities;

import android.os.Bundle;

import com.planb.porest.R;
import com.planb.porest.activities.base.BaseActivity;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}
