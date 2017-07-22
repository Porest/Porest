package com.planb.porest.activities.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.planb.porest.R;
import com.planb.porest.activities.base.BaseActivity;
import com.planb.porest.support.networking.Host;
import com.planb.porest.support.view.SnackbarManager;

import java.util.HashMap;

/**
 * Created by dsm2016 on 2017-07-22.
 */

public class Signup extends BaseActivity {
    private ImageButton backBtn;

    private EditText nickname;
    private EditText id;
    private EditText pw;
    private EditText pwConfirm;
    private Button signupBtn;

    private AQuery aq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        backBtn = (ImageButton) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

        nickname = (EditText) findViewById(R.id.nickname);
        id = (EditText) findViewById(R.id.id);
        pw = (EditText) findViewById(R.id.pw);
        pwConfirm = (EditText) findViewById(R.id.pwConfirm);
        signupBtn = (Button) findViewById(R.id.signupBtn);
        aq = new AQuery(getApplicationContext());

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(nickname.getText().toString().isEmpty() || id.getText().toString().isEmpty() || pw.getText().toString().isEmpty() || pwConfirm.getText().toString().isEmpty()) {
                    SnackbarManager.make(v, "입력이 올바르지 않습니다.").show();
                } else {
                    if(!pw.getText().toString().equals(pwConfirm.getText().toString())) {
                        SnackbarManager.make(v, "비밀번호를 확인하세요.").show();
                    } else {
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("id", id.getText().toString());
                        params.put("password", pw.getText().toString());
                        params.put("nickname", nickname.getText().toString());
                        params.put("registration_id", "hello");

                        aq.ajax(Host.HOST + "/register", params, String.class, new AjaxCallback<String>() {
                            @Override
                            public void callback(String url, String object, AjaxStatus status) {
                                System.out.println(status.getCode());
                                if(status.getCode() == 200) {
                                    SnackbarManager.make(v, "회원가입 성공!").show();
                                    startActivity(new Intent(getApplicationContext(), Login.class));
                                } else {
                                    SnackbarManager.make(v, "이미 가입된 아이디입니다.").show();
                                }
                            }
                        });
                    }
                }
            }
        });
    }
}
