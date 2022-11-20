package com.example.ctcdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

public class AddRestaurantActivity extends AppCompatActivity implements View.OnClickListener {


    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        firebaseDatabase = FirebaseDatabase.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_restaurant);

        Button addRestaurantButton = findViewById(R.id.submit_button);
        addRestaurantButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String name = getName();
        String city = getCity();
        Boolean hasHealthyOptions = getHasHealthyOptions();
        Boolean hasVeganOptions = getHasHealthyOptions();
        double latitude = getLatitude();
        double longitude = getLongitude();

        Restaurant restaurant = new Restaurant(name,city,hasHealthyOptions,hasVeganOptions,latitude,longitude);
        firebaseDatabase.getReference("restaurant").child(city).child(name).setValue(restaurant);
        switchToMapsActivity();
    }

    protected String getName() {

        EditText name = findViewById(R.id.name);
        return name.getText().toString().trim();

    }

    protected String getCity() {

        EditText city = findViewById(R.id.city);
        return city.getText().toString().trim();

    }

    protected double getLatitude() {

        EditText latitude = findViewById(R.id.latitude);
        return Double.parseDouble(latitude.getText().toString().trim());

    }

    protected double getLongitude() {

        EditText longitude = findViewById(R.id.longitude);
        return Double.parseDouble(longitude.getText().toString().trim());

    }

    protected Boolean getHasHealthyOptions() {

        CheckBox hasHealthyOptions = findViewById(R.id.hasHealthyOptions);

        return Boolean.parseBoolean(hasHealthyOptions.getText().toString().trim());

    }

    protected void switchToMapsActivity() {

        Intent intent = new Intent(AddRestaurantActivity.this, MapsActivity.class);
        startActivity(intent);

    }
}
