package com.iiitd.mc.travelguideapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NearByPlacesActivity extends AppCompatActivity {

    ListView nearByListView;
    DatabaseReference database;
    //    ArrayAdapter<String> arrayAdapter;
    ArrayList<HashMap<String,String>> expenseArrayList, LatLongArrayList;
    EditText nearbyLocation;
    Button btn_searchNearBy, btn_showInMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_places);

        nearbyLocation = findViewById(R.id.nearbyLocation);
        btn_searchNearBy = findViewById(R.id.btn_searchNearBy);

        nearByListView = findViewById(R.id.nearByListView);
        btn_showInMap = findViewById(R.id.btn_showInMap);

//        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        String[] searchMyNearLocation = new String[1];
        btn_searchNearBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchMyNearLocation[0] = nearbyLocation.getText().toString();
                database = FirebaseDatabase.getInstance().getReference().child("NearByLocations").child(searchMyNearLocation[0]);
                expenseArrayList = new ArrayList<HashMap<String, String>>();
                LatLongArrayList = new ArrayList<HashMap<String, String>>();

//                final String[] searchObject = new String[1];

                database.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                            System.out.println("##########" + dataSnapshot.getKey().toString());
//                            if(dataSnapshot.getKey().toString().equalsIgnoreCase(searchMyNearLocation)){
//                                searchObject[0] = searchMyNearLocation;
//                                for (DataSnapshot dataSnapshot1 : dataSnapshot.child(searchMyNearLocation).getChildren()) {
                            HashMap<String, String> mHashMap = new HashMap<>();
                            HashMap<String, String> latLongHashMap = new HashMap<>();
                            //                    System.out.println("Expense"+dataSnapshot.child("amount").getValue());

                            System.out.println(dataSnapshot.child("name").getValue().toString());
                            System.out.println(dataSnapshot.child("distance").getValue().toString());
                            System.out.println(dataSnapshot.child("address").getValue().toString());
                            mHashMap.put("expensePurpose", dataSnapshot.child("name").getValue().toString());
                            mHashMap.put("expenseAmount", dataSnapshot.child("distance").getValue().toString());
                            mHashMap.put("expenseComment", dataSnapshot.child("address").getValue().toString());
                            mHashMap.put("expenseUserId", "distance");
                            expenseArrayList.add(mHashMap);

                            latLongHashMap.put("latitude", dataSnapshot.child("latitude").getValue().toString());
                            latLongHashMap.put("longitude", dataSnapshot.child("longitude").getValue().toString());
                            LatLongArrayList.add(latLongHashMap);

                            System.out.println("################INside innerloop#######");

                            ExpenseAdapter customListView = new ExpenseAdapter(expenseArrayList, NearByPlacesActivity.this);
                            nearByListView.setAdapter(customListView);
//                                }
//                                break;
                        }
//                            } else if(dataSnapshot.getKey().toString().equalsIgnoreCase("Hospitals")){
//                                searchObject[0] = "Hospitals";
//                            } else if(dataSnapshot.getKey().toString().equalsIgnoreCase("Hotels")){
//                                searchObject[0] = "Hotels";
//                            } else if(dataSnapshot.getKey().toString().equalsIgnoreCase("Petrol")){
//                                searchObject[0] = "Petrol";
//                            }

                    }

//                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("NearByLocations").child(searchObject[0]);

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        btn_showInMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NearByPlacesActivity.this, MapsActivity.class);
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable) LatLongArrayList);
                intent.putExtra("BUNDLE",args);
                startActivity(intent);
//                intent.putExtra("SelectedNearbyPlaceLatLong", LatLongArrayList);
//                startActivity(intent);
            }
        });


//        but_addExpense.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(ShowExpenseActivity.this, AddExpenseActivity.class));
//            }
//        });
    }
}