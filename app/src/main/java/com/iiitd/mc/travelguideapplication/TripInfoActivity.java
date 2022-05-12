package com.iiitd.mc.travelguideapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class TripInfoActivity extends AppCompatActivity {

    TextView myTripName;
    ListView cotravellerListView, routeListView;
    DatabaseReference database, routedb, dbref;
    ArrayList<HashMap<String,String>> cotravellerArrayList;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_info);

        myTripName = findViewById(R.id.myTripName);
        cotravellerListView = findViewById(R.id.cotravellerList);
        routeListView = findViewById(R.id.routeList);

        userID = FirebaseAuth.getInstance().getUid();
        dbref = FirebaseDatabase.getInstance().getReference().child("User").child("4").child("currentTripPlan");

        cotravellerArrayList = new ArrayList<HashMap<String, String>>();
        ArrayList<String> routeArrayList = new ArrayList<>();

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    if(dataSnapshot.getKey().toString().equals("name")){
                        myTripName.setText(dataSnapshot.getValue().toString());
                        System.out.println(dataSnapshot.getValue().toString());
                    }
                    else if(dataSnapshot.getKey().toString().equals("cotravellers")){
                        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("currentTripPlan").child("cotravellers");
                        db.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {

                                    HashMap<String,String> mHashMap = new HashMap<>();
                                    mHashMap.put("cotravellerName", dataSnapshot1.child("name").getValue().toString());
                                    mHashMap.put("cotravellerEmail", dataSnapshot1.child("email").getValue().toString());
                                    cotravellerArrayList.add(mHashMap);
//                                    cotravellerArrayList.add(dataSnapshot1.child("name").getValue().toString());
//                                    cotravellerArrayList.add(dataSnapshot1.child("email").getValue().toString());

//                                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(TripInfoActivity.this, android.R.layout.simple_list_item_1, cotravellerArrayList);
                                    CotravellerAdapter arrayAdapter = new CotravellerAdapter(cotravellerArrayList, TripInfoActivity.this);
                                    cotravellerListView.setAdapter(arrayAdapter);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                    else if(dataSnapshot.getKey().toString().equals("route")){
                        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("currentTripPlan").child("route");
                        db.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                                    routeArrayList.add(dataSnapshot1.child("location").getValue().toString() + ", " + dataSnapshot1.child("city").getValue().toString()  + ", " + dataSnapshot1.child("state").getValue().toString() + ", " + dataSnapshot1.child("country").getValue().toString());

                                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(TripInfoActivity.this, android.R.layout.simple_list_item_1, routeArrayList);
                                    routeListView.setAdapter(arrayAdapter);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        database = FirebaseDatabase.getInstance().getReference().child("User").child("4").child("currentTripPlan").child("cotravellers");
//        ArrayList<ArrayList<String>> cotravellersArrayList = new ArrayList<>();
//        ArrayList<ArrayList<String>> routeArrayList = new ArrayList<>();
//
//        database.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//
//                    ArrayList<String> cotraveller = new ArrayList<>();
//                    cotraveller.add(dataSnapshot.child("name").getValue().toString());
//                    cotraveller.add(dataSnapshot.child("email").getValue().toString());
//                    cotravellersArrayList.add(cotraveller);
//
//                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(TripInfoActivity.this, android.R.layout.simple_list_item_2, cotraveller);
//                    cotravellerListView.setAdapter(arrayAdapter);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        routedb = FirebaseDatabase.getInstance().getReference().child("User").child("4").child("currentTripPlan").child("route");
//        routedb.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//
//                    ArrayList<String> route = new ArrayList<>();
//                    route.add(dataSnapshot.child("location").getValue().toString());
//                    route.add(dataSnapshot.child("city").getValue().toString());
//                    routeArrayList.add(route);
//
//                    ArrayAdapter<String> routeAdapter = new ArrayAdapter<String>(TripInfoActivity.this, android.R.layout.simple_list_item_2, route);
//                    routeListView.setAdapter(routeAdapter);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }
}