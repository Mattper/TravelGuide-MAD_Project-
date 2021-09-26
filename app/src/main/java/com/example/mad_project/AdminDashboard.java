package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class AdminDashboard extends AppCompatActivity {
    CardView mCardHotel;
    CardView mCardService;
    CardView mCardArticle;
    CardView mCardBookings;
    Button mLogoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        mLogoutBtn =findViewById(R.id.btn_admin_logout);

        mCardHotel =findViewById(R.id.card_hotel);
        mCardService =findViewById(R.id.card_service);
        mCardArticle =findViewById(R.id.card_article);
        mCardBookings =findViewById(R.id.card_bookings);

        //on click listener for Hotels
        mCardHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminDashboard.this, "Hotels admin", Toast.LENGTH_SHORT).show();
            }
        });

        //on click listener for Services
        mCardService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminDashboard.this, "Services admin", Toast.LENGTH_SHORT).show();
            }
        });

        //on click listener for Articles
        mCardArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AdminArticleList.class));
            }
        });

        //on click listener for Bookings
        mCardBookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AdminBookingList.class));
            }
        });



        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LogInActivity.class));
                finish();
            }
        });
    }
}