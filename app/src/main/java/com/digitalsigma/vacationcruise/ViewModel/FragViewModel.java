package com.digitalsigma.vacationcruise.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.digitalsigma.vacationcruise.Model.Cruises;

import java.util.List;

/**
 * Created by Hima on 12/20/2018.
 */

public class FragViewModel extends AndroidViewModel {

    private FragRepository repository;

    private LiveData<Cruises> allCruise;

    public FragViewModel(@NonNull Application application) {
        super(application);
        repository = new FragRepository(application);
    }



    public LiveData<Cruises> getCruises(int id) {
        allCruise = repository.getallCruises(id);
        return allCruise;
    }
}
