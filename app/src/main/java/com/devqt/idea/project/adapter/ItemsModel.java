package com.devqt.idea.project.adapter;



public class ItemsModel {


    String name, description, video, icon;

    public ItemsModel(String name, String icon, String video, String description) {
        this.name = name;
        this.icon = icon;
        this.description = description;
        this.video = video;
    }

    public ItemsModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getDescription() {
        return description;
    }

    public String getVideo() {
        return video;
    }
}


