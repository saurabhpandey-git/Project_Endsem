package com.iiitd.mc.travelguideapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeScreenFragment extends Fragment {

    RecyclerView rv;
    DatabaseReference db;
    AdapterHomeScreen ahs;
    ArrayList<Places> listPlaces;
    ImageButton userLogin;
    

    public HomeScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home_screen, container, false);

        rv = v.findViewById(R.id.recyclerViewList);
        db = FirebaseDatabase.getInstance().getReference("PlacesInfo");
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        listPlaces = new ArrayList<>();
        ahs = new AdapterHomeScreen(getContext(), listPlaces);
        rv.setAdapter(ahs);

        userLogin = (ImageButton) v.findViewById(R.id.userLogin);

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity act = (AppCompatActivity) v.getContext();
                Fragment loginFragment = new LoginFragment();
                act.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHomeScreen,loginFragment).addToBackStack(null).commit();
            }
        });


        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Places places = dataSnapshot.getValue(Places.class);
                    listPlaces.add(places);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        // Inflate the layout for this fragment
        return v;
    }
}