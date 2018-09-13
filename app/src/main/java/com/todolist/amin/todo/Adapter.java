package com.todolist.amin.todo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<String> list;
    private Activity activity;
    private int dltposition;
    OnTapListener listener;

    public Adapter() {

    }

    public Adapter(ArrayList<String> list, Activity activity, OnTapListener listener) {
        this.list = list;
        this.activity = activity;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.list_item_view, viewGroup, false);
        return new ViewHolder(view, activity);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String title = list.get(i);
        viewHolder.textView.setText(title);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    void addItem(String text) {
        list.add(text);
    }

    void updateItem(String newText, int position) {
        list.set(position, newText);
    }

    String getItem(int position) {
        return list.get(position);
    }

    ArrayList<String> getItems() {
        return list;
    }

    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public int position;
        TextView textView;
        ImageButton dlt_button;
        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvText);

            itemView.setOnClickListener(this);

            dlt_button = itemView.findViewById(R.id.dlt_button);
            dlt_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dltposition = getAdapterPosition();
                    removeItem(dltposition);
                }
            });

        }

        @Override
        public void onClick(View view) {
            position = getAdapterPosition();
            listener.onItemClick(position);
        }
    }
}
