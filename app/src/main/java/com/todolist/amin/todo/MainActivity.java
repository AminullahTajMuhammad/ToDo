package com.todolist.amin.todo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    String catchEdt;
    Adapter adapter;
    String editData;

    ArrayList<Task> restoredItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        floatingActionButton = findViewById(R.id.fabtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,write_text.class);
                startActivityForResult(intent,123); //1: make start actiity
            }
        });

        if(savedInstanceState != null){
            //rotation bug fix later
//            restoredItems = savedInstanceState.getStringArrayList("listData");
        }
        BackButtonInvisiable();
        setRecyclerViewList();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
                intent.putExtra("Show_TextView", adapter.getItem(position).getDesc());
                startActivityForResult(intent, 142);
                clickedPosition = position;
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // this is for edit task screen
        if(requestCode == 142) {
            if(resultCode == RESULT_OK) {
                catchEdt = data.getStringExtra("Edited_Data");
                adapter.updateItem(new Task(catchEdt, false),clickedPosition);
                adapter.notifyItemChanged(clickedPosition);
            }
        }

        //this is for floating action button screen or main screen
        else if(requestCode == 123) {
            if(resultCode == RESULT_OK) {
                catchEdt = data.getStringExtra("DATA");
                adapter.addItem(new Task(catchEdt, false));
                adapter.notifyItemInserted(adapter.getItemCount());
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.v(TAG, "onSaveInstanceState()");
//        outState.putStringArrayList("listData", adapter.getItems());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
//        restoredItems = savedInstanceState.getStringArrayList("listData");
    }

    void BackButtonInvisiable() {
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setVisibility(View.GONE);

        //TextView txtAppName = findViewById(R.id.tv_Appname);
        //txtAppName.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_search_icon,menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<Task> newList = new ArrayList<>();

        for(Task items : restoredItems) {
            String list = items.toString();
            if(list.toLowerCase().contains(newText)) {
                newList.add(items);
            }
        }

        adapter.searchitems(newList);
        return true;
    }
}
