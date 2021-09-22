package com.example.mad_project.model;

public class Article {

    String id , title , authorName , article ;

    public Article(){}

    public Article(String id, String title, String authorName){
        this.id =id;
        this.title =title;
        this.authorName =authorName;
    }

    public Article(String id, String title, String authorName, String article){
        this.id =id;
        this.title =title;
        this.authorName =authorName;
        this.article =article;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

}
