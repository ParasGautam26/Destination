package com.example.destination;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<MainViewModel> mlist;
   // private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private MyAdapter mMyAdapter;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*mProgressBar = findViewById(R.id.progressbar);
        mProgressBar.setVisibility(View.VISIBLE);*/
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mlist = new ArrayList<>();
        mMyAdapter = new MyAdapter(this,mlist);
        mRecyclerView.setAdapter(mMyAdapter);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("New Folder");
        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    for(DataSnapshot ds:snapshot.getChildren()){
                        String companyname = ds.child("company").getValue(String.class);
                        String profile  = ds.child("profile").getValue(String.class);
                        String link = ds.child("link").getValue(String.class);
                        String desc = ds.child("stipend").getValue(String.class);
                        String thumb = ds.child("img").getValue(String.class);
                        MainViewModel m = new MainViewModel(companyname,link,desc,profile,thumb);
                        mlist.add(m);
                    }
                    mMyAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}
