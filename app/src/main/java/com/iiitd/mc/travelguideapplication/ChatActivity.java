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

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    private ListView lv;
    private EditText et;
    private Button b;
    private DatabaseReference dbase;
    private ArrayAdapter<String> aAdapter;
    private static ArrayList<String> chatHis=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        lv=(ListView) findViewById(R.id.lV);
        et=(EditText) findViewById(R.id.eT);
        b=(Button) findViewById(R.id.button3);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(ChatActivity.this, ""+getIntent().getExtras().get("ChatRoom").toString(), Toast.LENGTH_SHORT).show();
                String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                dbase=FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("chats");
                dbase=dbase.push();
                dbase.child("Message").setValue("Hi");
                dbase.child("Sender").setValue("Jack");
//              dbase=FirebaseDatabase.getInstance().getReference().child("Srijan").child("Chats").child(getIntent().getExtras().get("ChatRoom").toString());
                dbase=dbase.push();
                dbase.child("Message").setValue("Hello");
                dbase.child("Sender").setValue("Pooja");
//                dbase=FirebaseDatabase.getInstance().getReference().child("Srijan").child("Chats").child(getIntent().getExtras().get("ChatRoom").toString());
                dbase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        chatHis.clear();
                        for(DataSnapshot ds:snapshot.getChildren()){
                            if(ds.child("Sender").getValue().toString().length()!=0) {
                                String a = ds.child("Sender").getValue().toString() + " : " + ds.child("Message").getValue().toString();
                                //Toast.makeText(groupChats.this, ""+a, Toast.LENGTH_SHORT).show();
                                chatHis.add(a);
                            }
                            //Toast.makeText(groupChats.this, "chatHis"+chatHis.size(), Toast.LENGTH_SHORT).show();


                        }
                        if(et.getText().toString().length()!=0) {

                            chatHis.add("Majnu : " + et.getText().toString());

                        }


                        aAdapter=new ArrayAdapter<String>(ChatActivity.this, android.R.layout.simple_list_item_1,chatHis);
                        lv.setAdapter(aAdapter);
                        // et.setText("");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });
                //Toast.makeText(groupChats.this, "chatHis outside"+chatHis.size(), Toast.LENGTH_SHORT).show();
                if(et.getText().toString().length()!=0){
                    dbase = FirebaseDatabase.getInstance().getReference().child("Srijan").child("Chats").child(getIntent().getExtras().get("ChatRoom").toString());
                    dbase = dbase.push();
                    dbase.child("Message").setValue(et.getText().toString());
                    dbase.child("Sender").setValue("Majnu");
                }
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