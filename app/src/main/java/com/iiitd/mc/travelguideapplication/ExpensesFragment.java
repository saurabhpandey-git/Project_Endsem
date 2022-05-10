package com.iiitd.mc.travelguideapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iiitd.mc.travelguideapplication.model.User;

import java.util.ArrayList;
import java.util.List;


/**
 * A fragment representing a list of Items.
 */
public class ExpensesFragment extends Fragment {


//    private TextView tv_tripName;
//    private TextView tv_amount;
//
//    RecyclerView recyclerView;
//    DatabaseReference database;
//    MyExpensesListItemRecyclerViewAdapter myAdapter;
//
//
//
//    public String tripName;
//    public String amount;
//
//    public static List<ExpenseListItem> expenses = new ArrayList<ExpenseListItem>();
//
//    /**
//     * Mandatory empty constructor for the fragment manager to instantiate the
//     * fragment (e.g. upon screen orientation changes).
//     */
//    public ExpensesFragment() {
//    }
//
//    // TODO: Customize parameter initialization
//    @SuppressWarnings("unused")
//    public static ExpensesFragment newInstance(String tripName, String amount) {
//        ExpensesFragment fragment = new ExpensesFragment();
//        Bundle args = new Bundle();
//
//        args.putString("TripName", tripName);
//        args.putString("Amount", amount);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            tripName = getArguments().getString("TripName");
//            amount = getArguments().getString("Amount");
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_expenses_list, container, false);
//
////        tv_tripName = view.findViewById(R.id.tripName);
////        tv_amount = view.findViewById(R.id.amount);
//
//        recyclerView = view.findViewById(R.id.list);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
////        for(int i=0; i<20; i++){
////            listItems.add(new ListItem(i, "Dummy Text"));
////        }
//
////
////        for(int i=0; i<20; i++){
////            expenses.add(new ExpenseListItem("TripX", 10000.0F));
////        }
//
//
//
//        myAdapter = new MyExpensesListItemRecyclerViewAdapter(expenses, getContext());
//        recyclerView.setAdapter(myAdapter);
//
//        database = FirebaseDatabase.getInstance().getReference("User");
//
//        database.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//
//                    System.out.println(dataSnapshot);
//                    System.out.println("=============================");
//                    System.out.println(dataSnapshot.getValue());
//                    //for(Object o: )
//
//                    //System.out.println(dataSnapshot.getValue(User.class));
//                    User user = dataSnapshot.getValue(User.class);
//                    //System.out.println(user.getId()+" "+user.getName());
//                    if(user.getId() == MainActivity.user0.getId())
//                        expenses.add(new ExpenseListItem(user.getCurrentTripPlan().getName(), 1000.0F));
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        return view;
//    }
}