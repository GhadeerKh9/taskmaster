package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.analytics.AnalyticsEvent;
import com.amplifyframework.core.Amplify;

import java.io.File;

public class TaskDetailPage extends AppCompatActivity {


//    private String fileURL;

    String img = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tas_detail_page);


        recordEvents();

//    Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//        String taskName = extras.getString("title");
//        TextView text = findViewById(R.id.TaskTitle);
//        text.setText(taskName);
//
//        String taskBody = extras.getString("body");
//        TextView text2 = findViewById(R.id.textView8);
//        text2.setText(taskBody);
//
//        String taskState = extras.getString("state");
//        TextView text3 = findViewById(R.id.textView7);
//        text3.setText(taskState);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String titleName = sharedPreferences.getString("title", "title");
        String bodyName = sharedPreferences.getString("body", "body");
        String stateName = sharedPreferences.getString("state", "state");
//        String img = sharedPreferences.getString("img", "");

        TextView title = findViewById(R.id.TaskTitle);
        TextView body = findViewById(R.id.textView8);
        TextView state = findViewById(R.id.textView7);


        title.setText(titleName);
        body.setText(bodyName);
        state.setText(stateName);


//        String img =  sharedPreferences.getString("img", "");



        Amplify.Storage.downloadFile(
                "img",
                new File(getApplicationContext().getFilesDir() + "/download.jpg"),
                result -> {
                    ImageView image = findViewById(R.id.imageId);
                    sharedPreferences.getString("img", "");
                    image.setImageBitmap(BitmapFactory.decodeFile(result.getFile().getPath()));

                    Log.i("MyAmplifyApp", "Successfully downloaded: " + result.getFile());
                },
                error -> Log.e("MyAmplifyApp", "Download Failure", error)
        );
    }


    public void recordEvents() {
        AnalyticsEvent event = AnalyticsEvent.builder()
                .name("PasswordReset")
                .addProperty("Channel", "SMS")
                .addProperty("Successful", true)
                .addProperty("ProcessDuration", 792)
                .addProperty("UserAge", 120.3)
                .build();

        Amplify.Analytics.recordEvent(event);
    }

    }



