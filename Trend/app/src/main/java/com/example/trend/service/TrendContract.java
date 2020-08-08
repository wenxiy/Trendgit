package com.example.trend.service;

import com.example.trend.BasePresenter;
import com.example.trend.BaseView;
import com.example.trend.ui.model.Repository;

import java.util.List;

public interface TrendContract {
    interface View extends BaseView<Presenter>{
        void showDeveloperList(List<Repository> mDevelopers);
        void showviewerror(int error_code);
    }
    interface Presenter extends BasePresenter{
        void error();
    }
}
