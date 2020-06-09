package com.example.trend.service.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.trend.service.Retrofit.RetrofitHelper;
import com.example.trend.service.TrendContract;
import com.example.trend.service.model.Developers;
import com.example.trend.ui.view.DevelopersView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DevelopersPresenter implements TrendContract.Presenter {
    private Context mcontext;
    private DevelopersView dataView;
    private Developers mdevelopers;
    private final CompositeDisposable compositeDisposable=new CompositeDisposable();;
    private final RetrofitHelper retrofitHelper=new RetrofitHelper();
    public DevelopersPresenter(Context context) {
        this.mcontext = context;
    }
    public void getDevelopers() {
        compositeDisposable.
        retrofitHelper.getDevelopers()
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
                                   dataView.OnError("请求失败");
                               }

                               @Override
                               public void onComplete() {
                                   if (mdevelopers != null) {
                                       dataView.success(mdevelopers);
                                       //这里去写和view的联系，把数据传过去
                                   }
                                   Toast.makeText(mcontext, "请求完成", Toast.LENGTH_SHORT).show();
                               }
                           }
    ));
    }

    @Override
    public void attachview(DevelopersView View) {
        dataView=View;
    }

    @Override
    public void detachview() {

    }
}
