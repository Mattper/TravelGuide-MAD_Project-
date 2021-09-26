package com.example.mad_project.model;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.UUID;

public class AddEditHotelActivity extends AppCompatActivity implements View.OnClickListener {
    DatabaseReference databaseReference;
    Button uploadBtn;
    Button addBtn;
    EditText name;
    EditText address;
    EditText owner;
    EditText contact;
    EditText facilities;
    EditText hrn;
    HotelModel model;
    boolean add = true;
    Uri filePath;

    // request code
    private final int PICK_IMAGE_REQUEST = 22;
    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_hotel);
        name = findViewById(R.id.name);
        addBtn = findViewById(R.id.add);
        addBtn.setOnClickListener(this);
        address = findViewById(R.id.address);
        owner = findViewById(R.id.owner);
        contact = findViewById(R.id.contact);
        facilities = findViewById(R.id.facilities);
        hrn = findViewById(R.id.hrn);
        uploadBtn = findViewById(R.id.uploadBtn);
        uploadBtn.setOnClickListener(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("hotel");
        if (getIntent().hasExtra("object")) {
            model = (HotelModel) getIntent().getSerializableExtra("object");
            name.setText(model.getName());
            address.setText(model.getAddress());
            owner.setText(model.getOwner());
            contact.setText(model.getContact());
            facilities.setText(model.getFacilities());
            hrn.setText(model.getHrn());
            add = false;
        } else {
            add = true;
            model = null;

        }

        // get the Firebase  storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

    }

    // Select Image method
    private void SelectImage() {

        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }

    // Override onActivityResult method
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {

        super.onActivityResult(requestCode,
                resultCode,
                data);

        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);
            } catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }

    // UploadImage method
    private void uploadImage() {
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();


            // Defining the child of storageReference
            StorageReference ref
                    = storageReference
                    .child("images/" + UUID.randomUUID().toString());

            ref.putFile(filePath)
                    .addOnSuccessListener(taskSnapshot -> {
                        progressDialog.dismiss();
                        Toast.makeText(AddEditHotelActivity.this,
                                "Image Uploaded!!",
                                Toast.LENGTH_SHORT)
                                .show();
                        taskSnapshot
                                .getStorage()
                                .getDownloadUrl()
                                .addOnSuccessListener(new OnSuccessListener() {

                                    @Override
                                    public void onSuccess(Object o) {
                                        model.setPhoto(((Uri) o).toString());
                                        databaseReference.child(model.getId()).setValue(model);
                                        Toast.makeText(AddEditHotelActivity.this, add ? "Hotel Add" : "Hotel Update", Toast.LENGTH_LONG).show();
                                        finish();
                                    }


                                });

                    })

                    .addOnFailureListener(e -> {

                        // Error, Image not uploaded
                        progressDialog.dismiss();
                        Toast
                                .makeText(AddEditHotelActivity.this,
                                        "Failed " + e.getMessage(),
                                        Toast.LENGTH_SHORT)
                                .show();
                        finish();
                    })
                    .addOnProgressListener(
                            taskSnapshot -> {
                                double progress
                                        = (100.0
                                        * taskSnapshot.getBytesTransferred()
                                        / taskSnapshot.getTotalByteCount());
                                progressDialog.setMessage(
                                        "Uploaded "
                                                + (int) progress + "%");
                            });
        }
    }

    @Override
    public void onClick(View v) {

        if (name.getText().toString().isEmpty() || address.getText().toString().isEmpty()|| owner.getText().toString().isEmpty() ||contact.getText().toString().isEmpty() || facilities.getText().toString().isEmpty() || hrn.getText().toString().isEmpty() ){
            Toast.makeText(this,"Fil All Fields",Toast.LENGTH_LONG).show();
            return;
        }
        if (v.getId() == addBtn.getId()) {
            if (add) {
                model = new HotelModel(databaseReference.push().getKey(), name.getText().toString(), address.getText().toString(), owner.getText().toString(), contact.getText().toString(), facilities.getText().toString(), hrn.getText().toString());

            } else {
                model.update(name.getText().toString(), address.getText().toString(), owner.getText().toString(), contact.getText().toString(), facilities.getText().toString(), hrn.getText().toString());
            }
            uploadImage();

            if (filePath == null) {

                databaseReference.child(model.getId()).setValue(model);
                Toast.makeText(AddEditHotelActivity.this, add ? "Hotel Add" : "Hotel Update", Toast.LENGTH_LONG).show();
                finish();

            }
        } else {
            SelectImage();
        }
    }

}