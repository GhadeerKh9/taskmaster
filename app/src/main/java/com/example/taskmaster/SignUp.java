package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;

public class SignUp extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        configureAmplify();


        Button signUp = findViewById(R.id.signUpButton);
        EditText username = findViewById(R.id.userNameSignUp);


        EditText email = findViewById(R.id.emailSignInSignUp);
        EditText password = findViewById(R.id.passwordSignInSignUp);


        TextView signIn = findViewById(R.id.signin);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp(
                        username.getText().toString(),
                        email.getText().toString(),
                        password.getText().toString());
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signIn = new Intent(SignUp.this, SignIn.class);
                startActivity(signIn);

            }
        });
    }



    void signUp(String username, String email, String password) {
        Amplify.Auth.signUp(
                username,
                password,
                AuthSignUpOptions.builder()
                        .userAttribute(AuthUserAttributeKey.email(), email)
                        .build(),
                success -> {
                    Log.i(TAG, "Success: " + success.toString());
                    Intent headToVerification = new Intent(SignUp.this, SignUpVerification.class);
                    headToVerification.putExtra("username", username);
                    headToVerification.putExtra("password", password);
                    startActivity(headToVerification);
                },
                error -> {
                    Log.e(TAG, "Failed: " + error.toString());
                });

    }

    private void configureAmplify() {

        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());
            Log.i(TAG, "Intialized");
        } catch (AmplifyException exception) {
            Log.e(TAG, "Failed to initialize" + exception.toString());
        }
    }
}