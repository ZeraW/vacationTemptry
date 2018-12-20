package com.digitalsigma.vacationcruise.Model;

/**
 * Created by Hima on 10/22/2018.
 */

public class ContactUsModel {

    String data ;
    int img;

    public ContactUsModel(String data, int img) {
        this.data = data;
        this.img = img;
    }

    public String getData() {
        return data;
    }

    public int getImg() {
        return img;
    }
}
