package com.example.mad_project.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_project.AdminArticleList;
import com.example.mad_project.R;
import com.example.mad_project.model.Article;

import java.util.List;

public class AdminArticleAdapter extends RecyclerView.Adapter<AdminArticleAdapter.adminArticleViewHolder> {

    private AdminArticleList adminArticleList;
    private List<Article> mList;

    public AdminArticleAdapter(AdminArticleList adminArticleList,List<Article> mList){
        this.adminArticleList =adminArticleList;
        this.mList =mList;
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

            title =itemView.findViewById(R.id.card_articleTitle);
            authorName =itemView.findViewById(R.id.card_articleAuthorName);

        }
    }
}