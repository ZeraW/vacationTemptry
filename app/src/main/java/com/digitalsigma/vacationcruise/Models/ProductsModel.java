package com.digitalsigma.vacationcruise.Models;

/**
 * Created by Hima on 4/15/2018.
 */

public class ProductsModel {

    String text,title,logo,url;


    public ProductsModel(String title,String text, String logo, String url) {
        this.text = text;
        this.title = title;
        this.logo = logo;
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public String getLogo() {
        return logo;
    }

    public String getUrl() {
        return url;
    }
}
