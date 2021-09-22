package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mad_project.adapter.ArticleAdapter;
import com.example.mad_project.model.Article;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ArticlesListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FirebaseFirestore fStore;
    private ArticleAdapter articleAdapter;
    private List<Article> articleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles_list);

        recyclerView =findViewById(R.id.article_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fStore =FirebaseFirestore.getInstance();

        articleList =new ArrayList<>();
        articleAdapter =new ArticleAdapter(this,articleList);

        recyclerView.setAdapter(articleAdapter);

        ShowData();

    }

    public void ShowData(){
        fStore.collection("Articles").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                articleList.clear();
                for (DocumentSnapshot snapshot : task.getResult()){
                    Article article =new Article(snapshot.getString("id"),
                            snapshot.getString("ArticleTitle"),
                            snapshot.getString("AuthorName"));
                    articleList.add(article);
                }
                articleAdapter.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ArticlesListActivity.this, "Something Went Wrong!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}