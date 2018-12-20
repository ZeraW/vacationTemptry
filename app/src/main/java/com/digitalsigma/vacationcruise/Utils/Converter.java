package com.digitalsigma.vacationcruise.Utils;

import android.arch.persistence.room.TypeConverter;

import com.digitalsigma.vacationcruise.Model.Gallery;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

/**
 * Created by Hima on 12/19/2018.
 */

public class Converter {

    @TypeConverter
    public static List<Gallery> fromString(String  value) {
        Type listType = new TypeToken<List<Gallery>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }
    @TypeConverter
    public static String fromArrayLisr(List<Gallery> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

}
