//package com.todolist.amin.todo;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.ImageButton;
//
//public class ItemList extends AppCompatActivity {
//    public void onCreat(Bundle saveInstanceState) {
//        super.onCreate(saveInstanceState);
//        setContentView(R.layout.list_item_view);
//        ImageButton imageButton = findViewById(R.id.dlt_button);
//
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            Adapter adapter;
//
//            @Override
//            public void onClick(View v) {
//                int position = adapter.position;
//                adapter.removeItem(position);
//            }
//        });
//    }
//}
