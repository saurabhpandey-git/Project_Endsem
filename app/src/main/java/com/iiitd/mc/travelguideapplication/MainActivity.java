package com.iiitd.mc.travelguideapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.iiitd.mc.travelguideapplication.model.ChatGroup;
import com.iiitd.mc.travelguideapplication.model.ExpenseRecord;
import com.iiitd.mc.travelguideapplication.model.Expenses;
import com.iiitd.mc.travelguideapplication.model.Location;
import com.iiitd.mc.travelguideapplication.model.Trip;
import com.iiitd.mc.travelguideapplication.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static User user0;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DatabaseReference dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();

        Log.i("LINE_27", "ref :"+dbRef);

        User user1 = new User("Yukti", 26, "9876543210", "abc@gmail.com");
        User user2 = new User("Roshan", 26, "9632587410", "xyz@gmail.com");
        User user3 = new User("Srijan", 26, "6985471230", "asfas@gmail.com");
        User user4 = new User("Harman", 26, "7584962102", "srgerw@gmail.com");


       //User0 details
//       String username = "Saurabh Pandey";
//       int userage = 26;
//       String usermobile = "7007874545";
//       String email = "saurabh.pandeyhp@gmail.com";
//       String profilePic = "\"https://cdn2.vectorstock.com/i/1000x1000/20/76/man-avatar-profile-vector-21372076.jpg\"";
        Location location = new Location(28.544976F, 77.192628F, "IIIT Delhi");
        List<User> cotravellers = new ArrayList<User>();
        cotravellers.add(user1);
        cotravellers.add(user2);
        cotravellers.add(user3);
        cotravellers.add(user4);

        List<ExpenseRecord> expenses  = new ArrayList<ExpenseRecord>();
        expenses.add(new ExpenseRecord(user1.getId(), "Hotel", "In Ram Vilas", 12000.0F));
        expenses.add(new ExpenseRecord(user1.getId(), "Food", "At City Plaza", 3287.0F));
        expenses.add(new ExpenseRecord(user2.getId(), "Cab", "From Delhi to Panvel", 15000.0F));
        expenses.add(new ExpenseRecord(user3.getId(), "Cab", "Visit of day1", 12000.0F));

        Trip currentTrip = new Trip("Lonavala Trip", "14072022", null, new Expenses(expenses), null, null);

        user0 = new User("Saurabh Pandey", 26, "7007874545", "saurabh.pandeyhp@gmail.com",
                "https://cdn2.vectorstock.com/i/1000x1000/20/76/man-avatar-profile-vector-21372076.jpg", location,
                currentTrip, null, null);
        dbRef = FirebaseDatabase.getInstance().getReference().child("User/"+user1.getId());

        dbRef.setValue(user1).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(MainActivity.this, "User successfully added", Toast.LENGTH_SHORT).show();
                Log.i("LINE 33", "User successfully added");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("LINE 38", "User could not be added");
            }
        });
        dbRef = FirebaseDatabase.getInstance().getReference().child("User/"+user2.getId());
        dbRef.setValue(user2).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(MainActivity.this, "User successfully added", Toast.LENGTH_SHORT).show();
                Log.i("LINE 33", "User successfully added");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("LINE 38", "User could not be added");
            }
        });
        dbRef = FirebaseDatabase.getInstance().getReference().child("User/"+user3.getId());
        dbRef.setValue(user3).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(MainActivity.this, "User successfully added", Toast.LENGTH_SHORT).show();
                Log.i("LINE 33", "User successfully added");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("LINE 38", "User could not be added");
            }
        });
        dbRef = FirebaseDatabase.getInstance().getReference().child("User/"+user4.getId());
        dbRef.setValue(user4).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(MainActivity.this, "User successfully added", Toast.LENGTH_SHORT).show();
                Log.i("LINE 33", "User successfully added");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("LINE 38", "User could not be added");
            }
        });


        dbRef = FirebaseDatabase.getInstance().getReference().child("User/"+user0.getId());
        dbRef.setValue(user0).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(MainActivity.this, "User successfully added", Toast.LENGTH_SHORT).show();
                Log.i("LINE 33", "User successfully added");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("LINE 38", "User could not be added");
            }
        });

        CollectionReference appStorage = db.collection("AppCollection");

        appStorage.add(user0).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                // after the data addition is successful
                // we are displaying a success toast message.
                Toast.makeText(MainActivity.this, "Your Course has been added to Firebase Firestore", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
                Toast.makeText(MainActivity.this, "Fail to add course \n" + e, Toast.LENGTH_SHORT).show();
            }
        });






//        db.collection("users").document("abc")
//                .set(user)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Log.i("LINE_22", "DocumentSnapshot successfully written!");
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.i("LINE_23", "Error writing document");
//                    }
//                });
    }
}