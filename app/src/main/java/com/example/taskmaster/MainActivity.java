package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.TaskClass;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    boolean configured=true;
    private TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button2Page1 = findViewById(R.id.Button3);
        button2Page1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent2 = new Intent(MainActivity.this, AddTaskPage.class);
                startActivity(intent2);
            }

        });


        Button buttonSettings = findViewById(R.id.TaskViewer);
        buttonSettings.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                Intent intent3 = new Intent(MainActivity.this, SettingsPage.class);
                startActivity(intent3);
            }

        });

        if(configured) {
            configureAmplify();
        }


        List<TaskClass> tasks = new ArrayList<TaskClass>();
        tasks= gettingData();


        RecyclerView allTasks = findViewById(R.id.TaskID);

        allTasks.setLayoutManager(new LinearLayoutManager(this));

        List<TaskClass> finalTasks = tasks;
        adapter = new TaskAdapter(tasks, new TaskAdapter.OnTaskItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                Intent goToDetailsIntent = new Intent(getApplicationContext(), TaskDetailPage.class);
                goToDetailsIntent.putExtra("title", finalTasks.get(position).getTitle());
                goToDetailsIntent.putExtra("body", finalTasks.get(position).getBody());
                goToDetailsIntent.putExtra("state", finalTasks.get(position).getState());
                startActivity(goToDetailsIntent);

            }
        });

        allTasks.setAdapter(adapter);

    }



    public void getTask1(View view) {
        Intent taskDetail = new Intent(this,TaskDetailPage.class);
        taskDetail.putExtra("title", "Task1");
        startActivity(taskDetail);
    }


    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String enteredName = sharedPreferences.getString("EnteredText","Write the name");

        TextView tasks = findViewById(R.id.Message);
        tasks.setText(enteredName + "'s Tasks");
    }

    private void configureAmplify() {
        configured=false;

        try {

            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());
            Log.i("MainActivity", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MainActivity", "Could not initialize Amplify", error);
        }}

    private  List<TaskClass> gettingData(){
        List<TaskClass> foundTask=new ArrayList<>();

        Amplify.DataStore.query(
                TaskClass.class,
                queryMatches -> {
                    while (queryMatches.hasNext()) {
                        Log.i("MainActivity", "Founded");
                        foundTask.add(queryMatches.next());
                    }
                },
                error -> {
                    Log.i("MainActivity",  "COULD NOT FIND TASK", error);
                });
        return  foundTask;
    }


    }




