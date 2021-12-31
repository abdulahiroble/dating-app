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

import java.util.HashMap;
import java.util.Map;

public class editprofile extends AppCompatActivity {

    Intent data;

    EditText medittitleofnote, meditcontentofnote;
    FloatingActionButton msaveeditnote;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        medittitleofnote = findViewById(R.id.edittitleofnote);
        meditcontentofnote = findViewById(R.id.editcontentofnote);
        msaveeditnote = findViewById(R.id.saveeditnote);

        data = getIntent();

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        msaveeditnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newtitle = medittitleofnote.getText().toString();

                if (newtitle.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Something is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    DocumentReference documentReference = firebaseFirestore.collection("users").document(firebaseUser.getUid()).collection("user").document(data.getStringExtra("noteId"));

                    Map <String, Object> note = new HashMap<>();

                    note.put("firstname", newtitle);

                    documentReference.set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(), "Note updated", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(editprofile.this, profile.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Failed to updated", Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }
        });

        String notetitle = data.getStringExtra("firstname");
        medittitleofnote.setText(notetitle);





    }



}