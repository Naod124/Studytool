package com.company.studytool.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.company.studytool.R;
import com.company.studytool.Model.ToDoList;

public class ToDoListViewAdapter extends ListAdapter<ToDoList, ToDoListViewAdapter.MyViewHolder> {

    private OnItemClickListener listener;

    public ToDoListViewAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<ToDoList> DIFF_CALLBACK = new DiffUtil.ItemCallback<ToDoList>() {
        @Override
        public boolean areItemsTheSame(@NonNull ToDoList oldItem, @NonNull ToDoList newItem) {

            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull ToDoList oldItem, @NonNull ToDoList newItem) {
            return oldItem.getTitle().equals(newItem.getTitle())
                    && oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPriority() == (newItem.getPriority());
        }
    };

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.node_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ToDoList currentToDoList = getItem(position);
        holder.title.setText(currentToDoList.getTitle());
        holder.description.setText(currentToDoList.getDescription());
        holder.priority.setText(String.valueOf(currentToDoList.getPriority()));
    }


    public ToDoList getNodeAt(int position) {
        return getItem(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView description;
        private TextView priority;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_view_title);
            description = itemView.findViewById(R.id.text_view_description);
            priority = itemView.findViewById(R.id.text_view_priority);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(ToDoList toDoList);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }
}
