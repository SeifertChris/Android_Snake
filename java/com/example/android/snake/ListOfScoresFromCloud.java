package com.example.android.snake;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by chris on 3/11/2017.
 */

public class ListOfScoresFromCloud extends Activity {


    ListView listFromCloud;
    DatabaseReference myRef;
    ArrayList<String> list = new ArrayList<>();
    ArrayAdapter<String> adapter;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_from_cloud);
        myRef = FirebaseDatabase.getInstance().getReference().child("Top Score");
        listFromCloud = (ListView) findViewById(R.id.list_from_cloud);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listFromCloud.setAdapter(adapter);
        myRef.keepSynced(true);

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String value = dataSnapshot.getValue(String.class);
                list.add(value);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                list.add(value);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                list.add(value);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                finish();
                startActivity(new Intent(ListOfScoresFromCloud.this, Snake.class));
            }
        };

        Timer opening = new Timer();
        opening.schedule(timerTask, 10000);

    }


}
