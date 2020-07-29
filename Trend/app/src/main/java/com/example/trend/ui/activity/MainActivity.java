package com.example.trend.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.example.trend.R;
import com.example.trend.service.TrendContract;
import com.example.trend.service.model.Developers;
import com.example.trend.service.presenter.DevelopersPresenter;
import com.example.trend.ui.adapter.ListViewAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TrendContract.View {
    private RecyclerView mrecyclerview;//recyclerview的建立
    private DevelopersPresenter mdeveloperspresenter;//呈现层的建立
    private List<Developers> developerdatas;//存放数据的List
    private LinearLayoutManager linearLayoutManager;
    private ListViewAdapter adpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mdeveloperspresenter = new DevelopersPresenter(this);
        mrecyclerview = findViewById(R.id.recyclerview_1);
        Fresco.initialize(this);
        mdeveloperspresenter.subscribe();
        initdeveloperdatas();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);//创建数据菜单
        return super.onCreateOptionsMenu(menu);//呈现菜单
    }

    public void initdeveloperdatas()
    {
        //骨架屏
        final SkeletonScreen skeletonScreen = Skeleton.bind(mrecyclerview)
                .adapter(adpter)
                .shimmer(true)
                .angle(30)
                .frozen(false)
                .duration(1500)
                .count(10)
                //.load(R.layout.item_list)
                .show();
    }

    @Override
    public void showdeveloperlist(List<Developers> mdevelopers) {
        developerdatas = new ArrayList<>();//创建数据对象，最后把请求的数据放进去
        developerdatas.addAll(mdevelopers);
        Log.d("TAG", developerdatas.get(0).getName());//test for developerdatas
        Log.d("TAG", "successful");
        adpter = new ListViewAdapter(developerdatas);
        linearLayoutManager = new LinearLayoutManager(this);
        mrecyclerview.setLayoutManager(linearLayoutManager);
        mrecyclerview.setAdapter(adpter);
        Toast.makeText(this, "成功！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showviewerror() {

    }

    @Override
    public void setPresenter(TrendContract.Presenter presenter) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
