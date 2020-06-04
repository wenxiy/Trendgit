package com.example.trend.service.presenter;

import android.content.Intent;

import com.example.trend.ui.view.DevelopersView;
import com.example.trend.ui.view.View;

public interface Presenter<T extends DevelopersView>{
    void attachview(T View);
    void detachview();
}
