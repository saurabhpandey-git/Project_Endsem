package com.iiitd.mc.travelguideapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iiitd.mc.travelguideapplication.model.AppUser;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    private ListView lv;
    private EditText et;
    private Button b;
    private DatabaseReference dbase;
    private ArrayAdapter<String> aAdapter;
    private static ArrayList<String> chatHistory = new ArrayList<>();
    private String username = "";
    private String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        lv=(ListView) findViewById(R.id.lV);
        et=(EditText) findViewById(R.id.eT);
        b=(Button) findViewById(R.id.button3);

        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        dbase=FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("chats");
        dbase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chatHistory.clear();
                for(DataSnapshot ds:snapshot.getChildren()){
                    if(ds.child("Sender").getValue().toString().length()!=0) {
                        String a = ds.child("Sender").getValue().toString() + " : " + ds.child("Message").getValue().toString();
                        Toast.makeText(ChatActivity.this, ""+a, Toast.LENGTH_SHORT).show();
                        chatHistory.add(a);

                    }
                    //Toast.makeText(groupChats.this, "chatHis"+chatHis.size(), Toast.LENGTH_SHORT).show();


                }
                aAdapter=new ArrayAdapter<String>(ChatActivity.this, android.R.layout.simple_list_item_1,chatHistory);
                lv.setAdapter(aAdapter);

                // et.setText("");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        dbase=FirebaseDatabase.getInstance().getReference().child("Users").child(userID);
        dbase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                AppUser userProfile = snapshot.getValue(AppUser.class);
                username = userProfile.getName();
                System.out.println("Snapshot = "+ snapshot);
                System.out.println("User Name +++++++++++++++++++++++++++++++= "+userProfile.getName()+" , user age = "+userProfile.getAge());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ChatActivity.this, "Something Wrong Happened!", Toast.LENGTH_SHORT).show();
            }
        });


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(ChatActivity.this, ""+getIntent().getExtras().get("ChatRoom").toString(), Toast.LENGTH_SHORT).show();
//                chatHistory.clear();
                dbase=FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("chats");
                dbase=dbase.push();
                String msg = et.getText().toString();
                dbase.child("Message").setValue(msg);
                dbase.child("Sender").setValue(username);
                chatHistory.add(username + " : " +msg);
                aAdapter=new ArrayAdapter<String>(ChatActivity.this, android.R.layout.simple_list_item_1,chatHistory);
                lv.setAdapter(aAdapter);
                et.setText("");
            }
        });
        /*dbase= FirebaseDatabase.getInstance().getReference().child("Srijan");
        dbase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/



    }
}