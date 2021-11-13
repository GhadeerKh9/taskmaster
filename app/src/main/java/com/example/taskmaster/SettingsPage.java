package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class SettingsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);


        Spinner teamsList = findViewById(R.id.spinner_setting);
        String[] teams = new String[]{"","201-course", "301-course", "401-course"};
        ArrayAdapter<String> TeamsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, teams);
        teamsList.setAdapter(TeamsAdapter);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = sharedPreferences.edit();


        findViewById(R.id.SaveButton).setOnClickListener(view -> {

            TextView text = findViewById(R.id.EnteredName);
            Spinner teamSpinner = (Spinner) findViewById(R.id.spinner_setting);
            String teamName = teamSpinner.getSelectedItem().toString();




            String name =text.getText().toString();

            editor.putString("EnteredText",name);
            editor.putString("teamName", teamName);

            editor.apply();

//
//            Intent intentOne = new Intent(this, MainActivity.class);
//
//            startActivity(intentOne);


        });






    }
}