package com.todolist.amin.todo;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<String> list;
    private Activity activity;
    private int dltposition;
    private final int Task = 1, SubTask = 2;
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
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        RecyclerView.ViewHolder viewHolder = null;
        switch (i) {
            case Task:
                View v1 = inflater.inflate(R.layout.list_item_view, viewGroup, false);
                viewHolder = new ViewHolder1(v1, activity);
                break;

            case SubTask:
                View v2 = inflater.inflate(R.layout.activity_sub_task_recycler_view, viewGroup, false);
                viewHolder = new ViewHolder2(v2, activity);
                break;

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        switch (viewHolder.getItemViewType()) {
            case Task:
                ViewHolder1 viewHolder1 = (ViewHolder1) viewHolder;
                setTask(viewHolder1, i);
                break;
            case SubTask:
                ViewHolder2 viewHolder2 = (ViewHolder2) viewHolder;
                setSubTask(viewHolder2, i);
                break;
        }
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



    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        public int position;
        TextView textView;
        ImageButton dlt_button;
        public ViewHolder1(@NonNull View itemView, Context context) {
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
    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        public int position;
        TextView tvsubTask;
        ImageButton dlt_subTask;
        public ViewHolder2(@NonNull View itemView, Context context) {
            super(itemView);
            tvsubTask = itemView.findViewById(R.id.tvText_subtask);
            dlt_subTask = itemView.findViewById(R.id.dlt_button_subtask);
            dlt_subTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dltposition = getAdapterPosition();
                    removeItem(dltposition);
                }
            });
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            position = getAdapterPosition();
            listener.onItemClick(position);
        }
    }

    String getTask;
    private void setTask(ViewHolder1 vh1, int position) {
        String Task = list.get(position);
        getTask = Task;
        vh1.textView.setText(Task);
    }
    private void setSubTask(ViewHolder2 vh2, int position ) {
        String SubTask = list.get(position);
        vh2.tvsubTask.setText(SubTask);
    }

    private class getArrayList {
        private String task;

        public getArrayList(String task) {
            this.task = task;
        }

    }


    private ArrayList<Object> getItemsForList() {
        ArrayList<Object> items = new ArrayList<>();
        items.add(new getArrayList(getTask));
        return items;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
