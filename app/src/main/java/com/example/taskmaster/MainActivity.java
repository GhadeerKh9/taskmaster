package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.addTask);
        button1.setOnClickListener(new View.OnClickListener() {

    @Override
            public void onClick(View view){
        Intent intent1 = new Intent(MainActivity.this, Page2.class);
        startActivity(intent1);
    }

        });

        Button button2Page1 = findViewById(R.id.button3);
        button2Page1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent2 = new Intent(MainActivity.this, Page3.class);
                startActivity(intent2);
            }

        });




    }




}