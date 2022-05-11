package com.iiitd.mc.travelguideapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iiitd.mc.travelguideapplication.model.ExpenseRecord;

public class AddExpenseActivity extends AppCompatActivity {

    EditText expensePurpose, expenseAmount, expenseComment, expenseEmail;
    Button addExpense;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        expensePurpose = findViewById(R.id.expensePurpose);
        expenseAmount = findViewById(R.id.expenseAmount);
        expenseComment = findViewById(R.id.expenseComment);
        expenseEmail = findViewById(R.id.expenseUserEmail);
        addExpense = findViewById(R.id.addExpense);

        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myPurpose = expensePurpose.getText().toString();
                int myAmount = Integer.parseInt(expenseAmount.getText().toString());
                String myComment = expenseComment.getText().toString();
                String email = expenseEmail.getText().toString();

                ExpenseRecord expense = new ExpenseRecord(myPurpose, myComment, myAmount, email);
                String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                database = FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("currentTripPlan").child("expenseRecords");
                database=database.push();
                database.setValue(expense);
//                database.child("amount").setValue(myAmount);
//                database.child("comment").setValue(myComment);
//                database.child("purpose").setValue(myPurpose);
//                database.child("user").setValue(myUserId);
                Intent intent = new Intent(AddExpenseActivity.this, ShowExpenseActivity.class);
                startActivity(intent);
            }
        });

    }
}