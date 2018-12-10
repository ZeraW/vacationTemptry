package com.digitalsigma.vacationcruise.Models;

/**
 * Created by Hima on 4/15/2018.
 */

public class MainModel {

    String text,title,logo;


    public MainModel(String title , String text, String logo) {
        this.text = text;
        this.title = title;
        this.logo = logo;

    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

}
