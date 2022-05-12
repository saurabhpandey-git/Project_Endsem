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
import android.widget.LinearLayout;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PlanTripActivity extends AppCompatActivity {

    DatabaseReference database;
    ImageButton but_userLocation;
    Button but_submit, but_addTraveller, but_addLocation;
    TextView tv_userLocationLine1, tv_userLocationLine2;
    LinearLayout ll_cotravellerinfo1, ll_cotravellerinfo2, ll_cotravellerinfo3, ll_cotravellerinfo4, ll_cotravellerinfo5,
            ll_locationInfo1, ll_locationInfo2, ll_locationInfo3, ll_locationInfo4, ll_locationInfo5;
    EditText et_tripName, et_ColleageName1, et_ColleageName2, et_ColleageName3, et_ColleageName4, et_ColleageName5,
            et_ColleageEmail1, et_ColleageEmail2, et_ColleageEmail3, et_ColleageEmail4, et_ColleageEmail5,
            et_LocationName11, et_LocationName12, et_LocationName13, et_LocationName14,
            et_LocationName21, et_LocationName22, et_LocationName23, et_LocationName24,
            et_LocationName31, et_LocationName32, et_LocationName33, et_LocationName34,
            et_LocationName41, et_LocationName42, et_LocationName43, et_LocationName44,
            et_LocationName51, et_LocationName52, et_LocationName53, et_LocationName54;


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

        ll_cotravellerinfo1 = findViewById(R.id.ll_cotravellerinfo1);
        ll_cotravellerinfo2 = findViewById(R.id.ll_cotravellerinfo2);
        ll_cotravellerinfo3 = findViewById(R.id.ll_cotravellerinfo3);
        ll_cotravellerinfo4 = findViewById(R.id.ll_cotravellerinfo4);
        ll_cotravellerinfo5 = findViewById(R.id.ll_cotravellerinfo5);
        ll_locationInfo1 = findViewById(R.id.ll_locationInfo1);
        ll_locationInfo2 = findViewById(R.id.ll_locationInfo2);
        ll_locationInfo3 = findViewById(R.id.ll_locationInfo3);
        ll_locationInfo4 = findViewById(R.id.ll_locationInfo4);
        ll_locationInfo5 = findViewById(R.id.ll_locationInfo5);

        et_ColleageName1 = findViewById(R.id.et_ColleageName1);
        et_ColleageName2 = findViewById(R.id.et_ColleageName2);
        et_ColleageName3 = findViewById(R.id.et_ColleageName3);
        et_ColleageName4 = findViewById(R.id.et_ColleageName4);
        et_ColleageName5 = findViewById(R.id.et_ColleageName5);
        et_ColleageEmail1 = findViewById(R.id.et_ColleageEmail1);
        et_ColleageEmail2 = findViewById(R.id.et_ColleageEmail2);
        et_ColleageEmail3 = findViewById(R.id.et_ColleageEmail3);
        et_ColleageEmail4 = findViewById(R.id.et_ColleageEmail4);
        et_ColleageEmail5 = findViewById(R.id.et_ColleageEmail5);

        et_LocationName11 = findViewById(R.id.et_LocationName11);
        et_LocationName12 = findViewById(R.id.et_LocationName12);
        et_LocationName13 = findViewById(R.id.et_LocationName13);
        et_LocationName14 = findViewById(R.id.et_LocationName14);

        et_LocationName21 = findViewById(R.id.et_LocationName21);
        et_LocationName22 = findViewById(R.id.et_LocationName22);
        et_LocationName23 = findViewById(R.id.et_LocationName23);
        et_LocationName24 = findViewById(R.id.et_LocationName24);

        et_LocationName31 = findViewById(R.id.et_LocationName31);
        et_LocationName32 = findViewById(R.id.et_LocationName32);
        et_LocationName33 = findViewById(R.id.et_LocationName33);
        et_LocationName34 = findViewById(R.id.et_LocationName34);

        et_LocationName41 = findViewById(R.id.et_LocationName41);
        et_LocationName42 = findViewById(R.id.et_LocationName42);
        et_LocationName43 = findViewById(R.id.et_LocationName43);
        et_LocationName44 = findViewById(R.id.et_LocationName44);

        et_LocationName51 = findViewById(R.id.et_LocationName51);
        et_LocationName52 = findViewById(R.id.et_LocationName52);
        et_LocationName53 = findViewById(R.id.et_LocationName53);
        et_LocationName54 = findViewById(R.id.et_LocationName54);


        but_addTraveller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ll_cotravellerinfo1.getVisibility() == View.GONE) {
                    ll_cotravellerinfo1.setVisibility(View.VISIBLE);
                }else if(ll_cotravellerinfo2.getVisibility() == View.GONE){
                    ll_cotravellerinfo2.setVisibility(View.VISIBLE);
                }else if(ll_cotravellerinfo3.getVisibility() == View.GONE){
                    ll_cotravellerinfo3.setVisibility(View.VISIBLE);
                }else if(ll_cotravellerinfo4.getVisibility() == View.GONE){
                    ll_cotravellerinfo4.setVisibility(View.VISIBLE);
                }else if(ll_cotravellerinfo5.getVisibility() == View.GONE){
                    ll_cotravellerinfo5.setVisibility(View.VISIBLE);
                    but_addTraveller.setVisibility(View.GONE);
                }
            }
        });

        but_addLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ll_locationInfo1.getVisibility() == View.GONE) {
                    ll_locationInfo1.setVisibility(View.VISIBLE);
                }else if(ll_locationInfo2.getVisibility() == View.GONE) {
                    ll_locationInfo2.setVisibility(View.VISIBLE);
                }else if(ll_locationInfo3.getVisibility() == View.GONE) {
                    ll_locationInfo3.setVisibility(View.VISIBLE);
                }else if(ll_locationInfo4.getVisibility() == View.GONE) {
                    ll_locationInfo4.setVisibility(View.VISIBLE);
                }else if(ll_locationInfo5.getVisibility() == View.GONE) {
                    ll_locationInfo5.setVisibility(View.VISIBLE);
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

        List<String> colleageName = new ArrayList<>();
        List<String> colleageEmail = new ArrayList<>();
        List<String> LocationName = new ArrayList<>();
        List<String> LocationCity = new ArrayList<>();
        List<String> LocationState = new ArrayList<>();
        List<String> LocationCountry = new ArrayList<>();

        colleageName.add(et_ColleageName1.getText().toString());
        colleageName.add(et_ColleageName2.getText().toString());
        colleageName.add(et_ColleageName3.getText().toString());
        colleageName.add(et_ColleageName4.getText().toString());
        colleageName.add(et_ColleageName5.getText().toString());
        colleageEmail.add(et_ColleageEmail1.getText().toString());
        colleageEmail.add(et_ColleageEmail2.getText().toString());
        colleageEmail.add(et_ColleageEmail3.getText().toString());
        colleageEmail.add(et_ColleageEmail4.getText().toString());
        colleageEmail.add(et_ColleageEmail5.getText().toString());

        LocationName.add(et_LocationName11.getText().toString());
        LocationCity.add(et_LocationName12.getText().toString());
        LocationState.add(et_LocationName13.getText().toString());
        LocationCountry.add(et_LocationName14.getText().toString());

        LocationName.add(et_LocationName21.getText().toString());
        LocationCity.add(et_LocationName22.getText().toString());
        LocationState.add(et_LocationName23.getText().toString());
        LocationCountry.add(et_LocationName24.getText().toString());

        LocationName.add(et_LocationName31.getText().toString());
        LocationCity.add(et_LocationName32.getText().toString());
        LocationState.add(et_LocationName33.getText().toString());
        LocationCountry.add(et_LocationName34.getText().toString());

        LocationName.add(et_LocationName41.getText().toString());
        LocationCity.add(et_LocationName42.getText().toString());
        LocationState.add(et_LocationName43.getText().toString());
        LocationCountry.add(et_LocationName44.getText().toString());

        LocationName.add(et_LocationName51.getText().toString());
        LocationCity.add(et_LocationName52.getText().toString());
        LocationState.add(et_LocationName53.getText().toString());
        LocationCountry.add(et_LocationName54.getText().toString());

        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("currentTripPlan");
        database.child("name").setValue(et_tripName.getText().toString());

        int x;
        if(!colleageName.get(4).equals("")){
            x = 4;
        }else if(!colleageName.get(3).equals("")){
            x = 3;
        }else if(!colleageName.get(2).equals("")){
            x = 2;
        }else if(!colleageName.get(1).equals("")){
            x = 1;
        }else{
            x = 0;
        }
        for(int i=0; i<x+1; i++){
            database = FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("currentTripPlan").child("cotravellers");
            database=database.push();
            database.child("name").setValue(colleageName.get(i));
            database.child("email").setValue(colleageEmail.get(i));
        }

        int y;
        if(!LocationName.get(4).equals("")){
            y = 4;
        }else if(!LocationName.get(3).equals("")){
            y = 3;
        }else if(!LocationName.get(2).equals("")){
            y = 2;
        }else if(!LocationName.get(1).equals("")){
            y = 1;
        }else{
            y = 0;
        }
        for(int i=0; i<y+1; i++){
            database = FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("currentTripPlan").child("route");
            database = database.push();
            database.child("location").setValue(LocationName.get(i));
            database.child("city").setValue(LocationCity.get(i));
            database.child("state").setValue(LocationState.get(i));
            database.child("country").setValue(LocationCountry.get(i));
        }
        startActivity(new Intent(PlanTripActivity.this, UserProfileActivity.class));

    }
}