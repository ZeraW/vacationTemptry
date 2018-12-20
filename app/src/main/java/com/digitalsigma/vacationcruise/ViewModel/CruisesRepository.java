package com.digitalsigma.vacationcruise.ViewModel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.digitalsigma.vacationcruise.Data.Network.RetrofitAPI;
import com.digitalsigma.vacationcruise.Data.Network.RetrofitClient;
import com.digitalsigma.vacationcruise.Data.SQL.MyDao;
import com.digitalsigma.vacationcruise.Data.SQL.MyDb;
import com.digitalsigma.vacationcruise.Model.AllCruises;
import com.digitalsigma.vacationcruise.Model.Cruises;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Function6;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CruisesRepository {

    private MyDao dao;
    private LiveData<List<Cruises>> allCruises;
    private RetrofitAPI retrofitAPI;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private static String TAG = "dadada";

    public CruisesRepository(Application application) {
        MyDb db = MyDb.getInstance(application);
        dao = db.myDao();
        retrofitAPI = RetrofitClient.getClient(application).create(RetrofitAPI.class);
        allCruises = dao.getCruise();
    }

    public LiveData<List<Cruises>> getAllUsers(int page) {
        addUserToDataBase(page);
        return allCruises;
    }

    private void addUserToDataBase(int page) {
        //Network Request
        compositeDisposable.add(retrofitAPI.getCruises(page)
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSingleObserver<AllCruises>() {
                    @Override
                    public void onSuccess(AllCruises allCruises) {
                        //Save In DataBase
                        Observable<List<Cruises>> userObservable = Observable.fromArray(allCruises.getData());
                        compositeDisposable.add(userObservable
                                .subscribeOn(Schedulers.io())
                                .subscribeWith(new DisposableObserver<List<Cruises>>() {
                                    @Override
                                    public void onNext(List<Cruises> data) {
                                        dao.addCruises(data);
                                        Log.e(TAG, "onNext: " + data);
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.e(TAG, "onError: " + e.getMessage());
                                    }

                                    @Override
                                    public void onComplete() {
                                    }
                                }));

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    public void onStopClear() {
        compositeDisposable.clear();
    }

}
