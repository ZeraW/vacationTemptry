package com.digitalsigma.vacationcruise.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.digitalsigma.vacationcruise.Model.Cruises;

import java.util.List;


/**
 * Created by Hima on 12/18/2018.
 */

public class CruisesViewModel extends AndroidViewModel {
    private CruisesRepository repository;
    private LiveData<List<Cruises>> allCruise;

    public CruisesViewModel(@NonNull Application application) {
        super(application);
        repository = new CruisesRepository(application);

    }

    public LiveData<List<Cruises>> getAllUsers(int page) {
        allCruise = repository.getAllUsers(page);
        return allCruise;
    }

    public void clear(){
        repository.onStopClear();
    }
}
