package com.iiitd.mc.travelguideapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.iiitd.mc.travelguideapplication.model.AppUser;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{


    private FirebaseAuth mAuth;

    private Button signupButton;
    private EditText editTextName, editTextAge, editTextEmail, editTextPassword;
    private ProgressBar registerProgressBar;

    private TextView tv_accountAlreadyExists;
    private ImageView roveRegisterPageLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        roveRegisterPageLogo = (ImageView) findViewById(R.id.roveRegisterPageLogo);
        roveRegisterPageLogo.setOnClickListener(this);

        signupButton = (Button) findViewById(R.id.signupButton);
        signupButton.setOnClickListener(this);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextAge = (EditText) findViewById(R.id.editTextAge);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        tv_accountAlreadyExists = findViewById(R.id.accountAlreadyExists);
        tv_accountAlreadyExists.setOnClickListener(this);

        registerProgressBar = (ProgressBar) findViewById(R.id.registerProgressBar);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.roveRegisterPageLogo:
                startActivity(new Intent(this, HomeScreenActivity.class));
                break;

            case R.id.signupButton:
                registerUser();
                break;

            case R.id.accountAlreadyExists:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                break;
        }
    }

    private void registerUser() {

        String name = editTextName.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(name.isEmpty()){
            editTextName.setError("Name is Required");
            editTextName.requestFocus();
            return;
        }

        if(age.isEmpty()){
            editTextAge.setError("Age is Required");
            editTextAge.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editTextEmail.setError("Email is Required");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please! Provide Valid Email");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is Required");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length()<6){
            editTextPassword.setError("Min Password length should be 6");
            editTextPassword.requestFocus();
            return;
        }

        registerProgressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            AppUser user = new AppUser(name,age, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "User has been registered successfully", Toast.LENGTH_LONG).show();
                                        registerProgressBar.setVisibility(View.GONE);
                                        startActivity(new Intent(RegisterActivity.this, UserProfileActivity.class));
                                    }
                                    else{
                                        Toast.makeText(RegisterActivity.this, "Failed to Register! Try Again", Toast.LENGTH_LONG).show();
                                        registerProgressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(RegisterActivity.this, "Failed to Register! Try Again", Toast.LENGTH_LONG).show();
                            registerProgressBar.setVisibility(View.GONE);
                        }
                    }
                });


    }
}