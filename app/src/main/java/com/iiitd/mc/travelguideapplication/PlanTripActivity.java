package com.iiitd.mc.travelguideapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class PlanTripActivity extends AppCompatActivity {

    ImageButton but_userLocation;
    TextView tv_userLocationLine1, tv_userLocationLine2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_trip);

        but_userLocation = findViewById(R.id.but_userLocation);
        tv_userLocationLine1 = findViewById(R.id.tv_userLocationLine1);
        tv_userLocationLine2 = findViewById(R.id.tv_userLocationLine2);

        but_userLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                if (ActivityCompat.checkSelfPermission(PlanTripActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(PlanTripActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(PlanTripActivity.this, "Location Access required",Toast.LENGTH_LONG);
                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 0, (LocationListener) PlanTripActivity.this);
                Criteria criteria = new Criteria();
                String bestProvider = locationManager.getBestProvider(criteria, true);
                Location location = locationManager.getLastKnownLocation(bestProvider);

                if (location == null) {
                    Toast.makeText(getApplicationContext(), "GPS signal not found", Toast.LENGTH_SHORT).show();
                }
                if (location != null) {
                    Log.e("location", "location--" + location);

                    Log.e("latitude at beginning",
                            "@@@@@@@@@@@@@@@" + location.getLatitude());
                    onLocationChanged(location);
                }
            }
        });
    }

    public void onLocationChanged(Location location) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        Log.e("latitude", "latitude--" + latitude);
        try {
            Log.e("latitude", "inside latitude--" + latitude);
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && addresses.size() > 0) {
                String address = addresses.get(0).getAddressLine(0);
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName();

                tv_userLocationLine1.setText(state + " , " + city + " , " + country);
                tv_userLocationLine2.setText(address + " , " + knownName + " , " + postalCode);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}