package com.digitalsigma.vacationcruise.Utils;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.digitalsigma.vacationcruise.Data.Network.RetrofitAPI;
import com.digitalsigma.vacationcruise.Data.SQL.MyDao;
import com.digitalsigma.vacationcruise.Model.AllCruises;
import com.digitalsigma.vacationcruise.Model.Cruises;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class CruiseDataSource extends PageKeyedDataSource<Integer,Cruises> {

    private static final int FIRST_PAGE = 1;
    private static final String SITE_NAME = "vacationcruise";

    private MyDao dao;
    private RetrofitAPI retrofitAPI;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Cruises> callback) {

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Cruises> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Cruises> callback) {

    }

    private void addUserToDataBase() {
        //Network Request
        compositeDisposable.add(retrofitAPI.getCruises(2)
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
                                        Log.e("dede", "onNext: " + data);
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.e("dede", "onError: " + e.getMessage());
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


}
