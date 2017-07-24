package com.planb.porest.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.planb.porest.R;
import com.planb.porest.support.db.DBHelper;
import com.planb.porest.support.networking.Host;
import com.planb.porest.support.view.SnackbarManager;

import java.util.HashMap;

/**
 * Created by dsm2016 on 2017-07-23.
 */

public class MakeTree extends Dialog {
    private Button cancelBtn;
    private Button confirmBtn;
    private EditText treeTitle;
    private EditText leafLimit;
    private AQuery aq;
    private final int LEAF_LIMIT = 365;

    public MakeTree(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_make_tree);

        cancelBtn = (Button) findViewById(R.id.cancelBtn);
        confirmBtn = (Button) findViewById(R.id.confirmBtn);
        treeTitle = (EditText) findViewById(R.id.treeTitle);
        leafLimit = (EditText) findViewById(R.id.leafLimit);
        aq = new AQuery(getContext());

        leafLimit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().isEmpty()) {
                    if(Integer.parseInt(s.toString()) > LEAF_LIMIT) {
                        leafLimit.setText("365");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(treeTitle.getText().toString().isEmpty() || leafLimit.getText().toString().isEmpty()) {
                    SnackbarManager.make(v, "입력을 확인하세요.").show();
                } else {
                    DBHelper helper = DBHelper.getInstance(getContext(), "check.db", null, 1);

                    HashMap<String, String> params = new HashMap<>();
                    params.put("tree_name", treeTitle.getText().toString());
                    params.put("id", helper.getId());
                    params.put("maximum_leaves", leafLimit.getText().toString());
                    aq.ajax(Host.HOST + "/tree", params, String.class, new AjaxCallback<String>() {
                        @Override
                        public void callback(String url, String object, AjaxStatus status) {
                            if(status.getCode() == 200) {
                                dismiss();
                            }
                        }
                    });
                }
            }
        });
    }
}
