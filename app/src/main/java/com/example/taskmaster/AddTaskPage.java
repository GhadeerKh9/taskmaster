package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.TaskClass;
import com.amplifyframework.datastore.generated.model.Team;

import java.util.ArrayList;
import java.util.List;

public class AddTaskPage extends AppCompatActivity {

    private TasksDAO taskDao;
    private String teamId = "";



    private final List<Team> teams = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);


        Spinner teamsList = findViewById(R.id.spinner);
        String[] teams = new String[]{"201-course", "301-course", "401-course"};
        ArrayAdapter<String> TeamsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, teams);
        teamsList.setAdapter(TeamsAdapter);



        TasksDatabase database = Room.databaseBuilder(getApplicationContext(), TasksDatabase.class, "task_List")
                .allowMainThreadQueries().build();
        taskDao = database.tasksDAO();


        getTeamsDataFromCloud();


        Button addToDbButton = findViewById(R.id.AddToDB);
        addToDbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){


                EditText titleField = findViewById(R.id.Field1ID);
                String title = titleField.getText().toString();

                EditText bodyField = findViewById(R.id.Field2ID);
                String body = bodyField.getText().toString();

                EditText stateField = findViewById(R.id.Field3ID);
                String state = stateField.getText().toString();


                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();



                com.example.taskmaster.TaskClass newTask = new com.example.taskmaster.TaskClass(title, body, state);
                taskDao.insertItem(newTask);



                Spinner teamSpinner = (Spinner) findViewById(R.id.spinner);
                String teamName = teamSpinner.getSelectedItem().toString();

                editor.putString("teamName", teamName);
                editor.apply();




                Log.i("ADD button", "THE TEAM ID:  " + getTeamId(teamName));

                addTaskToCloud(title,
                        body,
                        state,
                        new Team(getTeamId(teamName), teamName));

                System.out.println( ".....................>>>>>>>>>>>>>>>>" +  "Task ID is " + title + ".....................>>>>>>>>>>>>>>>>");





                //////////////////////////////////////////////////////////////



//                TaskClass task = TaskClass.builder()
//                        .title(title).body(body).state(state).build();
//
//                Amplify.API.mutate(
//                        ModelMutation.create(task),
//                        response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
//                        error -> Log.e("MyAmplifyApp", "Create failed", error)
//                );


                ///////////////////////////////////////////////////////


            }
        });



    }

    public void addTaskToCloud(String title, String body, String state, Team team) {
        com.amplifyframework.datastore.generated.model.TaskClass task = com.amplifyframework.datastore.generated.model.TaskClass.builder()
                .title(title)
                .body(body)
                .state(state)
                .team(team)
                .build();

        Amplify.API.mutate(ModelMutation.create(task),
                success -> Log.i("Add Task", "Saved item: " + task.getTitle()),
                error -> Log.e("Add Task", "Could not save item to API", error));

        Toast toast = Toast.makeText(this, "Task added!", Toast.LENGTH_LONG);
        toast.show();
    }

    private void getTeamsDataFromCloud() {
        Amplify.API.query(ModelQuery.list(Team.class),
                response -> {
                    for (Team team : response.getData()) {
                        teams.add(team);
                        Log.i("Add Task", "TEAM ID FROM CLOUD IS:  " + team.getTeamName() + "  " + team.getId());
                    }
                },
                error -> Log.e("Add Task", "Failed to get TEAM ID FROM CLOUD: " + error.toString())
        );
    }


    public String getTeamId(String teamName) {
        for (Team team : teams) {
            if (team.getTeamName().equals(teamName)) {
                return team.getId();
            }
        }
        return "";
    }


}