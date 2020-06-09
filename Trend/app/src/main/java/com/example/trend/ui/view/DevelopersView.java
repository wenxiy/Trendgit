package com.example.trend.ui.view;

import com.example.trend.service.model.Developers;

public interface DevelopersView extends View{
    void success(Developers mdevelopers);
    void OnError(String result);
}
