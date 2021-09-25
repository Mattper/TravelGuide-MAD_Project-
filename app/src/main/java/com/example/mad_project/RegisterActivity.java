package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class RegisterActivity extends AppCompatActivity {
    private EditText mFullname,mEmail,mPassword,mUsername;
    Button mRegisterBtn;
    private FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    private ProgressBar mProgressBar;
    private String userId;
    TextView mLoginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFullname =findViewById(R.id.tv_register_fullName);
        mEmail= findViewById(R.id.tv_register_email);
        mUsername= findViewById(R.id.tv_register_uName);
        mPassword=findViewById(R.id.tv_register_pwd);
        mRegisterBtn= findViewById(R.id.btn_register_signUp);
        mLoginLink = findViewById(R.id.tv_register_signInLink);

        fStore =FirebaseFirestore.getInstance();
        mAuth= FirebaseAuth.getInstance();
        mProgressBar= findViewById(R.id.register_progressBar);

        if (mAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=mEmail.getText().toString().trim();
                String password=mPassword.getText().toString().trim();
                String fName =mFullname.getText().toString();
                String uName =mUsername.getText().toString();

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

                //add user to the firebase
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this,"User created",Toast.LENGTH_SHORT).show();

                            userId =mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("Users").document(userId);
                            Map<String,Object> user =new HashMap<>();
                            user.put("fName",fName);
                            user.put("Uname",uName);
                            user.put("email",email);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG, "onSuccess: user Profile is created for " + userId);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }else{
                            Toast.makeText(RegisterActivity.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        mLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LogInActivity.class));
            }
        });

    }
}