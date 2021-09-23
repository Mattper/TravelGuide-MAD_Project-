package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private ImageView mIVHotels,mIVArticles,mIVServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIVHotels =findViewById(R.id.main_hotels);
        mIVArticles =findViewById(R.id.main_articles);
        mIVServices =findViewById(R.id.main_services);

        mIVArticles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ArticlesListActivity.class));
            }
        });




    }

    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),LogInActivity.class));
        finish();
    }


}