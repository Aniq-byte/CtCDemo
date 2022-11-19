package com.example.ctcdemo;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity {
    FirebaseDatabase database;
    DatabaseReference ref;

    public void writeNewUser(String userId, String name, String email) {
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();
        User user = new User(name, email);
        ref.child("users").child(userId).setValue(user);
    }
}
