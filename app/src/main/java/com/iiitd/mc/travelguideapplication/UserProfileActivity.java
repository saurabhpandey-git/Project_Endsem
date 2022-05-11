package com.iiitd.mc.travelguideapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.iiitd.mc.travelguideapplication.model.AppUser;
import com.squareup.picasso.Picasso;

public class UserProfileActivity extends AppCompatActivity {

    private TextView nameTextView;
    private TextView ageTextView;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userId;

    private ImageView userImage;

    private Button logout, but_currentTrip;

    //chat screen
    private ImageButton chatButton;

    private FirebaseStorage storage;
    private StorageReference storageReference;

    private ImageButton cameraButton;

    private ImageButton expensesButton;
    private ImageButton historyButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        storageReference= FirebaseStorage.getInstance().getReference();

        but_currentTrip = findViewById(R.id.but_currentTrip);
        but_currentTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfileActivity.this, PlanTripActivity.class);
                intent.putExtra("CurrentUserID", userId);
                startActivity(intent);
            }
        });


        chatButton = (ImageButton) findViewById(R.id.chatButton);
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserProfileActivity.this, ChatActivity.class));
            }
        });

        // logout functionality
        logout = (Button) findViewById(R.id.signout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(UserProfileActivity.this, HomeScreenActivity.class));
            }
        });


        user= FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userId = user.getUid();



        //fetch image.
        StorageReference profileref = storageReference.child( userId + ".jpg");
        profileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext()).load(uri).into(userImage);
            }
        });


        final TextView nameTextView = (TextView) findViewById(R.id.nameTextView);
        final TextView ageTextView = (TextView) findViewById(R.id.ageTextView);

        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                AppUser userProfile = snapshot.getValue(AppUser.class);
                System.out.println("Snapshot = "+ snapshot);
                System.out.println("User Name = "+userProfile.getName()+" , user age = "+userProfile.getAge());
                if(userProfile!=null){
                    String fullname = userProfile.getName();
                    String age = userProfile.getAge();

                    nameTextView.setText(fullname);
                    ageTextView.setText(age);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserProfileActivity.this, "Something Wrong Happened!", Toast.LENGTH_SHORT).show();
            }
        });

        //add profile image code
        userImage = (ImageView) findViewById(R.id.userImage);
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //for uploading user image
                startActivityForResult(openGalleryIntent,1000);
            }
        });

        cameraButton = (ImageButton) findViewById(R.id.cameraButton);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openGalleryIntent2 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //for image button
                startActivityForResult(openGalleryIntent2,1);
            }
        });


        historyButton = (ImageButton) findViewById(R.id.historyButton);
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserProfileActivity.this, HistoryActivity.class));
            }
        });

        expensesButton = (ImageButton) findViewById(R.id.expensesButton);
        expensesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfileActivity.this, ShowExpenseActivity.class);

                startActivity(intent);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1000 && resultCode== RESULT_OK && data!=null && data.getData()!=null){
            Uri imageUri = data.getData();
//            userImage.setImageURI(imageUri);

            uploadImagetoFirebare(imageUri);
        }

        if(requestCode==1 && resultCode== RESULT_OK && data!=null && data.getData()!=null){
            Uri imageUri1 = data.getData();
            uploadImagetoFirebare(imageUri1);
        }

    }

    private void uploadImagetoFirebare(Uri imageUri) {
        //uploading image to firebase storage

//        String fileName = getRandomName();
        StorageReference fileref = storageReference.child(userId+".jpg");
        fileref.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(UserProfileActivity.this,"Image Uploaded",Toast.LENGTH_LONG).show();

                fileref .getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        //image uploaded and displayed using Picasso...
                        Picasso.with(getApplicationContext()).load(uri).into(userImage);

                    }
                });


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UserProfileActivity.this,"Image Upload Failed!!",Toast.LENGTH_LONG).show();
            }
        });

    }

    //generates a random file name for profile picture.
    static String getRandomName()
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString() + ".jpg";
    }



}