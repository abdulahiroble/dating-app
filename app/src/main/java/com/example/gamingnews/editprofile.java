package com.example.gamingnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class editprofile extends AppCompatActivity {

    Intent data;

    EditText meditfirstname, meditlastname, meditage, meditabout;
    FloatingActionButton msaveeditnote;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        meditfirstname = findViewById(R.id.editfirstname);
        meditlastname = findViewById(R.id.editlastname);
        meditage = findViewById(R.id.editage);
        meditabout = findViewById(R.id.editabout);
        msaveeditnote = findViewById(R.id.saveeditnote);

        data = getIntent();

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        msaveeditnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newtitle = meditfirstname.getText().toString();
                String newlastname = meditlastname.getText().toString();
                String newAge = meditage.getText().toString();
                String newAbout = meditabout.getText().toString();

                if (newtitle.isEmpty() || newlastname.isEmpty() || newAge.isEmpty() || newAbout.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Something is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    DocumentReference documentReference = firebaseFirestore.collection("users").document(firebaseUser.getUid()).collection("user").document(data.getStringExtra("noteId"));

                    Map <String, Object> note = new HashMap<>();

                    note.put("firstname", newtitle);
                    note.put("lastname", newlastname);
                    note.put("age", newAge);
                    note.put("about", newAbout);

                    documentReference.set(note, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(), "Profile updated", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(editprofile.this, profile.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Failed to update", Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }
        });

        String notetitle = data.getStringExtra("firstname");
        String lastname = data.getStringExtra("lastname");
        String age = data.getStringExtra("age");
        String about = data.getStringExtra("about");

        meditfirstname.setText(notetitle);
        meditlastname.setText(lastname);
        meditage.setText(age);
        meditabout.setText(about);





    }



}