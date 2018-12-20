package com.digitalsigma.vacationcruise.Data.SQL;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.digitalsigma.vacationcruise.Model.Cruises;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Hima on 12/18/2018.
 */

@Dao
public interface MyDao {
    @Insert(onConflict = REPLACE)
    void addCruise(Cruises users);

    @Query("SELECT * FROM Cruises") // to read item & SET PRIORITY
    LiveData<List<Cruises>> getCruise();

    @Insert(onConflict = REPLACE)
    void addCruises(List<Cruises> users);

    @Query("SELECT * FROM Cruises WHERE id IN (:ids)")
    LiveData<Cruises> getCruisesById(int ids);

   /* @Delete
    void deleteUser(Cruises users);

    @Update
    void updateUser(Cruises users);

    @Query("SELECT * FROM ListItem WHERE id = :itemId") // to select certain id
    LiveData<LauncherActivity.ListItem> getListItemById(int itemId);

    @Query("DELETE from tablename") // to delete all items
    void deleteAll();*/
}