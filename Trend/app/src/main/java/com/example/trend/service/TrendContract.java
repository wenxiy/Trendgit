package com.example.trend.service;

import com.example.trend.BasePresenter;
import com.example.trend.BaseView;
import com.example.trend.service.model.Developers;

public interface TrendContract {
    interface View extends BaseView<Presenter>{
        void loadview(Developers mdevelopers);
        void showviewerror();
        void showtrend();
    }
    interface Presenter extends BasePresenter{
        void loaddatas();
    }
}