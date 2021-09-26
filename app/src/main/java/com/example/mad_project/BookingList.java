package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class BookingList extends AppCompatActivity {
    Button button1, button2, button3, button4, button5, button6, button7, addnew,edit1,edit2,edit3,edit4,edit5,edit6,edit7;
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7;
    ListView listView1;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore fStore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);
        button1 = findViewById(R.id.delete1);
        button2 = findViewById(R.id.delete2);
        button3 = findViewById(R.id.delete3);
        button4 = findViewById(R.id.delete4);
        button5 = findViewById(R.id.delete5);
        button6 = findViewById(R.id.delete6);
        button7 = findViewById(R.id.delete7);
        addnew =  findViewById(R.id.addnew);
        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        edit3 = findViewById(R.id.edit3);
        edit4 = findViewById(R.id.edit4);
        edit5 = findViewById(R.id.edit5);
        edit6 = findViewById(R.id.edit6);
        edit7 = findViewById(R.id.edit7);

        textView1 = findViewById(R.id.booking1);
        textView2 = findViewById(R.id.booking2);
        textView3 = findViewById(R.id.booking3);
        textView4 = findViewById(R.id.booking4);
        textView5 = findViewById(R.id.booking5);
        textView6 = findViewById(R.id.booking6);
        textView7 = findViewById(R.id.booking7);

        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);
        button4.setVisibility(View.INVISIBLE);
        button5.setVisibility(View.INVISIBLE);
        button6.setVisibility(View.INVISIBLE);
        button7.setVisibility(View.INVISIBLE);

        edit1.setVisibility(View.INVISIBLE);
        edit2.setVisibility(View.INVISIBLE);
        edit3.setVisibility(View.INVISIBLE);
        edit4.setVisibility(View.INVISIBLE);
        edit5.setVisibility(View.INVISIBLE);
        edit6.setVisibility(View.INVISIBLE);
        edit7.setVisibility(View.INVISIBLE);

        textView1.setVisibility(View.INVISIBLE);
        textView2.setVisibility(View.INVISIBLE);
        textView3.setVisibility(View.INVISIBLE);
        textView4.setVisibility(View.INVISIBLE);
        textView5.setVisibility(View.INVISIBLE);
        textView6.setVisibility(View.INVISIBLE);
        textView7.setVisibility(View.INVISIBLE);

        addnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Booking.class));
            }
        });

        String userId = mAuth.getCurrentUser().getUid();
        CollectionReference citiesRef = fStore.collection("Bookings");
        Query stateQuery = citiesRef.whereEqualTo("customerID", "userId");
        fStore.collection("Bookings")
                .whereEqualTo("customerID", userId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int row = 1;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                switch (row) {
                                    case 1:
                                        textView1.setVisibility(View.VISIBLE);
                                        textView1.setText(document.getId());
                                        button1.setVisibility(View.VISIBLE);
                                        edit1.setVisibility(View.VISIBLE);
                                        break;
                                    case 2:
                                        textView2.setVisibility(View.VISIBLE);
                                        textView2.setText(document.getId());
                                        button2.setVisibility(View.VISIBLE);
                                        edit2.setVisibility(View.VISIBLE);
                                        break;
                                    case 3:
                                        textView3.setVisibility(View.VISIBLE);
                                        textView3.setText(document.getId());
                                        button3.setVisibility(View.VISIBLE);
                                        edit3.setVisibility(View.VISIBLE);
                                        break;
                                    case 4:
                                        textView4.setVisibility(View.VISIBLE);
                                        textView4.setText(document.getId());
                                        button4.setVisibility(View.VISIBLE);
                                        edit4.setVisibility(View.VISIBLE);
                                        break;
                                    case 5:
                                        textView5.setVisibility(View.VISIBLE);
                                        textView5.setText(document.getId());
                                        button5.setVisibility(View.VISIBLE);
                                        edit5.setVisibility(View.VISIBLE);
                                        break;
                                    case 6:
                                        textView6.setVisibility(View.VISIBLE);
                                        textView6.setText(document.getId());
                                        button6.setVisibility(View.VISIBLE);
                                        edit6.setVisibility(View.VISIBLE);
                                        break;
                                    case 7:
                                        textView7.setVisibility(View.VISIBLE);
                                        textView7.setText(document.getId());
                                        button7.setVisibility(View.VISIBLE);
                                        edit7.setVisibility(View.VISIBLE);
                                        break;

                                }

                                row++;
                            }
                        } else {
                            textView1.setVisibility(View.VISIBLE);
                            textView1.setText("No Bookings Found");
                        }

                    }


                });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookingid = textView1.getText().toString().trim();
                deleteBooking(bookingid);
                startActivity(new Intent(getApplicationContext(),BookingList.class));

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookingid = textView2.getText().toString().trim();
                deleteBooking(bookingid);
                startActivity(new Intent(getApplicationContext(),BookingList.class));

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookingid = textView3.getText().toString().trim();
                deleteBooking(bookingid);
                startActivity(new Intent(getApplicationContext(),BookingList.class));

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookingid = textView4.getText().toString().trim();
                deleteBooking(bookingid);
                startActivity(new Intent(getApplicationContext(),BookingList.class));

            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookingid = textView5.getText().toString().trim();
                deleteBooking(bookingid);
                startActivity(new Intent(getApplicationContext(),BookingList.class));

            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookingid = textView6.getText().toString().trim();
                deleteBooking(bookingid);
                startActivity(new Intent(getApplicationContext(),BookingList.class));

            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookingid = textView7.getText().toString().trim();
                deleteBooking(bookingid);
                startActivity(new Intent(getApplicationContext(),BookingList.class));

            }
        });

        edit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookingid = textView1.getText().toString().trim();
                Intent i = new Intent(getApplicationContext(),BookingEdit.class);
                i.putExtra("bookingid",bookingid);
                startActivity(i);

            }
        });
        edit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookingid = textView2.getText().toString().trim();
                Intent i = new Intent(getApplicationContext(),BookingEdit.class);
                i.putExtra("bookingid",bookingid);
                startActivity(i);

            }
        });
        edit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookingid = textView3.getText().toString().trim();
                Intent i = new Intent(getApplicationContext(),BookingEdit.class);
                i.putExtra("bookingid",bookingid);
                startActivity(i);

            }
        });
        edit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookingid = textView4.getText().toString().trim();
                Intent i = new Intent(getApplicationContext(),BookingEdit.class);
                i.putExtra("bookingid",bookingid);
                startActivity(i);

            }
        });
        edit5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookingid = textView5.getText().toString().trim();
                Intent i = new Intent(getApplicationContext(),BookingEdit.class);
                i.putExtra("bookingid",bookingid);
                startActivity(i);

            }
        });
        edit6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookingid = textView6.getText().toString().trim();
                Intent i = new Intent(getApplicationContext(),BookingEdit.class);
                i.putExtra("bookingid",bookingid);
                startActivity(i);

            }
        });
        edit7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookingid = textView7.getText().toString().trim();
                Intent i = new Intent(getApplicationContext(),BookingEdit.class);
                i.putExtra("bookingid",bookingid);
                startActivity(i);

            }
        });





    }

    public void deleteBooking(String id) {
        fStore.collection("Bookings").document(id)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(BookingList.this, "Booking Deleted", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(BookingList.this, "Error deleting booking :" + e, Toast.LENGTH_SHORT).show();
                    }
                });

    }
}