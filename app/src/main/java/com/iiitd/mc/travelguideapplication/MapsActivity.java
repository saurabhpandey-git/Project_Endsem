package com.iiitd.mc.travelguideapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.iiitd.mc.travelguideapplication.databinding.ActivityMapsBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private ArrayList<LatLng> locationArrayList;
    DatabaseReference database;
//    String locationArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationArrayList = new ArrayList<>();
//        locationArrayList = new ArrayList<HashMap<String, String>>();
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<HashMap<String,String>> object = (ArrayList<HashMap<String,String>>) args.getSerializable("ARRAYLIST");
//        locationArrayList = getIntent().getExtras().getString("SelectedNearbyPlaceLatLong");
        System.out.println("######## Inside Google Map: " + object.get(0).get("latitude") + "#######");

        for(int i=0; i<object.size(); i++){
            Double latitude = Double.parseDouble(object.get(i).get("latitude").toString());
            Double longitude = Double.parseDouble(object.get(i).get("longitude").toString());
            System.out.println("######" + latitude + "...." + longitude + "####$");
            LatLng place = new LatLng(latitude, longitude);
            locationArrayList.add(place);
        }

    }
//        database = FirebaseDatabase.getInstance().getReference().child("NearByLocations").child(selectedNearByPlace);
//        LatLng place = new LatLng(database.child("0").child("latitude").getValue().toString());
//        database.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    double latitude = Double.parseDouble(dataSnapshot.child("latitude").getValue().toString());
//                    double longitude = Double.parseDouble(dataSnapshot.child("longitude").getValue().toString());
////                    System.out.println(latitude + ".... " +longitude);
//                    LatLng place = new LatLng(latitude, longitude);
//                    locationArrayList.add(place);
//                    break;
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        for(int i=0; i<locationArrayList.size(); i++){
//            System.out.println(locationArrayList.get(i).latitude + "...." );
//        }

//        LatLng place1 = new LatLng(-34, 151);
//        LatLng place2 = new LatLng(-31.083332, 150.916672);
//        LatLng place3 = new LatLng(-32.916668, 151.750000);
//        LatLng place4 = new LatLng(-27.470125, 153.021072);
//        locationArrayList=new ArrayList<>();
//        locationArrayList.add(place1);
//        locationArrayList.add(place2);
//        locationArrayList.add(place3);
//        locationArrayList.add(place4);
//    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        for (int i = 0; i < locationArrayList.size(); i++) {
//             LatLng pla=new LatLng(28.6424728,77.2112383);
            // below line is use to add marker to each location of our array list.
            mMap.addMarker(new MarkerOptions().position(locationArrayList.get(i)).title("Marker"));

            // below lin is use to zoom our camera on map.
            mMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f));

            // below line is use to move our camera to the specific location.
            mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(i)));
        }
    }
}