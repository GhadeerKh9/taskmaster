package com.example.taskmaster;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
  import com.amplifyframework.datastore.generated.model.TaskClass;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>{
//
//    List<TaskClass> taskData = new ArrayList<>();
//
//
//    private OnTaskItemClickListener listener;


//    public TaskAdapter(List<TaskClass> taskData) {
//        this.taskData = taskData;
//    }

//    public TaskAdapter(List<TaskClass> taskData, OnTaskItemClickListener listener) {
//        this.taskData = taskData;
//        this.listener = listener;
//    }

//    public interface OnTaskItemClickListener {
//        void onItemClicked(int position);
//
//    }
//
//
//    public static class TaskViewHolder extends RecyclerView.ViewHolder {

//
//        public TaskClass task;
//
//
//
//        View itemView;
//
//        public TaskViewHolder(@NonNull View itemView, OnTaskItemClickListener listener) {
//            super(itemView);
//            this.itemView = itemView;
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    listener.onItemClicked(getAdapterPosition());
//                }
//            });
//        }
//    }
//
//
//    @NonNull
//    @Override
//
//    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task, parent , false);
//
//
//
//        return  new TaskViewHolder(view, listener);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
//
//        holder.task = taskData.get(position);
//        TextView title = holder.itemView.findViewById(R.id.TitleID);
//        TextView body = holder.itemView.findViewById(R.id.BodyID);
//        TextView state = holder.itemView.findViewById(R.id.StateID);
//
//
//        title.setText(holder.task.getTitle());
//        body.setText(holder.task.getBody());
//        state.setText(holder.task.getState());
//
//
//    }
//
//
//    @Override
//
//    public int getItemCount() {
//
//        return taskData.size();
//    }
//

////////////////////////////////// kafaween




    List<TaskClass> allTasksData = new ArrayList<>();


    public TaskAdapter(List<TaskClass> allTasksData) {
        this.allTasksData = allTasksData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Context context = viewHolder.itemView.getContext();

        TaskClass task= allTasksData.get(position);
        viewHolder.textViewTitle.setText(task.getTitle());
        viewHolder.textViewBody.setText(task.getBody());
        viewHolder.textViewState.setText(task.getState());
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("my Adapter", "Element "+ viewHolder.getAdapterPosition() + " clicked");

                String Task1 =viewHolder.textViewTitle.getText().toString();
                editor.putString("TaskName",Task1);
                editor.apply();
                Intent gotToStd = new Intent(context,TaskDetailPage.class);
                context.startActivity(gotToStd);
//
            }


        });

    }

    @Override
    public int getItemCount() {
        return allTasksData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewTitle;
        public TextView textViewBody;
        public TextView textViewState;
        public LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle= (TextView)  itemView.findViewById(R.id.title);
            textViewBody= (TextView)  itemView.findViewById(R.id.body);
            textViewState= (TextView)  itemView.findViewById(R.id.state);
            linearLayout=(LinearLayout) itemView.findViewById(R.id.layout);

        }
    }
}

