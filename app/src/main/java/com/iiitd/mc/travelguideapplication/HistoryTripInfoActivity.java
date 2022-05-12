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

public class HistoryTripInfoActivity extends AppCompatActivity {

    TextView myTripName;
    ListView cotravellerListView, routeListView;
    DatabaseReference database, routedb, dbref;
    ArrayList<HashMap<String,String>> cotravellerArrayList;
    String userID = FirebaseAuth.getInstance().getUid();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_trip_info);

        myTripName = findViewById(R.id.myTripName);
        cotravellerListView = findViewById(R.id.cotravellerList);
        routeListView = findViewById(R.id.routeList);

        String selectedTrip = getIntent().getExtras().getString("Selected Trip Name");

        dbref = FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("travelHistory");

        cotravellerArrayList = new ArrayList<HashMap<String, String>>();
        ArrayList<String> routeArrayList = new ArrayList<>();

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    System.out.println("Outer++++++++++++++++++++++++++++++++++++++++++++"+dataSnapshot.getKey().toString());
                    if (dataSnapshot.child("name").getValue().toString().equalsIgnoreCase(selectedTrip)) {
                        myTripName.setText(dataSnapshot.child("name").getValue().toString());

                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++"+dataSnapshot1.getKey().toString());

                            if (dataSnapshot1.getKey().toString().equalsIgnoreCase("cotravellers")) {
//                                System.out.println(dataSnapshot1.child("cotravellers").child("name").getValue().toString());
//                                System.out.println(dataSnapshot1.child("cotravellers").child("email").getValue().toString());
                                for (DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()) {
                                    HashMap<String, String> mHashMap = new HashMap<>();
                                    mHashMap.put("cotravellerName", dataSnapshot2.child("name").getValue().toString());
                                    mHashMap.put("cotravellerEmail", dataSnapshot2.child("email").getValue().toString());
                                    cotravellerArrayList.add(mHashMap);
                                    CotravellerAdapter arrayAdapter = new CotravellerAdapter(cotravellerArrayList, HistoryTripInfoActivity.this);
                                    cotravellerListView.setAdapter(arrayAdapter);
                                }
                            }else if (dataSnapshot1.getKey().toString().equalsIgnoreCase("route")) {
                                for (DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()) {
                                    routeArrayList.add(dataSnapshot2.child("location").getValue().toString() + ", " + dataSnapshot2.child("city").getValue().toString() + ", " + dataSnapshot2.child("state").getValue().toString() + ", " + dataSnapshot2.child("country").getValue().toString());

                                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(HistoryTripInfoActivity.this, android.R.layout.simple_list_item_1, routeArrayList);
                                    routeListView.setAdapter(arrayAdapter);
                                }

                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




//          \
    }
}