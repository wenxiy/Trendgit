package com.example.trend.service.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.trend.service.Retrofit.RetrofitHelper;
import com.example.trend.service.Retrofit.RetrofitService;
import com.example.trend.service.entity.Developers;
import com.example.trend.ui.view.DevelopersView;
import com.example.trend.ui.view.View;
import com.google.gson.JsonObject;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DevelopersPresenter implements Presenter {
    private Context mcontext;
    private DevelopersView dataView;
    private Developers mdevelopers;
    private final static  String baseurl="https://ghapi.huchen.dev";
    private CompositeDisposable compositeDisposable;
    private RetrofitHelper retrofitHelper=new RetrofitHelper();

    public DevelopersPresenter(Context context) {
        this.mcontext = context;
    }

    @Override
    public void onCreate() {
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }


    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(View view) {

    }


    public void attachView(DevelopersView view) {
        //这里写和view的联系
        dataView = view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {
        //添加网络请求，线程切换
        getDevelopers();
    }

    public void getDevelopers() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseurl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService retrofitService=retrofit.create(RetrofitService.class);
        retrofitService.getDevelopers()
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
                });
    }
}
