package com.example.trend.service.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.trend.service.Retrofit.RetrofitHelper;
import com.example.trend.service.Retrofit.RetrofitService;
import com.example.trend.service.Retrofit.Retrofitmanager;
import com.example.trend.service.TrendContract;
import com.example.trend.service.model.Developers;
import com.example.trend.ui.view.DevelopersView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.ListCompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class DevelopersPresenter implements TrendContract.Presenter {
    private Context mcontext;
    private TrendContract.View dataView;
    private Developers mdevelopers;
    private ListCompositeDisposable container;
    private final CompositeDisposable compositeDisposable=new CompositeDisposable();;
    private Observable<Developers> retrofitmanager=Retrofitmanager.getDevelopers();
    public DevelopersPresenter(TrendContract.View view) {
        container=new ListCompositeDisposable();
        this.dataView=view;
    }
    public void getDevelopers() {
        Disposable d=retrofitmanager
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Developers>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("TAG", "订阅");
            }

            @Override
            public void onNext(Developers developers) {
                mdevelopers = developers;
                Log.d("TAG", "请求数据");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                Log.d("TAG", "请求失败");
                dataView.showviewerror();
            }

            @Override
            public void onComplete() {
                if (mdevelopers != null) {
                    dataView.loadview(mdevelopers);
                    //这里去写和view的联系，把数据传过去
                }
                Toast.makeText(mcontext, "请求完成", Toast.LENGTH_SHORT).show();
            }

        }
        );
        container.add(d);
                           }
    @Override
    public void loaddatas() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
    }
}
