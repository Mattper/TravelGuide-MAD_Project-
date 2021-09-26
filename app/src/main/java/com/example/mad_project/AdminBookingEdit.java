package com.example.mad_project;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AdminBookingEdit extends AppCompatActivity {
    private EditText mFullname, mNIC, mMobilecontact, mHomecontact, mEmail, mNoofparticipants;
    Button next;
    private FirebaseAuth mAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        String bookingid = extras.getString("bookingid");
        setContentView(R.layout.activity_customer_details);
        mFullname = findViewById(R.id.editTextname);
        mNIC = findViewById(R.id.editTextnic);
        mEmail = findViewById(R.id.editTextemail);
        mMobilecontact = findViewById(R.id.editTextmobile);
        mHomecontact = findViewById(R.id.editTexthome);
        mNoofparticipants = findViewById(R.id.editTextparticipants);
        next = findViewById(R.id.save);
        fStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        fStore.collection("Bookings").document(bookingid)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot document = task.getResult();
                mFullname.setText(document.get("name").toString());
                mNIC.setText(document.get("nic").toString());
                mEmail.setText(document.get("email").toString());
                mMobilecontact.setText(document.get("mobile").toString());
                mHomecontact.setText(document.get("home").toString());
                mNoofparticipants.setText(document.get("participants").toString());
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mFullname.getText().toString();
                String nic = mNIC.getText().toString();
                String mobile = mMobilecontact.getText().toString();
                String home = mHomecontact.getText().toString();
                String email = mEmail.getText().toString().trim();
                String participants = mNoofparticipants.getText().toString();

                if (TextUtils.isEmpty(nic)) {
                    mNIC.setError("NIC Number is required!!");
                    return;
                }
                if (TextUtils.isEmpty(mobile)) {
                    mMobilecontact.setError("Mobile Number is required!!");
                    return;
                }
                if (TextUtils.isEmpty(participants)) {
                    mNoofparticipants.setError("Number of participants is required!!");
                    return;
                }

                String userId = mAuth.getCurrentUser().getUid();
                DocumentReference documentReference = fStore.collection("Bookings").document(bookingid);
                Map<String, Object> bookings = new HashMap<>();
                bookings.put("customerID", userId);
                bookings.put("name", name);
                bookings.put("nic", nic);
                bookings.put("mobile", mobile);
                bookings.put("home", home);
                bookings.put("email", email);
                bookings.put("participants", participants);
                documentReference.update(bookings).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "onSuccess: booking is created for " + name);
                        Toast.makeText(AdminBookingEdit.this, "Booking Updated", Toast.LENGTH_SHORT).show();
                    }
                });
                startActivity(new Intent(getApplicationContext(), BookingList.class));

//                } catch (Exception e) {
//                    Toast.makeText(Booking.this, "Error !" + e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }
}