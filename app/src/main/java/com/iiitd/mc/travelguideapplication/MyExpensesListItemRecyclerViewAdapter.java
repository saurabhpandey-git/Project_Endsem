package com.iiitd.mc.travelguideapplication;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iiitd.mc.travelguideapplication.model.User;
import com.iiitd.mc.travelguideapplication.databinding.FragmentExpensesBinding;

import java.util.List;

public class MyExpensesListItemRecyclerViewAdapter extends RecyclerView.Adapter<MyExpensesListItemRecyclerViewAdapter.ViewHolder> {

    private List<ExpenseListItem> expensesList;
    private Context context;

    public MyExpensesListItemRecyclerViewAdapter(List<ExpenseListItem> expensesList, Context context) {
        this.expensesList = expensesList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentExpensesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
    ExpenseListItem item = expensesList.get(position);
    holder.tripName.setText(item.getTripName());
    holder.amount.setText(String.valueOf(item.getAmount()));
    }

    @Override
    public int getItemCount() {
        return expensesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tripName;
        public TextView amount;

        public ViewHolder(FragmentExpensesBinding binding) {
            super(binding.getRoot());
            tripName = binding.tripName;
            amount = binding.amount;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tripName.getText() +" "+amount.getText()+"'";
        }
    }
}