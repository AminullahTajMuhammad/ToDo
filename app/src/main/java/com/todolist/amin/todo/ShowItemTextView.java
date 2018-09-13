package com.todolist.amin.todo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ZoomButtonsController;

import java.io.Serializable;
import java.util.ArrayList;

public class ShowItemTextView extends AppCompatActivity implements Serializable {
    EditText showEditView;
    ImageButton btnSave;
    String show_text;
    String editedData;
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

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editedData = showEditView.getText().toString();
                Intent intent1 = new Intent();
                intent1.putExtra("Edited_Data",editedData);
                setResult(Activity.RESULT_OK,intent1);
                finish();
            }
        });
        setToolBar();

    }
    void setToolBar() {
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView txtAppName = findViewById(R.id.tv_Appname);
        txtAppName.setVisibility(View.GONE);

    }
}
