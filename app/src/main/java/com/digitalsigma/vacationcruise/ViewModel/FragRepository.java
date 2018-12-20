package com.digitalsigma.vacationcruise.ViewModel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import com.digitalsigma.vacationcruise.Data.SQL.MyDao;
import com.digitalsigma.vacationcruise.Data.SQL.MyDb;
import com.digitalsigma.vacationcruise.Model.Cruises;

public class FragRepository {

    private MyDao dao;
    private LiveData<Cruises> allCruises;


    public FragRepository(Application application) {
        MyDb db = MyDb.getInstance(application);
        dao = db.myDao();
    }


    public LiveData<Cruises> getallCruises(int id) {
        allCruises = dao.getCruisesById(id);
        return allCruises;
    }
}
