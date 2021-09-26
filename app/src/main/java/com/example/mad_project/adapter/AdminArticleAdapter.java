package com.example.mad_project.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_project.AdminArticleList;
import com.example.mad_project.ArticleDetails;
import com.example.mad_project.R;
import com.example.mad_project.UpdateArticle;
import com.example.mad_project.model.Article;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class AdminArticleAdapter extends RecyclerView.Adapter<AdminArticleAdapter.adminArticleViewHolder> {

    private static  AdminArticleList adminArticleList;
    private static List<Article> mList;
    private FirebaseFirestore fStore =FirebaseFirestore.getInstance();

    public AdminArticleAdapter(AdminArticleList adminArticleList,List<Article> mList){
        AdminArticleAdapter.adminArticleList =adminArticleList;
        AdminArticleAdapter.mList =mList;
    }

    //Updating Article
    public void updateData(int position){
        Article article =mList.get(position);
        Bundle bundle =new Bundle();
        bundle.putString("ArticleId",article.getId());
        bundle.putString("ArticleTitle",article.getTitle());
        bundle.putString("AuthorName",article.getAuthorName());
        bundle.putString("Article",article.getArticle());
        Intent intent =new Intent(adminArticleList, UpdateArticle.class);
        intent.putExtras(bundle);
        adminArticleList.startActivity(intent);
    }

    //Deleting data
    public void deleteData(int position){
        Article article =mList.get(position);
        fStore.collection("Articles").document(article.getId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            notifyRemoved(position);
                            Toast.makeText(adminArticleList, "Article Deleted", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(adminArticleList, "Something went wrong!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(adminArticleList,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void notifyRemoved(int position){
        mList.remove(position);
        notifyItemRemoved(position);
        adminArticleList.showData();
    }


    @NonNull
    @Override
    public adminArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(adminArticleList).inflate(R.layout.article_item, parent,false);
        return new adminArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminArticleAdapter.adminArticleViewHolder holder, int position) {
        holder.title.setText(mList.get(position).getTitle());
        holder.authorName.setText(mList.get(position).getAuthorName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class adminArticleViewHolder extends RecyclerView.ViewHolder{

        TextView title,authorName;

        public adminArticleViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Article article =mList.get(getAbsoluteAdapterPosition());
                    Bundle bundle =new Bundle();
                    bundle.putString("ArticleId",article.getId());
                    bundle.putString("ArticleTitle",article.getTitle());
                    bundle.putString("AuthorName",article.getAuthorName());
                    bundle.putString("Article",article.getArticle());
                    Intent intent =new Intent(adminArticleList, ArticleDetails.class);
                    intent.putExtras(bundle);
                    adminArticleList.startActivity(intent);
                }
            });




            title =itemView.findViewById(R.id.card_articleTitle);
            authorName =itemView.findViewById(R.id.card_articleAuthorName);

        }
    }
}
