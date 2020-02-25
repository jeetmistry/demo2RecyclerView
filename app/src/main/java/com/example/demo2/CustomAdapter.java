package com.example.demo2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;

import static java.text.NumberFormat.*;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference rootRef,userRef;
    private long number;
    private int num;
    private static RecyclerView recyclerView;

    private ArrayList<StudentProfile> dataset,data;


    public CustomAdapter(ArrayList<StudentProfile> dataset) {
        this.dataset = dataset;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        TextView name = holder.names;
        firebaseDatabase = FirebaseDatabase.getInstance();
        rootRef = firebaseDatabase.getReference();
        userRef = rootRef.child("Users");
//        rootRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    Number = (int) dataSnapshot.getChildrenCount();
//                    rootRef.g
//                } else {
//                    Log.d("Cannot retrieve item count","No Snapshot found");
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String usernames = (String) dataSnapshot.child("username").getValue();
                    String name = (String) dataSnapshot.child("name").getValue();
                    holder.names.setText("Name : " +name);
                    holder.username.setText("Username : "+ usernames);
                    number = dataSnapshot.getChildrenCount();
                }
            }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

    }



        @Override
        public int getItemCount() {
        int i = (int) number;
           return 9;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView names,username,num;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.names = (TextView) itemView.findViewById(R.id.name_text);
            this.username = (TextView) itemView.findViewById(R.id.username_text);

        }
    }

}

