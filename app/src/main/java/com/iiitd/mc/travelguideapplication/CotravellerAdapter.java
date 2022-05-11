package com.iiitd.mc.travelguideapplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class CotravellerAdapter extends BaseAdapter {


    private ArrayList<HashMap<String,String>> list;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public CotravellerAdapter(ArrayList<HashMap<String,String>> list, Context c) {
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


        View view = mLayoutInflater.inflate(R.layout.cotraveller_item,parent,false);
        Holder h = new Holder();

        // set id's
        h.name = (TextView)(view.findViewById(R.id.cotravellerName));
        h.email = (TextView)(view.findViewById(R.id.cotravellerEmail));
//        h.stock = (TextView)(view.findViewById(R.id.stock));

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap = list.get(position);

        h.name.setText(hashMap.get("cotravellerName"));
        h.email.setText(hashMap.get("cotravellerEmail"));
//        h.stock.setText(hashMap.get("stockKey"));


        return view;
    }


    private class Holder
    {
        TextView name;
        TextView email;
    }
}

//public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.MyViewHolder> {
//
//    Context context;
//    ArrayList<Expense> expenseList;
//
//    public ExpenseAdapter(Context context, ArrayList<Expense> expenseList) {
//        this.context = context;
//        this.expenseList = expenseList;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(context).inflate(R.layout.expense_item, parent, false);
//        return new MyViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        Expense expense = expenseList.get(position);
//        holder.expenseName.setText(expense.getPurpose());
//        holder.expenseAmount.setText(expense.getAmount());
//    }
//
//    @Override
//    public int getItemCount() {
//        return expenseList.size();
//    }
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder{
//
//        TextView expenseName, expenseAmount;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            expenseName = itemView.findViewById(R.id.expenseName);
//            expenseAmount = itemView.findViewById(R.id.expenseAmount);
//        }
//    }
//}