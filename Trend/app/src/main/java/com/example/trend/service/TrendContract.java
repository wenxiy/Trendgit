package com.example.trend.service;

import com.example.trend.BasePresenter;
import com.example.trend.BaseView;

public interface TrendContract {
    interface View extends BaseView<Presenter>{
        void loadview();
        void showviewerror();
        void showtrend();
    }
    interface Presenter extends BasePresenter{
        void loaddatas();
    }
}
