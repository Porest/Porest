package com.planb.porest.activities;

import android.content.Context;
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
import com.planb.porest.support.db.DBHelper;
import com.planb.porest.support.networking.Host;
import com.planb.porest.support.vo.Tree;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ImageView fab;
    private AQuery aq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        layoutManager = new LinearLayoutManager(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        fab = (ImageView) findViewById(R.id.fab);
        aq = new AQuery(getApplicationContext());

        DBHelper helper = DBHelper.getInstance(getApplicationContext(), "check.db", null, 1);
        String id = helper.getId();
        aq.ajax(Host.HOST + "/tree?id=" + id, String.class, new AjaxCallback<String>() {
            @Override
            public void callback(String url, String response, AjaxStatus status) {
                ArrayList<Tree> tree = new ArrayList<>();

                try {
                    JSONArray arr = new JSONArray(response);
                    for(int i = 0; i < arr.length(); i++) {
                        JSONObject treeObj = arr.getJSONObject(i);
                    }

                    recyclerView.setAdapter(new RecyclerAdapter(tree));
                } catch(JSONException e) {

                }
            }
        });
    }
}

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    ArrayList<Tree> tree;

    public RecyclerAdapter(ArrayList<Tree> tree){
        this.tree = tree;
    }

    // 새로운 뷰 홀더 생성
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tree, parent, false);
        return new ViewHolder(view);
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(tree.get(position).title);
        holder.leaf.setText(tree.get(position).leaf);
        holder.date.setText(tree.get(position).date);
        holder.shared.setText(tree.get(position).shared);
    }

    // 데이터 셋의 크기를 리턴해줍니다.
    @Override
    public int getItemCount() {
        return tree.size();
    }

    // 커스텀 뷰홀더
// item layout 에 존재하는 위젯들을 바인딩합니다.
    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView title;
        private TextView leaf;
        private TextView date;
        private TextView shared;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
            leaf = (TextView) itemView.findViewById(R.id.leaf);
            date = (TextView) itemView.findViewById(R.id.date);
            shared = (TextView) itemView.findViewById(R.id.shared);
        }
    }
}
