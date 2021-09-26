package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private ImageView mIVHotels,mIVArticles,mIVServices,mIVBookings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIVHotels =findViewById(R.id.main_hotels);
        mIVArticles =findViewById(R.id.main_articles);
        mIVServices =findViewById(R.id.main_services);
        mIVBookings =findViewById(R.id.main_bookings);


        mIVHotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Hotels list", Toast.LENGTH_SHORT).show();
            }
        });

        mIVArticles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ArticlesListActivity.class));
            }
        });


        mIVServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddServices.class));
            }
        });


        mIVBookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),BookingList.class));
            }
        });


    }

    public void viewUserProfile(View view){
        startActivity(new Intent(getApplicationContext(),UserProfile.class));
    }



}