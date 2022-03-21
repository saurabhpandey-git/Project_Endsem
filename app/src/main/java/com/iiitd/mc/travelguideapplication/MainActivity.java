package com.iiitd.mc.travelguideapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragMan = getSupportFragmentManager();
        Fragment F = fragMan.findFragmentById(R.id.fragmentHomeScreen);
        if(F == null){
            Log.i("Fragment", "Fragment is null");
            F = new HomeScreenFragment();
            fragMan.beginTransaction().add(R.id.fragmentHomeScreen,F).commit();
        }
        Log.i("Fragment", "After inflating");


    }
}