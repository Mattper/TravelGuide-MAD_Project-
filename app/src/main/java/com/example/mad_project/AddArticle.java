package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.UUID;

public class AddArticle extends AppCompatActivity {
    private EditText mTitle,mAuthorName,mArticle;
    private Button mBtnSubmit;
    private FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);

        mTitle =findViewById(R.id.tv_article_title);
        mAuthorName =findViewById(R.id.tv_article_authorName);
        mArticle =findViewById(R.id.tv_article);
        mBtnSubmit =findViewById(R.id.btn_submit);

        fStore =FirebaseFirestore.getInstance();

        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title =mTitle.getText().toString();
                String authorName =mAuthorName.getText().toString();
                String article =mArticle.getText().toString();
                String id = UUID.randomUUID().toString();

                SaveToFireStore(id , title ,authorName ,article);
            }
        });

    }

    private void SaveToFireStore(String id,String title,String authorName,String article){

        if (!title.isEmpty() && !article.isEmpty() && !authorName.isEmpty()){
            HashMap<String,Object> map =new HashMap<>();
            map.put("ArticleId",id);
            map.put("ArticleTitle",title);
            map.put("AuthorName",authorName);
            map.put("Article",article);

            fStore.collection("Articles").document(id).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(AddArticle.this, "Data Saved", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddArticle.this, "Failed!!!", Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Toast.makeText(this, "Empty Fields are not Allowed!!", Toast.LENGTH_SHORT).show();
        }
    }




}