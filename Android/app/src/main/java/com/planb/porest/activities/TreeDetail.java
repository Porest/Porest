package com.planb.porest.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.planb.porest.R;
import com.planb.porest.activities.base.BaseActivity;
import com.planb.porest.support.vo.Tree;

/**
 * Created by dsm2016 on 2017-07-23.
 */

public class TreeDetail extends BaseActivity {
    private TextView titleView;
    private TextView leaf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tree_detail);

        titleView = (TextView) findViewById(R.id.titleView);
        leaf = (TextView) findViewById(R.id.leaf);

        Tree tree = Tree.focusTree;
    }
}
