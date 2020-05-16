package com.example.trend.ui.view;

import com.example.trend.service.entity.Developers;

public interface DevelopersView extends View{
    void success(Developers mdevelopers);
    void error(String result);
}
