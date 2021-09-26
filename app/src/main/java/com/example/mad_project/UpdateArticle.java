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

public class UpdateArticle extends AppCompatActivity {

    private EditText mTitle,mAuthorName,mArticle;
    private Button mBtnUpdate;
    private FirebaseFirestore fStore;

    private String uId,uTitle,uAuthorName,uArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_article);

        mTitle =findViewById(R.id.tv_admin_article_title);
        mAuthorName =findViewById(R.id.tv_admin_article_authorName);
        mArticle =findViewById(R.id.tv_admin_article);
        mBtnUpdate =findViewById(R.id.btn_update);

        fStore =FirebaseFirestore.getInstance();

        Bundle bundle =getIntent().getExtras();
        if (bundle != null){
            uId = bundle.getString("ArticleId");
            uTitle =bundle.getString("ArticleTitle");
            uAuthorName =bundle.getString("AuthorName");
            uArticle =bundle.getString("Article");
            mTitle.setText(uTitle);
            mAuthorName.setText(uAuthorName);
            mArticle.setText(uArticle);
        }else{
            Toast.makeText(this, "Something went wrong!!!", Toast.LENGTH_SHORT).show();
        }

        mBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id =uId;
                String title =mTitle.getText().toString();
                String authorName =mAuthorName.getText().toString();
                String article =mArticle.getText().toString();


                if (id != null){
                    updateToFireStore(id,title,authorName,article);
                }else{
                    Toast.makeText(UpdateArticle.this, "Id is null!!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void updateToFireStore(String id, String title, String authorName,String article) {
        fStore.collection("Articles").document(id).update("ArticleTitle",title,
                "AuthorName",authorName,
                "Article",article)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(UpdateArticle.this, "Article Updated!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(UpdateArticle.this, "Article Update failed!!!", Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateArticle.this,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}