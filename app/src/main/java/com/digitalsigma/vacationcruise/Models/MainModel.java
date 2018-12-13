package com.digitalsigma.vacationcruise.Models;

import java.util.ArrayList;

/**
 * Created by Hima on 4/15/2018.
 */

public class MainModel {

    private String id,name,description,itinerary,logo,departure;
    private ArrayList<String> imgArray;

    public MainModel(String id, String name, String description, String itinerary, String logo, String departure, ArrayList<String> imgArray) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.itinerary = itinerary;
        this.logo = logo;
        this.departure = departure;
        this.imgArray = imgArray;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getItinerary() {
        return itinerary;
    }

    public String getLogo() {
        return logo;
    }

    public String getDeparture() {
        return departure;
    }

    public ArrayList<String> getImgArray() {
        return imgArray;
    }
}
