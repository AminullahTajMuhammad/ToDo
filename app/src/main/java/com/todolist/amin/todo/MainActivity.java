package com.todolist.amin.todo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private static final String TAG = MainActivity.class.getSimpleName();
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    String catchEdt;
    Adapter adapter;
    String editData;

    ArrayList<String> restoredItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        floatingActionButton = findViewById(R.id.fabtn);
        ImageButton imgbutton = (ImageButton) findViewById(R.id.dlt_button);

//        imgbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = adapter.position;
//                adapter.removeItem(position);
//                adapter.notifyDataSetChanged();
//            }
//        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,write_text.class);
                startActivityForResult(intent,123); //1: make start actiity
            }
        });

        if(savedInstanceState != null){
            restoredItems = savedInstanceState.getStringArrayList("listData");
        }
        BackButtonInvisiable();
        setRecyclerViewList();

    }

    int clickedPosition = -1;

    void setRecyclerViewList() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new Adapter(restoredItems, this, new OnTapListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this,ShowItemTextView.class);
                intent.putExtra("Show_TextView", adapter.getItem(position));
                startActivityForResult(intent, 142);
                clickedPosition = position;
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 142) {
            if(resultCode == RESULT_OK) {
                catchEdt = data.getStringExtra("Edited_Data");
                adapter.updateItem(catchEdt,clickedPosition);
                adapter.notifyItemChanged(clickedPosition);
            }
        }
        else if(requestCode == 123) {
            if(resultCode == RESULT_OK) {
                catchEdt = data.getStringExtra("DATA");
                adapter.addItem(catchEdt);
                adapter.notifyItemInserted(adapter.getItemCount());
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.v(TAG, "onSaveInstanceState()");
        outState.putStringArrayList("listData", adapter.getItems());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        restoredItems = savedInstanceState.getStringArrayList("listData");
    }

    void BackButtonInvisiable() {
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setVisibility(View.GONE);
        //TextView txtAppName = findViewById(R.id.tv_Appname);
        //txtAppName.setVisibility(View.GONE);
    }
}