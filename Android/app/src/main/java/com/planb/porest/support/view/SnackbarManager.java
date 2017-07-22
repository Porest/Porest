package com.planb.porest.support.view;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by dsm2016 on 2017-07-22.
 */

public class SnackbarManager {
    public static Snackbar make(View v, String msg) {
        return Snackbar.make(v, msg, Snackbar.LENGTH_SHORT).setAction("확인", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.GONE);
            }
        });
    }
}
