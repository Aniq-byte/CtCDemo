package com.example.ctcdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Button registerButton = findViewById(R.id.createUserButton);
        registerButton.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();

    }


    @Override
    public void onClick(View view) {

        String emailAddress = getEmailAddress();
        String password = getPassword();

        if (password.isEmpty()) {

            Toast.makeText(RegistrationActivity.this, "Password is Empty", Toast.LENGTH_SHORT).show();

        }

        if (emailAddress.isEmpty()) {

            Toast.makeText(RegistrationActivity.this, "Email is Empty", Toast.LENGTH_SHORT).show();

        }

        auth.createUserWithEmailAndPassword(emailAddress,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            User user = new User(emailAddress, password);

                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {

                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            switchToNextActivity();

                                        }
                                    });

                        } else {

                            Toast.makeText(RegistrationActivity.this, "Authentication failed", Toast.LENGTH_LONG).show();

                        }
                    }
                });

        switchToNextActivity();
    }

    protected String getPassword() {

        EditText password = findViewById(R.id.registerPassword);
        return password.getText().toString().trim();

    }

    protected String getEmailAddress() {

        EditText emailAddress = findViewById(R.id.registerEmail);
        return emailAddress.getText().toString().trim();

    }

    protected void switchToNextActivity() {

        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
        startActivity(intent);

    }


}
