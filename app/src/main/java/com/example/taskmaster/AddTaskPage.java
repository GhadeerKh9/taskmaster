package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddTaskPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);


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


                TaskClass task = new TaskClass(title, body, state);
                Long taskID = TasksDatabase.getInstance(getApplicationContext()).tasksDAO().insertItem(task);


                System.out.println( ".....................>>>>>>>>>>>>>>>>" +  "Task ID is " + taskID + ".....................>>>>>>>>>>>>>>>>");

                Intent intent = new Intent(AddTaskPage.this, MainActivity.class);

                startActivity(intent);
            }
        });



    }



}