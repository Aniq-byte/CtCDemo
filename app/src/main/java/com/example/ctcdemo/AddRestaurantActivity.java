package com.example.ctcdemo;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;

public class AddRestaurantActivity extends AppCompatActivity implements View.OnClickListener {


    FirebaseDatabase firebaseDatabase;
    private Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        firebaseDatabase = FirebaseDatabase.getInstance();
        geocoder = new Geocoder(this);
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
        Boolean hasVeganOptions = getHasVeganOptions();
        String ZIPCode = getZIPCode();
        double latitude = 0;
        double longitude = 0;
        try {
            List<Address> addresses = geocoder.getFromLocationName(ZIPCode, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                // Use the address as needed
                latitude = address.getLatitude();
                longitude = address.getLongitude();
            } else {
                // Display appropriate message when Geocoder services are not available
                Toast.makeText(this, "Unable to geocode zipcode", Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            // handle exception
        }
        Toast.makeText(this,String.valueOf(latitude),Toast.LENGTH_LONG).show();
        Toast.makeText(this,String.valueOf(longitude),Toast.LENGTH_LONG).show();
        Restaurant restaurant = new Restaurant(name,city,hasHealthyOptions,hasVeganOptions,latitude,longitude,ZIPCode);
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

    protected String getZIPCode() {

        EditText ZIPCode = findViewById(R.id.ZIPCode);
        return ZIPCode.getText().toString().trim();

    }


    protected Boolean getHasHealthyOptions() {

        CheckBox hasHealthyOptions = findViewById(R.id.hasHealthyOptions);


        return hasHealthyOptions.isChecked();

    }

    protected Boolean getHasVeganOptions() {

        CheckBox hasVeganOptions = findViewById(R.id.hasVeganOptions);


        return hasVeganOptions.isChecked();

    }

    protected void switchToMapsActivity() {

        Intent intent = new Intent(AddRestaurantActivity.this, MapsActivity.class);
        startActivity(intent);

    }
}
