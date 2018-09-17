package com.todolist.amin.todo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ZoomButtonsController;

import java.io.Serializable;
import java.util.ArrayList;

public class ShowItemTextView extends AppCompatActivity {
    EditText showEditView;
    ImageButton btnSave;
    String show_text;
    String editedData;
    ArrayList<String> subTasks;
    AdapterSubTasks adapterSubTasks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_item_text_view);
        showEditView = findViewById(R.id.tv_showText);
        btnSave = findViewById(R.id.save_txt_button);

        final Intent intent;
        intent = getIntent();
        show_text = intent.getStringExtra("Show_TextView");
        showEditView.setText(show_text);
        btnSave.setVisibility(View.INVISIBLE);
        setToolBar();
        setAnimationOfSubtask();
        setRecyclerViewForSubTask();

    }
    void setToolBar() {
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editedData = showEditView.getText().toString();
                Intent intent1 = new Intent();
                intent1.putExtra("Edited_Data",editedData);
                setResult(Activity.RESULT_OK,intent1);
                finish();
            }
        });

        TextView txtAppName = findViewById(R.id.tv_Appname);
        txtAppName.setVisibility(View.GONE);

    }
    void setAnimationOfSubtask() {
        Button btn_Subtask = findViewById(R.id.btn_subtask);
        final EditText editText = findViewById(R.id.edt_subtask);
        btn_Subtask.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
    }
    void setRecyclerViewForSubTask() {
        RecyclerView recyclerView = findViewById(R.id.subtask_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapterSubTasks = new AdapterSubTasks(subTasks);
        recyclerView.setAdapter(adapterSubTasks);
    }
}
