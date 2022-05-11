package com.iiitd.mc.travelguideapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.iiitd.mc.travelguideapplication.model.Expense;

import java.util.ArrayList;
import java.util.HashMap;

public class ExpenseAdapter extends BaseAdapter {


    private ArrayList<HashMap<String,String>> list;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public ExpenseAdapter(ArrayList<HashMap<String,String>> list,Context c) {
        this.list = list;
        this.mContext = c;
        mLayoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View view = mLayoutInflater.inflate(R.layout.expense_item,parent,false);
        Holder h = new Holder();

        // set id's
        h.name = (TextView)(view.findViewById(R.id.expenseName));
        h.price = (TextView)(view.findViewById(R.id.expenseAmount));
//        h.stock = (TextView)(view.findViewById(R.id.stock));

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap = list.get(position);

        h.name.setText(hashMap.get("expensePurpose"));
        h.price.setText(hashMap.get("expenseAmount"));
//        h.stock.setText(hashMap.get("stockKey"));


        return view;
    }


    private class Holder
    {
        TextView name;
        TextView price;
    }
}