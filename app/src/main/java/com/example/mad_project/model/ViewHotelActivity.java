package com.example.mad_project.model;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ViewHotelActivity extends AppCompatActivity implements View.OnClickListener {
    TextView name;
    TextView address;
    TextView owner;
    TextView contact;
    TextView facilities;
    TextView hrn;
    com.example.hotel.HotelModel model;
    Button delete;
    Button edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hotel);
        name = findViewById(R.id.viewname);
        address = findViewById(R.id.viewaddress);
        owner = findViewById(R.id.viewowner);
        contact = findViewById(R.id.viewcontact);
        facilities = findViewById(R.id.viewfacilities);

        model = (com.example.hotel.HotelModel) getIntent().getSerializableExtra("object");
        model = (com.example.hotel.HotelModel) getIntent().getSerializableExtra("object");
        name.setText(model.getName());
        address.setText(model.getAddress());
        owner.setText(model.getOwner());
        contact.setText(model.getContact());
        facilities.setText(model.getFacilities());

        delete = findViewById(R.id.deleteBtn);
        edit = findViewById(R.id.modiBtn);
        delete.setOnClickListener(this);
        edit.setOnClickListener(this);
        ImageView imageView = findViewById(R.id.vixId);
        if(model.getPhoto()!=null){
            if (!model.getPhoto().isEmpty()){
                Picasso.get().load(model.getPhoto()).into(imageView);
            }
        }
        if (getIntent().getBooleanExtra("isAdmin", true)) {
            delete.setVisibility(View.VISIBLE);
            edit.setVisibility(View.VISIBLE);
        } else {
            delete.setVisibility(View.GONE);
            edit.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.deleteBtn) {
            FirebaseDatabase.getInstance().getReference("hotel").child(model.getId()).removeValue();
            Toast.makeText(this, "Event Delete", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Intent intent = new Intent(this, AddEditHotelActivity.class);
            intent.putExtra("object", model);
            startActivity(intent);
            finish();
        }

    }
}