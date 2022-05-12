package com.iiitd.mc.travelguideapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class HistoryActivity extends AppCompatActivity {

    ListView historyListView;
    DatabaseReference dbref;
    ArrayList<String> historyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyListView = findViewById(R.id.historyListView);
        historyName = new ArrayList<>();
        String userID = FirebaseAuth.getInstance().getUid();
        dbref = FirebaseDatabase.getInstance().getReference().child("User").child("4").child("travelHistory");


        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    historyName.add(dataSnapshot.child("name").getValue().toString());

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(HistoryActivity.this, android.R.layout.simple_list_item_1, historyName);
                    historyListView.setAdapter(arrayAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        historyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String tripSelected = ((TextView)view).getText().toString();
                Intent intent = new Intent(HistoryActivity.this, HistoryTripInfoActivity.class);
                intent.putExtra("Selected Trip Name", tripSelected);
                startActivity(intent);
            }
        });



    }
}