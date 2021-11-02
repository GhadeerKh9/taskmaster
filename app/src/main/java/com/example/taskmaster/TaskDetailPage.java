package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TaskDetailPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tas_detail_page);

        Intent intent = getIntent();
        ((TextView) findViewById(R.id.TaskTitle)).setText(intent.getExtras().getString("title"));
        ((TextView) findViewById(R.id.textView8)).setText(intent.getExtras().getString("body"));
        ((TextView) findViewById(R.id.textView7)).setText(intent.getExtras().getString("state"));

    }
}