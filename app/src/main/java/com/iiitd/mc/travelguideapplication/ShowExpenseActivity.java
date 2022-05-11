package com.iiitd.mc.travelguideapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.iiitd.mc.travelguideapplication.model.Places;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowExpenseActivity extends AppCompatActivity {

    ListView listView;
    DatabaseReference database;
    //    ArrayAdapter<String> arrayAdapter;
    ArrayList<HashMap<String,String>> expenseArrayList;

    Button but_addExpense;
    TextView tv_totalExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_expense);


        but_addExpense = findViewById(R.id.but_addExpense);
        tv_totalExpense = findViewById(R.id.tv_totalExpense);

        listView = findViewById(R.id.expenseitems);
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("currentTripPlan").child("expenseRecords");
        expenseArrayList = new ArrayList<HashMap<String, String>>();

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int total = 0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    HashMap<String,String> mHashMap = new HashMap<>();
                    System.out.println("Expense"+dataSnapshot.child("amount").getValue());
                    String amount = String.valueOf(dataSnapshot.child("amount").getValue());
                    total += Integer.parseInt(amount);
                    mHashMap.put("expensePurpose", amount);
                    mHashMap.put("expenseAmount", dataSnapshot.child("purpose").getValue().toString());
                    expenseArrayList.add(mHashMap);

                    System.out.println(expenseArrayList.get(expenseArrayList.size()-1));

                    ExpenseAdapter customListView = new ExpenseAdapter(expenseArrayList, ShowExpenseActivity.this);
                    listView.setAdapter(customListView);
                }
                tv_totalExpense.setText(Integer.toString(total));
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        but_addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShowExpenseActivity.this, AddExpenseActivity.class));
            }
        });
    }
}