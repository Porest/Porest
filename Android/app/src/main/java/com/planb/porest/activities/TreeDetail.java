package com.planb.porest.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.planb.porest.R;
import com.planb.porest.activities.account.Login;
import com.planb.porest.activities.base.BaseActivity;
import com.planb.porest.dialogs.MakeLeaf;
import com.planb.porest.support.networking.Host;
import com.planb.porest.support.vo.Leaf;
import com.planb.porest.support.vo.Tree;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by dsm2016 on 2017-07-23.
 */

public class TreeDetail extends BaseActivity {
    private ImageButton backBtn;
    private TextView titleView;
    private TextView leaf;
    private TextView like;
    private RecyclerView recyclerView;
    private ImageView fab;
    private AQuery aq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tree_detail);

        backBtn = (ImageButton) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        final Tree tree = Tree.focusTree;

        titleView = (TextView) findViewById(R.id.titleView);
        titleView.setText(tree.name);

        leaf = (TextView) findViewById(R.id.leaf);
        leaf.setText(tree.currentLeaves + " / " + tree.maxLeaves);
        leaf.setTextColor(Color.rgb(63, 155, 10));

        like = (TextView) findViewById(R.id.like);
        like.setText("좋아요" + tree.likeCount);
        leaf.setTextColor(Color.rgb(55, 137, 8));

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fab = (ImageView) findViewById(R.id.fab);
        aq = new AQuery(getApplicationContext());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MakeLeaf dialog = new MakeLeaf(TreeDetail.this);
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        setDatas(tree.index);
                    }
                });
                dialog.show();
            }
        });


        setDatas(tree.index);
    }

    private void setDatas(int treeIndex) {
        aq.ajax(Host.HOST + "/post?tree_idx=" + treeIndex, String.class, new AjaxCallback<String>() {
            @Override
            public void callback(String url, String response, AjaxStatus status) {
                ArrayList<Leaf> leafList = new ArrayList<>();

                try {
                    JSONArray arr = new JSONArray(response);
                    for(int i = 0; i < arr.length(); i++) {
                        JSONObject leafObj = arr.getJSONObject(i);
                        Leaf leaf = new Leaf(leafObj.getInt("tree_idx"), leafObj.getString("contant"));

                        leafList.add(leaf);
                    }

                    recyclerView.setAdapter(new LeafAdapter(leafList));
                } catch(JSONException e) {

                }
            }
        }.method(AQuery.METHOD_GET));
    }
}

class LeafAdapter extends RecyclerView.Adapter<LeafAdapter.ViewHolder> {
    ArrayList<Leaf> leaf;

    public LeafAdapter(ArrayList<Leaf> leaf){
        this.leaf = leaf;
    }

    // 새로운 뷰 홀더 생성
    @Override
    public LeafAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_leaf, parent, false);
        return new ViewHolder(view);
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.guideView.setText((position + 1) + "번째 잎사귀");
        holder.contentView.setText(leaf.get(position).content);
    }

    // 데이터 셋의 크기를 리턴해줍니다.
    @Override
    public int getItemCount() {
        return leaf.size();
    }

    // 커스텀 뷰홀더
    // item layout 에 존재하는 위젯들을 바인딩합니다.
    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView guideView;
        private TextView contentView;

        public ViewHolder(final View itemView) {
            super(itemView);

            guideView = (TextView) itemView.findViewById(R.id.guideView);
            contentView = (TextView) itemView.findViewById(R.id.contentView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = itemView.getContext();
                    Intent intent = new Intent(context, TreeDetail.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}

