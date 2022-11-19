package com.example.ctcdemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText emailInput;
    EditText password;

    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.email);
        password = findViewById(R.id.password);

        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() != null) {

            finish();
            return;

        }

        Button loginButton = findViewById(R.id.buttonLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                authenticateUser();

            }

        });

        Button registrationButton = findViewById(R.id.registerButton);

        registrationButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                switchToRegistrationActivity(view);

            }

        });

    }


    private void authenticateUser() {

        if(getEmail().isEmpty()){

            Toast.makeText(this, "Please enter your email", Toast.LENGTH_LONG).show();
            return;

        }

        if(getPassword().isEmpty()){
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_LONG).show();
            return;
        }

        auth.signInWithEmailAndPassword(getEmail(), getPassword())
                .addOnCompleteListener(this, task -> {

                            if (task.isSuccessful()) {

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);

                            } else {

                                Toast.makeText(LoginActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();

                            }
                        }
                );
    }

    public void switchToRegistrationActivity(View v) {

        Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(intent);

    }

    protected String getEmail() {

        EditText email = findViewById(R.id.email);

        return email.getText().toString().trim();

    }

    protected String getPassword() {

        EditText password = findViewById(R.id.password);

        return password.getText().toString().trim();

    }

}
