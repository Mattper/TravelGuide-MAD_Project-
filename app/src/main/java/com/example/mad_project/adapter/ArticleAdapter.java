package com.example.mad_project.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_project.ArticlesListActivity;
import com.example.mad_project.R;
import com.example.mad_project.model.Article;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private final ArticlesListActivity activity;
    private final List<Article> mArticleList;

    public ArticleAdapter(ArticlesListActivity activity ,List<Article> mArticleList){
        this.activity =activity;
        this.mArticleList =mArticleList;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.article_item, parent,false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter.ArticleViewHolder holder, int position) {
        holder.title.setText(mArticleList.get(position).getTitle());
        holder.authorName.setText(mArticleList.get(position).getAuthorName());
    }

    @Override
    public int getItemCount() {
        return mArticleList.size();
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder{

        TextView title, authorName;

        public ArticleViewHolder(@NonNull  View itemView) {
            super(itemView);

            title =itemView.findViewById(R.id.card_articleTitle);
            authorName =itemView.findViewById(R.id.card_articleAuthorName);

        }
    }


}
