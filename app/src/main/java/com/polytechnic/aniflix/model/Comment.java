package com.polytechnic.aniflix.model;

public class Comment {
    private String name;
    private String commnet;
    private String urlImage;

    public Comment(String name, String commnet, String urlImage) {
        this.name = name;
        this.commnet = commnet;
        this.urlImage = urlImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommnet() {
        return commnet;
    }

    public void setCommnet(String commnet) {
        this.commnet = commnet;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
