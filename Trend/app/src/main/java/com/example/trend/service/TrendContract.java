package com.example.trend.service;

import com.example.trend.BasePresenter;
import com.example.trend.BaseView;
import com.example.trend.service.model.Developers;

import java.util.List;

public interface TrendContract {
    interface View extends BaseView<Presenter>{
        void showdeveloperlist(List<Developers> mdevelopers);
        void showviewerror(boolean error_code);
    }
    interface Presenter extends BasePresenter{
        void error();
    }
}
