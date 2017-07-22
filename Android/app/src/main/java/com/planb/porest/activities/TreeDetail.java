package com.planb.porest.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.planb.porest.R;
import com.planb.porest.activities.base.BaseActivity;
import com.planb.porest.support.networking.Host;
import com.planb.porest.support.vo.Leaf;
import com.planb.porest.support.vo.Tree;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by dsm2016 on 2017-07-23.
 */

public class TreeDetail extends BaseActivity {
    private TextView titleView;
    private TextView leaf;
    private TextView like;
    private RecyclerView recyclerView;
    private AQuery aq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tree_detail);

        titleView = (TextView) findViewById(R.id.titleView);
        leaf = (TextView) findViewById(R.id.leaf);
        like = (TextView) findViewById(R.id.like);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        aq = new AQuery(getApplicationContext());

        Tree tree = Tree.focusTree;
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

