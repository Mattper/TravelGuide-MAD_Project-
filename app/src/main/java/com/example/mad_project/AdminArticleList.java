package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mad_project.Helper.TouchHelper;
import com.example.mad_project.adapter.AdminArticleAdapter;
import com.example.mad_project.model.Article;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AdminArticleList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseFirestore fStore;
    private AdminArticleAdapter adapter;
    private List<Article> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_article_list);

        recyclerView =findViewById(R.id.admin_article_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fStore =FirebaseFirestore.getInstance();
        list =new ArrayList<>();
        adapter =new AdminArticleAdapter(this, list);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper touchHelper =new ItemTouchHelper(new TouchHelper(adapter));
        touchHelper.attachToRecyclerView(recyclerView);

        showData();
    }

    public void showData() {
        fStore.collection("Articles").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                list.clear();
                for (DocumentSnapshot snapshot : task.getResult()){
                    Article article =new Article(snapshot.getString("ArticleId"),
                            snapshot.getString("ArticleTitle"),
                            snapshot.getString("AuthorName"));
                    list.add(article);
                }
                adapter.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AdminArticleList.this, "Error!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}