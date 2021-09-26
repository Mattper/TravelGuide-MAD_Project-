package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LogInActivity extends AppCompatActivity {
    private EditText mEmail,mPassword;
    private Button mLoginBtn;
    private TextView mRegisterLink;

    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail =findViewById(R.id.tv_login_email);
        mPassword =findViewById(R.id.tv_login_pwd);
        mLoginBtn =findViewById(R.id.btn_login);
        mRegisterLink =findViewById(R.id.tv_login_signUpLink);

        mAuth =FirebaseAuth.getInstance();
        fStore =FirebaseFirestore.getInstance();
        mProgressBar =findViewById(R.id.login_progressBar);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=mEmail.getText().toString().trim();
                String password=mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required!");
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required!!");
                    return;
                }

                if (password.length()<6){
                    mPassword.setError("Password must be more than six characters");
                    return;
                }

                mProgressBar.setVisibility(View.VISIBLE);

                //Authenticate User
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull  Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LogInActivity.this,"Login Successful!",Toast.LENGTH_SHORT).show();
                            checkUserAccessLevel(mAuth.getUid());
                            //startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }else{
                            Toast.makeText(LogInActivity.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        mRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });

    }

    private void checkUserAccessLevel(String uId){
        DocumentReference documentReference =fStore.collection("Users").document(uId);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.getString("isAdmin") != null){
                    startActivity(new Intent(getApplicationContext(),AdminDashboard.class));
                    finish();
                }

                if (documentSnapshot.getString("isUser") != null){
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }
            }
        });
    }



}