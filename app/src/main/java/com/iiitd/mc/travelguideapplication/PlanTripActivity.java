package com.iiitd.mc.travelguideapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iiitd.mc.travelguideapplication.model.ChatGroup;
import com.iiitd.mc.travelguideapplication.model.Trip;

public class PlanTripActivity extends AppCompatActivity {

    ImageButton but_userLocation;
    Button but_submit;
    TextView tv_userLocationLine1, tv_userLocationLine2;
    EditText et_tripName;

    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_trip);

        but_userLocation = findViewById(R.id.but_userLocation);
        tv_userLocationLine1 = findViewById(R.id.tv_userLocationLine1);
        tv_userLocationLine2 = findViewById(R.id.tv_userLocationLine2);

        et_tripName = findViewById(R.id.et_tripName);
        but_submit = findViewById(R.id.but_submit);

        but_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = getIntent().getExtras().get("CurrentUserID").toString();
                System.out.println("UserID = "+userId);
                db = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
                db = db.push();
                db.setValue(new Trip(et_tripName.getText().toString())).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "Trip successfully added", Toast.LENGTH_SHORT).show();
                        Log.i("LINE 33", "Trip successfully added");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("LINE 38", "Trip could not be added");
                    }
                });
            }
        });
    }
}