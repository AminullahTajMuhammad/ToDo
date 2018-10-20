package com.todolist.amin.todo;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

public class write_text extends AppCompatActivity {
    Button btnAdd;
    EditText editText;
    Adapter adapter;
    String catchText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_text);
        editText = findViewById(R.id.edt);
        btnAdd = findViewById(R.id.btnAdd);
        editText.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                catchMassage();
                Toast.makeText(write_text.this,
                        "You Task is Added in list",Toast.LENGTH_SHORT).show();
            }
        });

        setToolBar();            //For ToolBar contants
    }
    public void catchMassage() {
        catchText = editText.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("DATA",catchText);
        setResult(Activity.RESULT_OK,intent);
        finish();
        editText.setText("");
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
