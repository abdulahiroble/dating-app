package com.example.gamingnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class userinfo extends AppCompatActivity {

    private EditText  mfirstname, mlastname, mage, mabout;
    private Button msignup;
    private TextView mgotologin;

    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);


        getSupportActionBar().hide();

        msignup = (Button) findViewById(R.id.signupbutton);
        mgotologin = findViewById(R.id.gotologin);
        mfirstname = findViewById(R.id.signupfirstname);
        mlastname = findViewById(R.id.signuplastname);
        mage = findViewById(R.id.signupage);
        mabout = findViewById(R.id.signupabout);

        firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        msignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstname = mfirstname.getText().toString().trim();
                String lastname = mlastname.getText().toString().trim();
                String age = mage.getText().toString().trim();
                String about = mabout.getText().toString().trim();


                if (firstname.isEmpty() || lastname.isEmpty() || age.isEmpty() ||about.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else
                {


                    DocumentReference documentReference = firebaseFirestore.collection("users").document(firebaseUser.getUid()).collection("user").document();
                    Map<String, Object> note = new HashMap<>();
                    note.put("firstname", firstname);

                    documentReference.set(note).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getApplicationContext(), "User created succesfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(userinfo.this, notesactivity.class));

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Failed to create user", Toast.LENGTH_SHORT).show();
                            // startActivity(new Intent(createnote.this, notesactivity.class));
                        }
                    });



                }
            }
        });
    }
}