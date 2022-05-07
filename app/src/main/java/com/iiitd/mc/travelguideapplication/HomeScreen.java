package com.iiitd.mc.travelguideapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iiitd.mc.travelguideapplication.model.Place;
import com.iiitd.mc.travelguideapplication.model.Places;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {

    private RecyclerView rv;
    private DatabaseReference db;
    private AdapterHomeScreen ahs;
    private ArrayList<Places> listPlaces;
    private ImageButton userLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        rv = findViewById(R.id.recyclerViewList);
        db = FirebaseDatabase.getInstance().getReference("PlacesInformation");
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        listPlaces = new ArrayList<>();
        ahs = new AdapterHomeScreen(this, listPlaces);
        rv.setAdapter(ahs);

        userLogin = findViewById(R.id.userLogin);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Places places = dataSnapshot.getValue(Places.class);
                    System.out.println("Place name = "+places.getPlaceName());
                    System.out.println("Place URL = "+places.getURL());
                    listPlaces.add(places);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}