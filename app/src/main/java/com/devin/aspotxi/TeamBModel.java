package com.devin.aspotxi;

public class TeamBModel {
    String img,name,play,post;

    public TeamBModel() {
    }

    public TeamBModel(String img, String name, String play, String post) {
        this.img = img;
        this.name = name;
        this.play = play;
        this.post = post;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlay() {
        return play;
    }

    public void setPlay(String play) {
        this.play = play;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
