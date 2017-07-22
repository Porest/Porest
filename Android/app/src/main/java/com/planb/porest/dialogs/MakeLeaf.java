package com.planb.porest.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
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
import com.planb.porest.support.vo.Tree;

import java.util.HashMap;

/**
 * Created by dsm2016 on 2017-07-23.
 */

public class MakeLeaf extends Dialog {
    private Button cancelBtn;
    private Button confirmBtn;
    private EditText leafContent;
    private AQuery aq;

    public MakeLeaf(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_make_leaf);

        cancelBtn = (Button) findViewById(R.id.cancelBtn);
        confirmBtn = (Button) findViewById(R.id.confirmBtn);
        leafContent = (EditText) findViewById(R.id.leafContent);
        aq = new AQuery(getContext());

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(leafContent.getText().toString().isEmpty()) {
                    SnackbarManager.make(v, "입력을 확인하세요.").show();
                } else {
                    HashMap<String, String> params = new HashMap<>();
                    params.put("contant", leafContent.getText().toString());
                    params.put("tree_idx", Integer.toString(Tree.focusTree.index));

                    aq.ajax(Host.HOST + "/post", params, String.class, new AjaxCallback<String>() {
                        @Override
                        public void callback(String url, String object, AjaxStatus status) {
                            System.out.println(status.getCode());
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
