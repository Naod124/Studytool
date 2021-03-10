package com.company.studytool;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NodeViewAdapter extends ListAdapter<Node, NodeViewAdapter.MyViewHolder> {

    private OnItemClickListener listener;

    public NodeViewAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Node> DIFF_CALLBACK = new DiffUtil.ItemCallback<Node>() {
        @Override
        public boolean areItemsTheSame(@NonNull Node oldItem, @NonNull Node newItem) {

            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Node oldItem, @NonNull Node newItem) {
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
        Node currentNode = getItem(position);
        holder.title.setText(currentNode.getTitle());
        holder.description.setText(currentNode.getDescription());
        holder.priority.setText(String.valueOf(currentNode.getPriority()));
    }


    public Node getNodeAt(int position) {
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
        void onItemClick(Node node);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }
}
