package com.iiitd.mc.travelguideapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterHomeScreen extends RecyclerView.Adapter<AdapterHomeScreen.ViewHolderHomeScreen> {


    Context context;
    ArrayList<Places> listPlaces;

    public AdapterHomeScreen(Context context, ArrayList<Places> list) {
        this.context = context;
        this.listPlaces = list;
    }

    @NonNull
    @Override
    public ViewHolderHomeScreen onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list,parent,false);
        return new ViewHolderHomeScreen(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderHomeScreen holder, int position) {

        Places p = listPlaces.get(position);
        holder.placeTitle.setText(p.getPlaceName());

    }

    @Override
    public int getItemCount() {
        return listPlaces.size();
    }

    public static class ViewHolderHomeScreen extends RecyclerView.ViewHolder{

        TextView placeTitle;



        public ViewHolderHomeScreen(@NonNull View itemView) {
            super(itemView);

            placeTitle = itemView.findViewById(R.id.placeTitle);

        }
    }

}
