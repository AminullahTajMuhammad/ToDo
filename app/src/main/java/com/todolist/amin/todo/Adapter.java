package com.todolist.amin.todo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private ArrayList<Task> list = new ArrayList<Task>();
    private ArrayList<Task> contactListFiltered = new ArrayList<>();
    private Activity activity;
    private int dltposition;
    private final int VIEWTYPE_TASK = 1, VIEWTYPE_SUBTASK = 2;
    OnTapListener listener;


    public Adapter(ArrayList<Task> list, Activity activity, OnTapListener listener) {
        this.list = list;
        this.contactListFiltered = list;
        this.activity = activity;
        this.listener = listener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case VIEWTYPE_TASK:
                View v1 = inflater.inflate(R.layout.list_item_view, viewGroup, false);
                viewHolder = new ViewHolder1(v1, activity);
                break;

            case VIEWTYPE_SUBTASK:
                View v2 = inflater.inflate(R.layout.activity_sub_task_recycler_view, viewGroup, false);
                viewHolder = new ViewHolder2(v2, activity);
                break;

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        switch (viewHolder.getItemViewType()) {
            case VIEWTYPE_TASK:
                ViewHolder1 viewHolder1 = (ViewHolder1) viewHolder;
                setTask(viewHolder1, i);
                break;
            case VIEWTYPE_SUBTASK:
                ViewHolder2 viewHolder2 = (ViewHolder2) viewHolder;
                setSubTask(viewHolder2, i);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return contactListFiltered.size();
    }

    void addItem(Task task) {
        list.add(task);
    }

    void updateItem(Task newText, int position) {
        list.set(position, newText);
    }

    Task getItem(int position) {
        return list.get(position);
    }

    ArrayList<Task> getItems() {
        return list;
    }

    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }


//---------------- For Search Item List --------------------//
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    contactListFiltered = list;
                } else {
                    ArrayList<Task> filteredList = new ArrayList<>();
                    for (Task row : list) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.toString().toLowerCase().contains(charString.toLowerCase())
                                || row.toString().contains(constraint)) {
                            filteredList.add(row);
                        }
                    }

                    contactListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = contactListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                contactListFiltered = (ArrayList<Task>) results.values;
                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
    }
//-----------------------------------------------------//

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
            tvsubTask = itemView.findViewById(R.id.edt_subtask);
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
    private void setTask(ViewHolder1 vh1, int position) {
        Task task = contactListFiltered.get(position);
        vh1.textView.setText(task.getDesc());

    }
    private void setSubTask(ViewHolder2 vh2, int position ) {
        Task SubTask = list.get(position);
        vh2.tvsubTask.setText(SubTask.getDesc());
    }

    @Override
    public int getItemViewType(int position) {
        if(list.get(position).getIsSubtask()) {
            return VIEWTYPE_SUBTASK;
        }
        else {
            return VIEWTYPE_TASK;
        }
    }
//
//    void searchitems(ArrayList<Task> searchedList) {
//        list = new ArrayList<Task>();
//        list.addAll(searchedList);
//        notifyDataSetChanged();
//    }
}
