package com.iiitd.mc.travelguideapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class LoginFragment extends Fragment {


    TextView registerHere;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        registerHere = (TextView) view.findViewById(R.id.account);

        registerHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity act = (AppCompatActivity) view.getContext();
                Fragment registerFragment = new RegisterFragment();
                act.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHomeScreen,registerFragment).addToBackStack(null).commit();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}