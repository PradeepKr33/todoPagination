package com.example.todopagination;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todopagination.response.TodosItem;

import java.util.ArrayList;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private Context context;

    private ArrayList<TodosItem> todosItemsList;

    public TodoAdapter(Context context, ArrayList<TodosItem> todosItemsList) {
        this.context = context;
        this.todosItemsList = todosItemsList;
    }


    @NonNull
    @Override
    public TodoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.ViewHolder holder, int position) {

        TodosItem todosItem = todosItemsList.get(position);

        holder.userId.setText(String.valueOf(todosItem.getUserId()));
        holder.todo.setText(todosItem.getTodo());
        if (todosItem.isCompleted()) {
            holder.completed.setText("Completed");
        }else {
           holder.completed.setText("Not Completed");
        }

    }

    @Override
    public int getItemCount() {
        return todosItemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView userId,completed,todo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userId = itemView.findViewById(R.id.userId);
            completed = itemView.findViewById(R.id.completed);
            todo = itemView.findViewById(R.id.todo);
        }
    }
}
