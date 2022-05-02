package com.tmps.lab3.observer;

import com.tmps.lab1.builder.Album;
import com.tmps.lab1.singleton.CDMusicPlayer;
import com.tmps.lab1.singleton.MusicPlayer;

public class RoomLightMusicObserver extends MusicObserver {

    public RoomLightMusicObserver(MusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
        this.musicPlayer.addObserver(this);
    }

    @Override
    public void update() {
        if (musicPlayer.getCurrentState() == CDMusicPlayer.MUSIC_PLAYER_STATE.PAUSED) {
            System.out.println("Music paused, shutting down the lights...");
        } else if (musicPlayer.getCurrentState() == CDMusicPlayer.MUSIC_PLAYER_STATE.PLAYING) {
            Album currentAlbum = musicPlayer.getCurrentAlbum();
            if (currentAlbum != null) {
                String currentMusicGenre = currentAlbum.getGenre();

                if (currentMusicGenre.equals("Rock")) {
                    System.out.println("Observing " + currentMusicGenre + " music, blinking the lights in the room...");
                } else if (currentMusicGenre.equals("Rap")) {
                    System.out.println("Observing " + currentMusicGenre + " music, slowly dimming the lights in the room...");
                } else {
                    System.out.println("Observed " + currentMusicGenre + " music, but we don't know how to react");
                }
            }
        }
    }
}
