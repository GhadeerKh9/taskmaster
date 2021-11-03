package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SettingsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);



        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = sharedPreferences.edit();


        findViewById(R.id.SaveButton).setOnClickListener(view -> {
            TextView text = findViewById(R.id.EnteredName);



            String name =text.getText().toString();

            editor.putString("EnteredText",name);

            editor.apply();


            Intent intentOne = new Intent(this, MainActivity.class);

            startActivity(intentOne);


        });






    }
}