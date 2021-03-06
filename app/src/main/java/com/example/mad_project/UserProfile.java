package com.example.mad_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class UserProfile extends AppCompatActivity {

    TextView mFname,mEmail,mUname;
    Button mBtnLogout;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        mFname =findViewById(R.id.tv_UProfile_fname);
        mEmail =findViewById(R.id.tv_Uprofile_email);
        mUname =findViewById(R.id.tv_UProfile_uname);
        mBtnLogout =findViewById(R.id.btn_logout);

        fAuth =FirebaseAuth.getInstance();
        fStore =FirebaseFirestore.getInstance();
        userId =fAuth.getCurrentUser().getUid();



        DocumentReference documentReference =fStore.collection("Users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    mFname.setText(value.getString("fName"));
                    mEmail.setText(value.getString("email"));
                    mUname.setText(value.getString("Uname"));
            }
        });

        mBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fAuth.signOut();
                Toast.makeText(UserProfile.this, "User Logged Out.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),LogInActivity.class));

            }
        });

    }

}