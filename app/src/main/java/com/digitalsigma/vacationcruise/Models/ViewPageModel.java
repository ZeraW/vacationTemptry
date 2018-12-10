package com.digitalsigma.vacationcruise.Models;

/**
 * Created by Hima on 7/3/2018.
 */

public class ViewPageModel {
    String titles ;
    String descriptions;
    int icons;
    int backgrounds;

    public ViewPageModel(String titles, String descriptions, int icons, int backgrounds) {
        this.titles = titles;
        this.descriptions = descriptions;
        this.icons = icons;
        this.backgrounds = backgrounds;
    }

    public String getTitles() {
        return titles;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public int getIcons() {
        return icons;
    }

    public int getBackgrounds() {
        return backgrounds;
    }
}
