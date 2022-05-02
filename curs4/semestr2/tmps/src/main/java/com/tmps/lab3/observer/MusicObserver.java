package com.tmps.lab3.observer;

import com.tmps.lab1.singleton.MusicPlayer;

public abstract class MusicObserver {
    protected MusicPlayer musicPlayer;
    public abstract void update();
}
