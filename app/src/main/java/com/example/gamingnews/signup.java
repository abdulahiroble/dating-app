package com.example.gamingnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {

    private EditText msignupemail, msignuppasword, mfirstname, mlastname, mage, mabout;
    private Button msignup;
    private TextView mgotologin;



    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().hide();

        msignupemail = findViewById(R.id.signupemail);
        msignuppasword = findViewById(R.id.signuppassword);
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

        mgotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup.this, MainActivity.class);
                startActivity(intent);
            }
        });



        msignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail = msignupemail.getText().toString().trim();
                String password = msignuppasword.getText().toString().trim();
                String firstname = mfirstname.getText().toString().trim();
                String lastname = mlastname.getText().toString().trim();
                String age = mage.getText().toString().trim();
                String about = mabout.getText().toString().trim();


                if (mail.isEmpty() || password.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || age.isEmpty() || about.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else if (password.length() < 7)
                {
                    Toast.makeText(getApplicationContext(), "Password should be greater than 7 digits", Toast.LENGTH_SHORT).show();
                }
                else
                {


                    // Register the user to to firebase
                    firebaseAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(), "Registration is successfull", Toast.LENGTH_SHORT).show();

                                sendEmailVerification();

                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Failed to register", Toast.LENGTH_SHORT).show();
                            }

                        }

                    });

                    Map<String, Object> user = new HashMap<>();
                    user.put("email", mail);
                    user.put("firstname", firstname);
                    user.put("lastname", lastname);
                    user.put("age", age);
                    user.put("about", about);

                    firebaseFirestore.collection("users").document()
                            .set(user)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG", "DocumentSnapshot successfully written!");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("TAG", "Error writing document", e);
                                }
                            });




                }
            }
        });

    }

    // Send email verification
    private void sendEmailVerification()
    {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null)
        {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getApplicationContext(), "Verification email is sent, verify and log in again", Toast.LENGTH_SHORT).show();
                    firebaseAuth.signOut();
                    finish();
                    startActivity(new Intent(signup.this, MainActivity.class));
                }
            });
        }

        else
        {
            Toast.makeText(getApplicationContext(), "Failed to send verification email", Toast.LENGTH_SHORT).show();
        }
    }
}