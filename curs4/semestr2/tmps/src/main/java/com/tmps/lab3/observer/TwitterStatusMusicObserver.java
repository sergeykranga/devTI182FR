package com.tmps.lab3.observer;

import com.tmps.lab1.builder.Album;
import com.tmps.lab1.singleton.CDMusicPlayer;
import com.tmps.lab1.singleton.MusicPlayer;

public class TwitterStatusMusicObserver extends MusicObserver {

    public TwitterStatusMusicObserver(MusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
        this.musicPlayer.addObserver(this);
    }

    @Override
    public void update() {
        if (musicPlayer.getCurrentState() == CDMusicPlayer.MUSIC_PLAYER_STATE.PLAYING) {
            Album currentAlbum = musicPlayer.getCurrentAlbum();
            if (currentAlbum != null) {
                String currentAlbumArtist = currentAlbum.getArtist();
                System.out.println("Updating twitter status to: " + currentAlbumArtist + " is rocking!!!");
            }
        }
    }
}
