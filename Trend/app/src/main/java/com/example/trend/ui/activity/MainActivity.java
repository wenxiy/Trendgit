package com.example.trend.ui.activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trend.R;
import com.example.trend.service.entity.Developers;
import com.example.trend.service.entity.Languages_Collection;
import com.example.trend.service.entity.Repositories;
import com.example.trend.service.entity.Spoken_Languages_Collection;
import com.example.trend.service.presenter.DevelopersPresenter;
import com.example.trend.ui.adapter.ListViewAdapter;
import com.example.trend.ui.view.DevelopersView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mrecyclerview;//recyclerview的建立
    private DevelopersPresenter mdeveloperspresenter=new DevelopersPresenter(this);//呈现层的建立
    private List<Languages_Collection> languages_collectiondatas;
    private List<Developers> developerdatas;//存放数据的List
    private List<Repositories> repositorydata;
    private List<Spoken_Languages_Collection> spoken_languages_collectiondatas;
    private TextView textView_1;
    private TextView textView_2;
    private Button button_2;
    private SimpleDraweeView simpleDraweeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView_1=(TextView)this.findViewById(R.id.item_t1);//绑定视图
        textView_2=(TextView)this.findViewById(R.id.item_t2);//绑定视图
        button_2=this.findViewById(R.id.button2);
        simpleDraweeView=this.findViewById(R.id.aver);//绑定视图
        mrecyclerview=(RecyclerView)this.findViewById(R.id.recyclerview_1);//绑定视图
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdeveloperspresenter.getDevelopers();//调用网络请求方法
            }
        });
            initdeveloperdatas();//传送数据并呈现
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);//创建数据菜单
        return super.onCreateOptionsMenu(menu);//呈现菜单
    }

//    private void initView() {//将数据放到ui上
    //   mrecyclerview.setAdapter(madpter);
    //   }

    public void initdeveloperdatas()//拉取Developer的请求，将数据传入view里
    {

        //创建呈现层的对象，调用网络请求方法，与view联系
        mdeveloperspresenter.onCreate();//创建呈现层
        mdeveloperspresenter.attachView(mDevelopersview);
        /*
        以下是一个国外的解决办法，类似于提高响应的时间，但HttpUrlConnection报错，没有这个类，怀疑是OKhttp
        的方法和类
         */
      //  HttpUrlConnection conn = (HttpURLConnection) url.openConnection();
      //  conn.setConnectTimeout(7000);
        developerdatas=new ArrayList<>();//创建数据对象，最后把请求的数据放进去
        //设置布局管理器
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        mrecyclerview.setLayoutManager(linearLayoutManager);
        //假装传入一些自己的数据，看看是recyclerview的错误还是MVp的问题
        //创建适配器
        ListViewAdapter adpter=new ListViewAdapter(developerdatas);
        //设置到Recyclerview
        mrecyclerview.setAdapter(adpter);
    }
    private DevelopersView mDevelopersview=new DevelopersView() {//新建一个developersview层的数据去完成这些请求
        @Override
        public void success(Developers mdevelopers) {
                for(int i=0;i<10;i++) {
                    mdevelopers=new Developers();
                    mdevelopers.getAvatar();
                    mdevelopers.getName();
                    mdevelopers.getUsername();
                    developerdatas.add(mdevelopers);
                }
            //请求成功，利用请求过来的数据mdevelopers去设置数据 textview1是作者名字 2是项目名字 sim是头像
        }



        @Override
        public void error(String result) {
            Log.d("TAG","订阅失败");
            Toast.makeText(getApplicationContext(),"订阅失败",Toast.LENGTH_SHORT).show();
        }
    };

}
