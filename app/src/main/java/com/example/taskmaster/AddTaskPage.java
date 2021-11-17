package com.example.taskmaster;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.amplifyframework.analytics.AnalyticsEvent;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;


import com.amplifyframework.datastore.generated.model.TaskClass;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AddTaskPage extends AppCompatActivity {



    String img = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);


        recordEvents();


        Button addFile = findViewById(R.id.uploadImg);
        addFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFileFromDevice();
            }
        });



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


                RadioButton b1=findViewById(R.id.radioButton1);
                RadioButton b2=findViewById(R.id.radioButton2);
                RadioButton b3=findViewById(R.id.radioButton3);




                String id = null;
                if(b1.isChecked()){
                    id="1";
                }
                else if(b2.isChecked()){
                    id="2";
                }
                else if(b3.isChecked()){
                    id="3";
                }





                dataStore(title, body, state, id, img);



                System.out.println( ".....................>>>>>>>>>>>>>>>>" +  "Task ID is " + title + ".....................>>>>>>>>>>>>>>>>"


                );


                Intent intent = new Intent(AddTaskPage.this, MainActivity.class);
                startActivity(intent);

            }



        });



    }

    private void dataStore(String title, String body, String state,String id, String img) {
        TaskClass task = TaskClass.builder().teamId(id).title(title).body(body).state(state).img(img).build();


        Amplify.API.mutate(ModelMutation.create(task),succuess-> {
            Log.i("Add Task", "Saved to DYNAMODB");
        }, error -> {
            Log.i("Add Task", "error saving to DYNAMODB");
        });

    }


    private void getFileFromDevice() {
        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("*/*");
        chooseFile = Intent.createChooser(chooseFile, "Choose a File");
        startActivityForResult(chooseFile, 1234);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File uploadFile = new File(getApplicationContext().getFilesDir(), "uploadFileCopied");
        try {
            InputStream exampleInputStream = getContentResolver().openInputStream(data.getData());
            OutputStream outputStream = new FileOutputStream(uploadFile);
            img = data.getData().toString();
            byte[] buff = new byte[1024];
            int length;
            while ((length = exampleInputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            exampleInputStream.close();
            outputStream.close();
            Amplify.Storage.uploadFile(
                    "img",
                    uploadFile,
                    result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
                    storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
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