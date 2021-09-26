package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_project.model.Article;

public class ArticleDetails extends AppCompatActivity {

    private TextView mTitle,mAuthorName,mArticle;
    private String uId,uTitle,uAuthorName,uArticle;
    
    TextView title,authorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);

        mTitle =findViewById(R.id.tv_adetails_title);
        mAuthorName =findViewById(R.id.tv_adetails_authorName);
        mArticle =findViewById(R.id.tv_adetails_article);

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
    }
}