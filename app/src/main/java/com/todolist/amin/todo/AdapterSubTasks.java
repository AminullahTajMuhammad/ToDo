package com.todolist.amin.todo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

public class AdapterSubTasks extends RecyclerView.Adapter<AdapterSubTasks.ViewHolder> {
    ArrayList<String> subTasks;
    public AdapterSubTasks(@NonNull ArrayList<String> subTasks) {
        this.subTasks = subTasks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.activity_sub_task_recycler_view, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String str_SubTask = subTasks.get(i);
        viewHolder.tvSubTask.setText(str_SubTask);
    }

    @Override
    public int getItemCount() {
        return subTasks.size();
    }

    void addItem(String task) {
        subTasks.add(task);
    }

    public void removeItem(int position) {
        subTasks.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText tvSubTask;
        ImageButton btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSubTask = itemView.findViewById(R.id.edt_subtask);
            btnDelete = itemView.findViewById(R.id.dlt_button_subtask);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int dltposition = getAdapterPosition();
                    removeItem(dltposition);
                }
            });
        }
    }
}