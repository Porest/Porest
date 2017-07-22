package com.planb.porest.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.planb.porest.R;

/**
 * Created by dsm2016 on 2017-07-23.
 */

public class MakeTree extends Dialog {
    public MakeTree(Context context) {
        super(context);
    }

    private Button cancelBtn;
    private Button confirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_make_tree);

        cancelBtn = (Button) findViewById(R.id.cancelBtn);
        confirmBtn = (Button) findViewById(R.id.confirmBtn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
