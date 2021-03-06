package com.example.mad_project.model;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button login;
    EditText username;
    EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.B1);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(this, com.example.hotel.HotelActivity.class);
        if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
            intent.putExtra("isAdmin", true);
        } else {
            intent.putExtra("isAdmin", false);
        }
        startActivity(intent);
    }
}