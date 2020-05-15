package com.example.trend.ui.activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.example.trend.R;
import com.example.trend.service.entity.Developers;
import com.example.trend.service.entity.Languages_Collection;
import com.example.trend.service.entity.Repositories;
import com.example.trend.service.entity.Spoken_Languages_Collection;
import com.example.trend.service.presenter.DevelopersPresenter;
import com.example.trend.ui.adapter.ListViewAdapter;
import com.example.trend.ui.adapter.RecyclerViewAdapter;
import com.example.trend.ui.view.DevelopersView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mrecyclerview;
    private DevelopersPresenter mdeveloperspresenter=new DevelopersPresenter(this);
    private List<Languages_Collection> languages_collectiondatas;
    private List<Developers> developerdatas;
    private List<Repositories> repositorydata;
    private List<Spoken_Languages_Collection> spoken_languages_collectiondatas;
    private TextView textView_1;
    private TextView textView_2;
    private SimpleDraweeView simpleDraweeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView_1=(TextView)findViewById(R.id.item_t1);
        textView_2=(TextView)findViewById(R.id.item_t2);
        simpleDraweeView=findViewById(R.id.aver);
        mrecyclerview=(RecyclerView) findViewById(R.id.recyclerview_1);
        initdeveloperdatas();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

//    private void initView() {//将数据放到ui上
    //   mrecyclerview.setAdapter(madpter);
    //   }

    public void initdeveloperdatas()//拉取Developer的请求，将数据传入view里
    {

        //创建呈现层的对象，调用网络请求方法，与view联系
        mdeveloperspresenter.onCreate();//创建呈现层
        mdeveloperspresenter.attachView(mDevelopersview);
        mdeveloperspresenter.getDevelopers();//调用网络请求方法
        developerdatas=new ArrayList<>();//创建
        //设置布局管理器
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        mrecyclerview.setLayoutManager(linearLayoutManager);
        //创建适配器
        ListViewAdapter adpter=new ListViewAdapter(developerdatas);
        //设置到Recyclerview
        mrecyclerview.setAdapter(adpter);
    }
    private DevelopersView mDevelopersview=new DevelopersView() {//新建一个developersview层的数据去完成这些请求
        @Override
        public void success(Developers mdevelopers) {
            for(int i=0;i<10;i++)
            {
                developerdatas.add(mdevelopers);
            }

            //请求成功，利用请求过来的数据mdevelopers去设置数据 textview1是作者名字 2是项目名字 sim是头像
            textView_1.setText(mdevelopers.getUsername());
            textView_2.setText(mdevelopers.getName());
            simpleDraweeView.setImageURI(mdevelopers.getUrl());
        }


        @Override
        public void error(String result) {

        }
    };

}
