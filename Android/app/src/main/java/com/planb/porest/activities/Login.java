package com.planb.porest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.planb.porest.R;
import com.planb.porest.activities.base.BaseActivity;
import com.planb.porest.support.networking.Host;

import org.w3c.dom.Text;

import java.util.HashMap;

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
            public void onClick(final View v) {
                if(id.getText().toString().isEmpty() || pw.getText().toString().isEmpty()) {
                    makeSnackbar(v).show();
                } else {
                    HashMap<String, String> params = new HashMap<String, String>();

                    aq.ajax(Host.HOST + "/login", params, String.class, new AjaxCallback<String>() {
                        @Override
                        public void callback(String url, String object, AjaxStatus status) {
                            System.out.println(status.getCode());
                            if(status.getCode() == 200) {
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                makeSnackbar(v).show();
                            }
                        }
                    });
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
