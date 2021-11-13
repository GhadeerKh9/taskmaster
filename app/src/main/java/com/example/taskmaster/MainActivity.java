package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.TaskClass;
import com.amplifyframework.datastore.generated.model.Team;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TaskAdapter adapter;

    private List<com.example.taskmaster.TaskClass> tasksData;


    private Handler handler;
    private TasksDAO taskDao;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler(message -> {
            notifyDataSetChanged();
            return false;
        });


        configureAmplify();



        TasksDatabase database = Room.databaseBuilder(getApplicationContext(), TasksDatabase.class, "task_List")
                .allowMainThreadQueries().build();
        taskDao = database.tasksDAO();

//        try {
//            Amplify.addPlugin(new AWSDataStorePlugin());
//            Amplify.addPlugin(new AWSApiPlugin());
//            Amplify.configure(getApplicationContext());
//
//            Log.i("MainActivity", "Initialized Amplify");
//        } catch (AmplifyException error) {
//            Log.e("MainActivity", "Could not initialize Amplify", error);
//        }



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



        saveTeamToApi("201-course");
        saveTeamToApi("301-course");
        saveTeamToApi("401-course");






//
//        Handler handler = new Handler(Looper.myLooper(), new Handler.Callback() {
//            @Override
//            public boolean handleMessage(@NonNull Message msg) {
//                allTasks.getAdapter().notifyDataSetChanged();
//                return false;
//            }
//        });
//        Amplify.API.query(
//                ModelQuery.list(TaskClass.class),
//                response -> {
//                    for (TaskClass todo : response.getData()) {
//                        com.example.taskmaster.TaskClass taskOrg = new com.example.taskmaster.TaskClass(todo.getTitle(),todo.getBody(),todo.getState());
//                        tasksData.add(taskOrg);
//                    }
//
//                    handler.sendEmptyMessage(1);
//                },
//                error -> Log.e("MyAmplifyApp", "Query failure", error)
//        );




    }




    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String enteredName = sharedPreferences.getString("EnteredText","Write the name");
        String teamName = sharedPreferences.getString("teamName", "");

        TextView tasks = findViewById(R.id.Message);
        tasks.setText(enteredName + "'s Tasks");





        tasksData = new ArrayList<>();
        if (teamName.equals("")) {
            getTasksDataFromCloud();
        } else {
            ((TextView) findViewById(R.id.teamName)).setText(teamName + " Tasks");
            getTeamTasksFromCloud(teamName);
        }

        Log.i("MainActivity", "onResume: tasks " + tasks);





        RecyclerView allTasks = findViewById(R.id.TaskID);

        allTasks.setLayoutManager(new LinearLayoutManager(this));

//        List<TaskClass> finalTasks = tasksData ;
        adapter = new TaskAdapter(tasksData , new TaskAdapter.OnTaskItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                Intent goToDetailsIntent = new Intent(getApplicationContext(), TaskDetailPage.class);
                goToDetailsIntent.putExtra("title", tasksData .get(position).getTitle());
                goToDetailsIntent.putExtra("body", tasksData .get(position).getBody());
                goToDetailsIntent.putExtra("state", tasksData .get(position).getState());
                startActivity(goToDetailsIntent);

            }
        });



        allTasks.setAdapter(adapter);

    }



    private void configureAmplify() {
        // configure Amplify plugins
        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());
            Log.i("Main", "Successfully initialized Amplify plugins");
        } catch (AmplifyException exception) {
            Log.e("Main", "Failed to initialize Amplify plugins: " + exception.toString());
        }
    }

    private void getTasksDataFromCloud() {
        Amplify.API.query(ModelQuery.list(TaskClass.class),
                response -> {
                    for (TaskClass task : response.getData()) {
                        tasksData.add(new com.example.taskmaster.TaskClass(task.getTitle(), task.getBody(), task.getState()));
                        Log.i("Main", "The Tasks From Cloud are: " + task.getTitle());
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("Main", "Failed to get Tasks from From Cloud: " + error.toString())
        );
    }

    @SuppressLint("NotifyDataSetChanged")
    private void notifyDataSetChanged() {
        adapter.notifyDataSetChanged();
    }


    public void saveTeamToApi(String teamName) {
        Team team = Team.builder().teamName(teamName).build();

        Amplify.API.query(ModelQuery.list(Team.class, Team.TEAM_NAME.contains(teamName)),
                response -> {
                    List<Team> teams = (List<Team>) response.getData().getItems();

                    if (teams.isEmpty()) {
                        Amplify.API.mutate(ModelMutation.create(team),
                                success -> Log.i("Main", "Saved Team: " + team.getTeamName()),
                                error -> Log.e("Main", "Could not save Team: ", error));
                    }
                },
                error -> Log.e("Main", "Failed to get Team from Cloud: " + error.toString())
        );

    }

    private void getTeamTasksFromCloud(String teamName) {
        Amplify.API.query(ModelQuery.list(TaskClass.class),
                response -> {
                    for (TaskClass task : response.getData()) {

                        if ((task.getTeam().getTeamName()).equals(teamName)) {
                            tasksData.add(new com.example.taskmaster.TaskClass(task.getTitle(), task.getBody(), task.getState()));
                            Log.i("Main", "The Tasks From Cloud are: " + task.getTitle());
                            Log.i("Main", "The Team From Cloud are: " + task.getTeam().getTeamName());
                        }
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("Main", "Failed to get Tasks from Cloud: " + error.toString())
        );
    }





    }




