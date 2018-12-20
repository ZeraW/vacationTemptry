
package com.digitalsigma.vacationcruise.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllCruises {

    @SerializedName("data")
    @Expose
    private List<Cruises> data = null;


    public List<Cruises> getData() {
        return data;
    }

    public void setData(List<Cruises> data) {
        this.data = data;
    }

}
