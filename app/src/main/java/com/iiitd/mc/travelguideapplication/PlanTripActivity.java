package com.iiitd.mc.travelguideapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iiitd.mc.travelguideapplication.model.ChatGroup;
import com.iiitd.mc.travelguideapplication.model.Trip;

import java.util.List;
import java.util.Locale;

public class PlanTripActivity extends AppCompatActivity {

    ImageButton but_userLocation;
    Button but_submit, but_addTraveller, but_addLocation;
    TextView tv_userLocationLine1, tv_userLocationLine2;
    EditText et_tripName, et_ColleageEmail1, et_ColleageEmail2, et_ColleageEmail3, et_ColleageEmail4, et_ColleageEmail5,
            et_LocationName1, et_LocationName2, et_LocationName3, et_LocationName4, et_LocationName5;

    private DatabaseReference db;

    private LocationManager locationManager;
    private LocationListener locationListener;

    private FusedLocationProviderClient mFusedLocationClient;
    private int PERMISSION_ID = 44;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_trip);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        but_userLocation = findViewById(R.id.but_userLocation);
        tv_userLocationLine1 = findViewById(R.id.tv_userLocationLine1);
        tv_userLocationLine2 = findViewById(R.id.tv_userLocationLine2);

        et_tripName = findViewById(R.id.et_tripName);
        but_submit = findViewById(R.id.but_submit);

        but_addTraveller = findViewById(R.id.but_addTraveller);
        but_addLocation = findViewById(R.id.but_addLocation);

        et_LocationName1 = findViewById(R.id.et_LocationName1);
        et_LocationName2 = findViewById(R.id.et_LocationName2);
        et_LocationName3 = findViewById(R.id.et_LocationName3);
        et_LocationName4 = findViewById(R.id.et_LocationName4);
        et_LocationName5 = findViewById(R.id.et_LocationName5);
        et_ColleageEmail1 = findViewById(R.id.et_ColleageEmail1);
        et_ColleageEmail2 = findViewById(R.id.et_ColleageEmail2);
        et_ColleageEmail3 = findViewById(R.id.et_ColleageEmail3);
        et_ColleageEmail4 = findViewById(R.id.et_ColleageEmail4);
        et_ColleageEmail5 = findViewById(R.id.et_ColleageEmail5);




        but_addTraveller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_ColleageEmail1.getVisibility() == View.GONE) {
                    et_ColleageEmail1.setVisibility(View.VISIBLE);
                }else if(et_ColleageEmail2.getVisibility() == View.GONE){
                    et_ColleageEmail2.setVisibility(View.VISIBLE);
                }else if(et_ColleageEmail3.getVisibility() == View.GONE){
                    et_ColleageEmail3.setVisibility(View.VISIBLE);
                }else if(et_ColleageEmail4.getVisibility() == View.GONE){
                    et_ColleageEmail4.setVisibility(View.VISIBLE);
                }else if(et_ColleageEmail5.getVisibility() == View.GONE){
                    et_ColleageEmail5.setVisibility(View.VISIBLE);
                    but_addTraveller.setVisibility(View.GONE);
                }
            }
        });

        but_addLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_LocationName1.getVisibility() == View.GONE) {
                    et_LocationName1.setVisibility(View.VISIBLE);
                }else if(et_LocationName2.getVisibility() == View.GONE) {
                    et_LocationName2.setVisibility(View.VISIBLE);
                }else if(et_LocationName3.getVisibility() == View.GONE) {
                    et_LocationName3.setVisibility(View.VISIBLE);
                }else if(et_LocationName4.getVisibility() == View.GONE) {
                    et_LocationName4.setVisibility(View.VISIBLE);
                }else if(et_LocationName5.getVisibility() == View.GONE) {
                    et_LocationName5.setVisibility(View.VISIBLE);
                    but_addLocation.setVisibility(View.GONE);
                }
            }
        });







        but_userLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLastLocation();
            }
        });

        but_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formSubmit();
            }
        });
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        // check if permissions are given
        if (checkPermissions()) {

            // check if location is enabled
            if (isLocationEnabled()) {

                // getting last
                // location from
                // FusedLocationClient
                // object
                mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if (location == null) {
                            requestNewLocationData();
                        } else {
                            showLocationAddress(location.getLatitude(), location.getLongitude());
                        }
                    }
                });
            } else {
                Toast.makeText(this, "Please turn on" + " your location...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            // if permissions aren't available,
            // request for permissions
            requestPermissions();
        }
    }



    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {

        // Initializing LocationRequest
        // object with appropriate methods
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        // setting LocationRequest
        // on FusedLocationClient
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }

    private LocationCallback mLocationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            showLocationAddress(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        }
    };

    // method to check for permissions
    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        // If we want background location
        // on Android 10.0 and higher,
        // use:
        // ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    // method to request for permissions
    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID);
    }

    // method to check
    // if location is enabled
    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    // If everything is alright then
    @Override
    public void
    onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (checkPermissions()) {
            getLastLocation();
        }

    }

    private void showLocationAddress(double latitude, double longitude) {
        try {

            Geocoder geo = new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address> addresses = geo.getFromLocation(latitude, longitude, 1);
            if (addresses.isEmpty()) {
                tv_userLocationLine1.setText("Waiting for User Location");
                tv_userLocationLine2.setText("");
            } else {
                System.out.println("Feature name = " + addresses.get(0).getFeatureName());
                System.out.println("Locality = " + addresses.get(0).getLocality());
                System.out.println("Admin area = " + addresses.get(0).getAdminArea());
                System.out.println("Country name = " + addresses.get(0).getCountryName());
//                address = addresses.get(0).getFeatureName() + ", " + addresses.get(0).getLocality() + ", " + addresses.get(0).getAdminArea() + ", " + addresses.get(0).getCountryName();
//                name = addresses.get(0).getFeatureName() + ", " + addresses.get(0).getLocality();
                tv_userLocationLine1.setText(addresses.get(0).getFeatureName() + ", " + addresses.get(0).getLocality());
                tv_userLocationLine2.setText(addresses.get(0).getAdminArea() + ", " + addresses.get(0).getCountryName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }






    private void formSubmit() {
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        db = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);
        db = db.push();
        db.setValue(new Trip(et_tripName.getText().toString())).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Trip successfully added", Toast.LENGTH_SHORT).show();
                Log.i("LINE 33", "Trip successfully added");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("LINE 38", "Trip could not be added");
            }
        });
    }
}