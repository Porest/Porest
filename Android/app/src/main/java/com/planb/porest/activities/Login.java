package com.planb.porest.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.planb.porest.R;
import com.planb.porest.activities.base.BaseActivity;

import org.w3c.dom.Text;

/**
 * Created by dsm2016 on 2017-07-22.
 */

public class Login extends BaseActivity {
    private EditText id;
    private EditText pw;
    private Button loginBtn;
    private TextView jumpToSignupView;
    private AQuery aq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        id = (EditText) findViewById(R.id.id);
        pw = (EditText) findViewById(R.id.pw);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        jumpToSignupView = (TextView) findViewById(R.id.jumpToSignup);
        aq = new AQuery(getApplicationContext());

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id.getText().toString().isEmpty() || pw.getText().toString().isEmpty()) {
                    makeSnackbar(v).show();
                }
            }
        });
    }

    private Snackbar makeSnackbar(View v) {
        return Snackbar.make(v, "아이디 또는 비밀번호를 확인하세요.", Snackbar.LENGTH_SHORT).setAction("확인", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.GONE);
            }
        });
    }
}
