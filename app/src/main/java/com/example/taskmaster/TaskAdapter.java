package com.example.taskmaster;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
//import com.amplifyframework.datastore.generated.model.TaskClass;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    List<TaskClass> taskData = new ArrayList<>();


    private OnTaskItemClickListener listener;




    public TaskAdapter(List<TaskClass> taskData, OnTaskItemClickListener listener) {
        this.taskData = taskData;
        this.listener = listener;
    }

    public interface OnTaskItemClickListener {
        void onItemClicked(int position);

    }


    public static class TaskViewHolder extends RecyclerView.ViewHolder {


        public TaskClass task;



        View itemView;

        public TaskViewHolder(@NonNull View itemView, OnTaskItemClickListener listener) {
            super(itemView);
            this.itemView = itemView;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(getAdapterPosition());
                }
            });
        }
    }


    @NonNull
    @Override

    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task, parent , false);



        return  new TaskViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {

        holder.task = taskData.get(position);
        TextView title = holder.itemView.findViewById(R.id.TitleID);
        TextView body = holder.itemView.findViewById(R.id.BodyID);
        TextView state = holder.itemView.findViewById(R.id.StateID);


        title.setText(holder.task.getTitle());
        body.setText(holder.task.getBody());
        state.setText(holder.task.getState());


    }


    @Override

    public int getItemCount() {

        return taskData.size();
    }



}

