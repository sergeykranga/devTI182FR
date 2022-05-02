package com.tmps.lab1.singleton;

import com.tmps.lab1.builder.Album;
import com.tmps.lab3.observer.MusicObserver;

public interface MusicPlayer {
    void playAlbum(Album album);
    void play();
    void pause();
    Album getCurrentAlbum();
    void addObserver(MusicObserver musicObserver);
    CDMusicPlayer.MUSIC_PLAYER_STATE getCurrentState();
}
