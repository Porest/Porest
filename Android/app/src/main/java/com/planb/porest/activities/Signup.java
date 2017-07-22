package com.planb.porest.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.planb.porest.R;
import com.planb.porest.activities.base.BaseActivity;
import com.planb.porest.support.view.SnackbarManager;

/**
 * Created by dsm2016 on 2017-07-22.
 */

public class Signup extends BaseActivity {
    private Button backBtn;

    private EditText nickname;
    private EditText id;
    private EditText pw;
    private EditText pwConfirm;
    private Button signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        nickname = (EditText) findViewById(R.id.nickname);
        id = (EditText) findViewById(R.id.id);
        pw = (EditText) findViewById(R.id.pw);
        pwConfirm = (EditText) findViewById(R.id.pwConfirm);
        signupBtn = (Button) findViewById(R.id.signupBtn);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(nickname.getText().toString().isEmpty() || id.getText().toString().isEmpty() || pw.getText().toString().isEmpty() || pwConfirm.getText().toString().isEmpty()) {
                    SnackbarManager.make(v, "입력이 올바르지 않습니다.").show();
                } else {

                }
            }
        });
    }
}
