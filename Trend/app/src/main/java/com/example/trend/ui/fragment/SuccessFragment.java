package com.example.trend.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.example.trend.R;
import com.example.trend.service.TrendContract;
import com.example.trend.service.model.Repository;
import com.example.trend.service.presenter.DevelopersPresenter;
import com.example.trend.ui.activity.MainActivity;
import com.example.trend.ui.adapter.ListViewAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class SuccessFragment extends Fragment implements TrendContract.View {
    private RecyclerView mrecyclerview;//recyclerview的建立
    private DevelopersPresenter mdeveloperspresenter;//呈现层的建立
    private List<Repository> developerdatas;//存放数据的List
    private LinearLayoutManager linearLayoutManager;
    private RefreshLayout refreshLayout;
    private ListViewAdapter adpter;
    private SkeletonScreen skeletonScreen;
    private int errorcode = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.success_fragment, container, false);
        refreshLayout = v.findViewById(R.id.refreshLayout);
        mdeveloperspresenter = new DevelopersPresenter(this, errorcode);
        mrecyclerview = v.findViewById(R.id.recyclerview_1);
        Fresco.initialize(getContext());
        mdeveloperspresenter.subscribe();
        //   initdeveloperdatas();
        return v;
    }

    @Override
    public void showdeveloperlist(List<Repository> mdevelopers) {
        developerdatas = new ArrayList<>();//创建数据对象，最后把请求的数据放进去
        developerdatas.addAll(mdevelopers);
        Log.d("TAG", developerdatas.get(0).getName());//test for developerdatas
        Log.d("TAG", "successful");
        adpter = new ListViewAdapter(developerdatas);
        adpter.setOnItemClickListener(new ListViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                //点击事件
            }
        });
        linearLayoutManager = new LinearLayoutManager(getContext());
        mrecyclerview.setLayoutManager(linearLayoutManager);
        mrecyclerview.setAdapter(adpter);
//        developerdatas = null;
//        adpter.notifyDataSetChanged();
//        if (developerdatas == null) {
//            mrecyclerview.setAdapter(adpter);
//        }
//  设置动画,暂时无用
        mrecyclerview.getItemAnimator().setChangeDuration(300);
        mrecyclerview.getItemAnimator().setMoveDuration(300);
        Toast.makeText(getContext(), "成功拉取数据！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showviewerror(int error_code) {
            //利用bundle进行通信
            Intent intent = new Intent(getActivity(), MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("code", error_code);
            intent.putExtras(bundle);
            startActivity(intent);
            Toast.makeText(getContext(), "拉取数据失败", Toast.LENGTH_SHORT).show();
            Log.d("TAG", "Bug");
    }

    public void initdeveloperdatas() {
        //骨架屏
        skeletonScreen = Skeleton.bind(mrecyclerview)
                .adapter(adpter)
                .shimmer(true)
                .angle(30)
                .frozen(false)
                .duration(1500)
                .count(10)
                //.load(R.layout.item_list)
                .show();
        //刷新
        refreshLayout.setRefreshHeader(new MaterialHeader(getContext()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                mdeveloperspresenter.subscribe();
                Toast.makeText(getContext(), "success to refresh", Toast.LENGTH_SHORT).show();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                Toast.makeText(getContext(), "fail to refresh", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
