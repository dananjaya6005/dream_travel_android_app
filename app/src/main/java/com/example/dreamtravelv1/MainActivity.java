package com.example.dreamtravelv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import com.example.dreamtravelv1.MapsActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;


    //firebase

    private DatabaseReference myRef;

    //vairables
    private ArrayList<Messages> messagesList;
    private RecyclerAdapter recyclerAdapter;
    private Context mContext;


    ViewPager viewPager;
    ArrayList<Integer> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //code for hotel,plane,food,random btn

        FloatingActionButton Open_time_convertor = findViewById(R.id.Time_convertor);
        Open_time_convertor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Time_convert.class));
            }
        });


        FloatingActionButton OpenLocal_service = findViewById(R.id.local_service);
        OpenLocal_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),Local_data.class));
            }
        });


        FloatingActionButton OpenFood = findViewById(R.id.food_btn);
        OpenFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.google.com/maps/search/Restaurants/@6.9109381,79.8851072,13z/data=!3m1!4b1";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        FloatingActionButton OpneRandom = findViewById(R.id.random_btn);
        OpneRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),weather_view.class));
            }
        });



        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //firebase
        myRef = FirebaseDatabase.getInstance().getReference();

        //Arraylist
        messagesList = new ArrayList<>();

        //clear  Aray list
        ClearAll();

        //get Data from method
        GetDataFromFirebase();


        // Create intent to start MapActivity
        //Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        //Intent intent = new Intent(MainActivity.this, backend_data.class);
        // Start MapActivity
        //startActivity(intent);



        viewPager = findViewById(R.id.viewPager);
        arrayList.add(R.drawable.img1);
        arrayList.add(R.drawable.img2);
        arrayList.add(R.drawable.img3);
        arrayList.add(R.drawable.img4);
        arrayList.add(R.drawable.img5);

        MyAdapter myAdapter = new MyAdapter(MainActivity.this, arrayList);
        viewPager.setAdapter(myAdapter);


        // Set up the bottom navigation view
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.home:
                        return true;

                    case R.id.location:
                        startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), search.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.compass:
                        startActivity(new Intent(getApplicationContext(), compass.class));
                        overridePendingTransition(0, 0);
                        return true;
                    default:
                        return false;
                }
            }
        });


        

    }



    private void GetDataFromFirebase() {
        Query query = myRef.child("message");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ClearAll();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Messages messages = new Messages();

                    messages.setImageUrl(snapshot.child("image").getValue().toString());
                    messages.setName(snapshot.child("name").getValue().toString());
                    messages.setBody(snapshot.child("body").getValue().toString());
                    messagesList.add(messages);
                }

                recyclerAdapter = new RecyclerAdapter(getApplicationContext(),messagesList);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private  void ClearAll(){
        if(messagesList != null){
            messagesList.clear();

            if(recyclerAdapter != null){
                recyclerAdapter.notifyDataSetChanged();
            }
        }
        messagesList = new ArrayList<>();
    }
}










