package com.example.destination;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    public String name,link,desc,profile,thumb;

    public MainViewModel(String name, String link, String desc, String profile,String thumb) {
        this.name = name;
        this.link = link;
        this.desc = desc;
        this.profile = profile;
        this.thumb=thumb;
    }
}
