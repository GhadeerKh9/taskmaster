package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.core.Amplify;

public class SignUpVerification extends AppCompatActivity {


    private static final String TAG = "VerificationActivity";
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_verification);

        EditText confirmationCodeText = findViewById(R.id.confirmationCode);
        Button verify = findViewById(R.id.verify);

        Intent intent = getIntent();
        username = intent.getExtras().getString("username", "");
        password = intent.getExtras().getString("password", "");

        verify.setOnClickListener(view -> verification(username, confirmationCodeText.getText().toString()));
    }

    void verification(String username, String confirmationNumber) {
        Amplify.Auth.confirmSignUp(
                username,
                confirmationNumber,
                success -> {
                    Log.i(TAG, "Success" + success.toString());
                    Intent goToSignIn = new Intent(SignUpVerification.this, SignIn.class);
                    goToSignIn.putExtra("username", username);
                    startActivity(goToSignIn);


                },
                error -> {
                    Log.e(TAG, "Failed" + error.toString());
                });
    }

    void silentSignIn(String username, String password) {
        Amplify.Auth.signIn(
                username,
                password,
                success -> {
                    Log.i(TAG, "Success" + success.toString());
                },
                error -> Log.e(TAG, "Failed" + error.toString()));
    }
}