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
import android.widget.Toast;
import android.widget.ZoomButtonsController;

import java.io.Serializable;
import java.util.ArrayList;

public class ShowItemTextView extends AppCompatActivity {
    EditText showEditView;
    EditText editSubTask;
    ImageButton btnSave;
    Button btn_Subtask;
    String show_text;
    String editedData, str_SubTask;
    ArrayList<String> subTasks = new ArrayList<>();
    AdapterSubTasks adapterSubTasks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_item_text_view);
        showEditView = findViewById(R.id.tv_showText);
        btnSave = findViewById(R.id.save_txt_button);
        btn_Subtask = findViewById(R.id.btn_subtask);

        final Intent intent;
        intent = getIntent();
        show_text = intent.getStringExtra("Show_TextView");
        showEditView.setText(show_text);
        btnSave.setVisibility(View.INVISIBLE);
        setToolBar();

        btn_Subtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecyclerViewForSubTask();
            }
        });
    }
    void setToolBar() {
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editedData = showEditView.getText().toString();
                Toast.makeText(ShowItemTextView.this, "Your New Text is Saved",
                        Toast.LENGTH_SHORT).show();

                Intent intent1 = new Intent();
                intent1.putExtra("Edited_Data",editedData);
                setResult(Activity.RESULT_OK,intent1);
                finish();
            }
        });

        TextView txtAppName = findViewById(R.id.tv_Appname);
        txtAppName.setVisibility(View.GONE);

    }
    void setRecyclerViewForSubTask() {
        RecyclerView recyclerView = findViewById(R.id.subtask_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        editSubTask = findViewById(R.id.edt_subtask);
        str_SubTask = editSubTask.getText().toString();
        adapterSubTasks = new AdapterSubTasks(subTasks);
        adapterSubTasks.addItem(str_SubTask);
        adapterSubTasks.notifyItemInserted(adapterSubTasks.getItemCount());
        recyclerView.setAdapter(adapterSubTasks);
        editSubTask.setText("");
    }

}
