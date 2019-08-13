package com.example.trafficnewsandrules.Models;

public class News {
    String _id, title, imageName, desc;

    public News( String title, String imageName, String desc) {

        this.title = title;
        this.imageName = imageName;
        this.desc = desc;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
