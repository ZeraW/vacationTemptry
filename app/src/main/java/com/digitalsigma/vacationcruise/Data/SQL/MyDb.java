package com.digitalsigma.vacationcruise.Data.SQL;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.digitalsigma.vacationcruise.Utils.Converter;
import com.digitalsigma.vacationcruise.Model.Cruises;

/**
 * Created by Hima on 12/18/2018.
 */

@Database(entities = {Cruises.class}, version = 2,exportSchema = false)  // the database class can have more than one table
@TypeConverters({Converter.class})
public abstract class MyDb extends RoomDatabase {
    // to create single tone which mean this Db is created only one time per thread which mean if 2nd thread try to use it will not be building it from start
    private static MyDb instance;

    public abstract MyDao myDao();

    public static synchronized MyDb getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MyDb.class, "cruises_database")
                    .fallbackToDestructiveMigration()   // to remove all the data when upgrade the ver
                    .build();
        }
        return instance;
    }
}
