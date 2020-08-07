package com.example.trend.service.presenter;

import android.util.Log;

import com.example.trend.service.Retrofit.Retrofitmanager;
import com.example.trend.service.TrendContract;
import com.example.trend.service.model.Repository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class DevelopersPresenter implements TrendContract.Presenter {
    private TrendContract.View dataView;
    private List<Repository> mdevelopers;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Observable<List<Repository>> retrofitmanager = Retrofitmanager.getDevelopers();
    private int error_code = 0;

    public DevelopersPresenter(TrendContract.View DeveloperView,int errorcode) {
        dataView = DeveloperView;
        error_code = errorcode;
    }

    public void getDevelopers() {
        compositeDisposable.add(retrofitmanager
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        //onNext
                        developers -> {
                            Log.d("TAG", "data");
                            mdevelopers = developers;
                            dataView.showDeveloperList(mdevelopers);

                        }
                        //error
                        , throwable -> {
                            throwable.printStackTrace();
                            if(throwable instanceof HttpException){
                                HttpException exception = (HttpException) throwable;
                                String message = exception.response().message();
                                error_code = exception.response().code();
                            }
                        }
                ));
    }

    @Override
    public void error() {
        error_code = 0;
    }

    @Override
    public void subscribe() {
        getDevelopers();
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
    }
}
