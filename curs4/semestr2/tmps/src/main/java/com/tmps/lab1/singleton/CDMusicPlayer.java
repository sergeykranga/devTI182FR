package com.tmps.lab1.singleton;

import java.util.ArrayList;
import java.util.List;

import com.tmps.lab1.builder.Album;
import com.tmps.lab2.flyweight.AlbumLibrary;
import com.tmps.lab2.adapter.plugs.us.USSocketPlug;
import com.tmps.lab3.observer.MusicObserver;

public class CDMusicPlayer implements MusicPlayer, USSocketPlug {
    public enum MUSIC_PLAYER_STATE {
        PLAYING,
        PAUSED
    }

    private static CDMusicPlayer cdMusicPlayer;

    private Album currentAlbum;
    private final List<MusicObserver> musicObservers;
    private MUSIC_PLAYER_STATE currentState;


    private CDMusicPlayer() {
        musicObservers = new ArrayList<>();
        currentState = MUSIC_PLAYER_STATE.PAUSED;
    }

    public static CDMusicPlayer getInstance() {
        if (cdMusicPlayer == null) {
            cdMusicPlayer = new CDMusicPlayer();
        }

        return cdMusicPlayer;
    }

    @Override
    public boolean powerOn() {
        System.out.println("Powering on CD player...");
        currentState = MUSIC_PLAYER_STATE.PAUSED;
        notifyAllMusicObservers();
        return false;
    }

    @Override
    public void playAlbum(Album album) {
        Album albumToPlay = AlbumLibrary.getAlbum(album);
        System.out.println("Playing album on CD player: " + albumToPlay);
        currentAlbum = albumToPlay;
        currentState = MUSIC_PLAYER_STATE.PLAYING;
        notifyAllMusicObservers();
    }

    @Override
    public void play() {
        System.out.println("Starting playing a song on CD player...");
        currentState = MUSIC_PLAYER_STATE.PLAYING;
        notifyAllMusicObservers();
    }

    @Override
    public void pause() {
        System.out.println("Pausing a song on CD player...");
        currentState = MUSIC_PLAYER_STATE.PAUSED;
        notifyAllMusicObservers();
    }

    @Override
    public Album getCurrentAlbum() {
        return currentAlbum;
    }

    @Override
    public void addObserver(MusicObserver musicObserver) {
        musicObservers.add(musicObserver);
    }

    @Override
    public MUSIC_PLAYER_STATE getCurrentState() {
        return currentState;
    }

    private void notifyAllMusicObservers() {
        for (MusicObserver musicObserver: musicObservers) {
            musicObserver.update();
        }
    }
}
