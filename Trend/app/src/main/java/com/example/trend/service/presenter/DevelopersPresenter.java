package com.example.trend.service.presenter;

import android.util.Log;

import com.example.trend.service.Retrofit.Retrofitmanager;
import com.example.trend.service.TrendContract;
import com.example.trend.service.model.Developers;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class DevelopersPresenter implements TrendContract.Presenter {
    private TrendContract.View dataView;
    private List<Developers> mdevelopers;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    ;
    private Observable<List<Developers>> retrofitmanager = Retrofitmanager.getDevelopers();

    public DevelopersPresenter(TrendContract.View DeveloperView) {
        dataView = DeveloperView;
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
                            dataView.showdeveloperlist(mdevelopers);
                        }
                        //error
                        , throwable -> {
                            throwable.printStackTrace();
                        }
                ));
    }

    @Override
    public void loaddatas() {

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
