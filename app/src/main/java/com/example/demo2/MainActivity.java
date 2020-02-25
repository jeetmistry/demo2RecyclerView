package com.example.demo2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText edit,user;
    Button submit, viewRecycler;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference rootRef, userref;
    String Name,Username;
    int number;
    TextView num;
    ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit =  findViewById(R.id.editText);
        user = findViewById(R.id.username);
        submit = findViewById(R.id.submitbutton);
        viewRecycler = findViewById(R.id.viewRecycler);
        progress = findViewById(R.id.progress);
        num = (TextView) findViewById(R.id.numberofChildern);

        firebaseDatabase = FirebaseDatabase.getInstance();
        rootRef = firebaseDatabase.getReference();
        userref = rootRef.child("Users");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.VISIBLE);
                Name = edit.getText().toString();
                Username = user.getText().toString();
                StudentProfile sp = new StudentProfile(Username,Name);
                userref.setValue(sp).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        progress.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "Name " + Name + " added to database", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        rootRef.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Long number =  dataSnapshot.getChildrenCount();
                num.setText(Long.toString(number));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        viewRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recycler = new Intent(MainActivity.this,RecyclerActivity.class);
                startActivity(recycler);
            }
        });
    }


}
