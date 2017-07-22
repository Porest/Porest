package com.planb.porest.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.planb.porest.R;
import com.planb.porest.activities.base.BaseActivity;
import com.planb.porest.dialogs.MakeTree;
import com.planb.porest.support.db.DBHelper;
import com.planb.porest.support.networking.Host;
import com.planb.porest.support.vo.Tree;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MakeTree dialog = new MakeTree(MainActivity.this);
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        setDatas();
                    }
                });
                dialog.show();
            }
        });

        setDatas();
    }

    private void setDatas() {
        DBHelper helper = DBHelper.getInstance(getApplicationContext(), "check.db", null, 1);
        String id = helper.getId();

        aq.ajax(Host.HOST + "/tree?id=" + id, String.class, new AjaxCallback<String>() {
            @Override
            public void callback(String url, String response, AjaxStatus status) {
                ArrayList<Tree> treeList = new ArrayList<>();

                try {
                    JSONArray arr = new JSONArray(response);
                    for(int i = 0; i < arr.length(); i++) {
                        JSONObject treeObj = arr.getJSONObject(i);

                        int index = treeObj.getInt("tree_idx");
                        String treeName = treeObj.getString("tree_name");
                        int currentLeaves = treeObj.getInt("current_leaves");
                        int maxLeaves = treeObj.getInt("maximum_leaves");
                        String creationTime = treeObj.getString("creation_time");
                        boolean isShared = treeObj.getInt("is_shared") == 1 ? true : false;
                        int like = treeObj.getInt("like_count");
                        Tree tree = new Tree(
                                index,
                                treeName,
                                currentLeaves,
                                maxLeaves,
                                creationTime,
                                isShared,
                                like
                        );
                        treeList.add(tree);
                    }

                    recyclerView.setAdapter(new RecyclerAdapter(treeList));
                } catch(JSONException e) {

                }
            }
        }.method(AQuery.METHOD_GET));
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
        Random random = new Random();
        switch(tree.get(position).index % 5) {
            case 0:
                holder.image.setImageResource(R.drawable.tree);
                break;
            case 1:
                holder.image.setImageResource(R.drawable.tree2);
                break;
            case 2:
                holder.image.setImageResource(R.drawable.tree3);
                break;
            case 3:
                holder.image.setImageResource(R.drawable.tree4);
                break;
            case 4:
                holder.image.setImageResource(R.drawable.tree5);
                break;
        }
        holder.name.setText(tree.get(position).name);

        holder.leaf.setText("목표치 " + tree.get(position).maxLeaves + "개 중 " + tree.get(position).currentLeaves + "개 잎사귀");
        holder.leaf.setTextColor(Color.rgb(63, 155, 10));

        holder.date.setText(tree.get(position).date.substring(0, tree.get(position).date.length() - 5).replace("T", " ") + "에 생성된 나무");
        holder.date.setTextColor(Color.rgb(55, 137, 8));

        if(tree.get(position).isShared) {
            holder.shared.setText("숲에 공유된 나무");
            holder.shared.setTextColor(Color.rgb(15, 40, 250));
        } else {
            holder.shared.setText("숲에 공유되지 않은 나무");
            holder.shared.setTextColor(Color.rgb(237, 143, 21));
        }
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
        private TextView name;
        private TextView leaf;
        private TextView date;
        private TextView shared;

        public ViewHolder(final View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.name);
            leaf = (TextView) itemView.findViewById(R.id.leaf);
            date = (TextView) itemView.findViewById(R.id.date);
            shared = (TextView) itemView.findViewById(R.id.shared);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Tree.focusTree = tree.get(getLayoutPosition());
                    Context context = itemView.getContext();
                    Intent intent = new Intent(context, TreeDetail.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}
