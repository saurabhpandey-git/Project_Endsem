package com.iiitd.mc.travelguideapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.iiitd.mc.travelguideapplication.model.Places;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
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
        System.out.println("Inside onBindViewHolder method ++++++++++++++++++++");
        Places p = listPlaces.get(position);
//        Picasso.get().load(listPlaces.get(position).getImgUrl()).into(holder.placesImg);
        holder.placeTitle.setText(p.getPlaceName());

        new ImageLoadTask(p.getURL(), holder.placesImg).execute();
//        Picasso.with(context).load(p.getURL()).into(holder.placesImg);
//        holder.placesImg.setImageURI(Uri.parse(p.getImgUrl()));
//        Picasso.with(context.getApplicationContext()).load(p.getURL()).into(holder.placesImg);
    }

    @Override
    public int getItemCount() {
        return listPlaces.size();
    }

    public static class ViewHolderHomeScreen extends RecyclerView.ViewHolder{

        TextView placeTitle;
//        TextView placeUrl;
         ImageView placesImg;



        public ViewHolderHomeScreen (@NonNull View itemView) {
            super(itemView);

            placeTitle = itemView.findViewById(R.id.placeTitle);
            placesImg = itemView.findViewById(R.id.placesImage);

        }
    }



    public static class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

        private String url;
        private ImageView imageView;

        public ImageLoadTask(String url, ImageView imageView) {
            //System.out.println("Got URL : "+url);
            this.url = url;
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                System.out.println("++++++++++++++++++++Inside Background of Async++++++++");
                System.out.println("URL = "+url);

                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection
                        .openConnection();
                //connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            imageView.setImageBitmap(result);
        }

    }

}