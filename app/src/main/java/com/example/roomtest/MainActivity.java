package com.example.roomtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.roomtest.database.AppDatabase;
import com.example.roomtest.database.RoomUser;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据editText中的数据，添加到数据库中。
 * 还是压迫看官方文档。
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mAddBtn;
    private Button mDeleteBtn;
    private Button mUpDataBtn;
    private RecyclerView mRecyclerView;
    private EditText mInputEditText;
    private MyAdapter mAdapter;
    private List<RoomUser> mRoomUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAddBtn = (Button) findViewById(R.id.add);
        mDeleteBtn = (Button) findViewById(R.id.delete);
        mUpDataBtn = (Button) findViewById(R.id.upadata);
        mInputEditText = (EditText) findViewById(R.id.inputEditText);

        mAddBtn.setOnClickListener(this);
        mDeleteBtn.setOnClickListener(this);
        mUpDataBtn.setOnClickListener(this);


        mRecyclerView = findViewById(R.id.recyclerView);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mRoomUsers = AppDatabase.getInstance(this).userDao().getAll();
        if (mRoomUsers == null) {
            mRoomUsers = new ArrayList<>();
        }
        mAdapter = new MyAdapter(mRoomUsers);
        mRecyclerView.setAdapter(mAdapter);

    }


    private String getText() {
        String trim = mInputEditText.getText().toString().trim();

        return TextUtils.isEmpty(trim) ? "" : trim;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                RoomUser user = new RoomUser();
                user.userId = getText();
                user.loginName = "沈浩波";
                mRoomUsers.remove(user);
                mRoomUsers.add(user);
                AppDatabase.getInstance(this).userDao().addUser(user);
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.delete:
                RoomUser idUser = new RoomUser();
                idUser.userId = getText();
                mRoomUsers.remove(idUser);
                AppDatabase.getInstance(this).userDao().deleteUser(idUser);
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.upadata:

                RoomUser userByUserId = AppDatabase.getInstance(this).userDao().findUserByUserId(getText());
                Log.i("TAG", "onClick: " + userByUserId.toString());
                break;
            default:
                break;
        }
    }


    private class MyAdapter extends RecyclerView.Adapter<ViewHolder> {
        private List<RoomUser> mList;

        public MyAdapter(List<RoomUser> stringList) {
            mList = stringList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mTv.setText(mList.get(position).userId + " - - " + mList.get(position).loginName);
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTv;

        public ViewHolder(View itemView) {
            super(itemView);
            mTv = itemView.findViewById(android.R.id.text1);
        }

    }
}
