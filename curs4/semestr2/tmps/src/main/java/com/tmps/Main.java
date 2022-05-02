package com.tmps;

import com.tmps.lab1.factory.MusicPlayerFactory;
import com.tmps.lab1.builder.Album;
import com.tmps.lab2.proxy.SafeUSPlugToEUOutletAdapter;
import com.tmps.lab3.command.MusicPlayerRemote;
import com.tmps.lab1.singleton.MusicPlayer;
import com.tmps.lab2.adapter.outlets.eu.EUSocketOutlet;
import com.tmps.lab2.adapter.plugs.us.USSocketPlug;
import com.tmps.lab3.observer.RoomLightMusicObserver;
import com.tmps.lab3.observer.TwitterStatusMusicObserver;

public class Main {
    public static void main(String[] args) {
        MusicPlayer musicPlayer = MusicPlayerFactory.createMusicPlayer("CD");
        new RoomLightMusicObserver(musicPlayer);
        new TwitterStatusMusicObserver(musicPlayer);

        // plugging in the player
        EUSocketOutlet euSocketOutlet = new EUSocketOutlet() {};

        SafeUSPlugToEUOutletAdapter safeAdapter = new SafeUSPlugToEUOutletAdapter();
        euSocketOutlet.insertPlug(safeAdapter);
        safeAdapter.insertPlug((USSocketPlug) musicPlayer);

        // playing some albums
        Album uh = new Album.AlbumBuilder("Underclass Hero").withArtist("Sum 41").withGenre("Rock").build();
        System.out.println("Playing " + uh + " for the first time");
        musicPlayer.playAlbum(uh);
        System.out.println("Playing " + uh + " for the second time");
        musicPlayer.playAlbum(uh);

        Album ts = new Album.AlbumBuilder("The search").withArtist("NF").withGenre("Rap").build();
        System.out.println("Playing " + ts + " for the first time");
        musicPlayer.playAlbum(ts);
        System.out.println("Playing " + ts + " for the second time");
        musicPlayer.playAlbum(ts);

        // taking a remote for this music player
        MusicPlayerRemote remote = new MusicPlayerRemote(musicPlayer);
        remote.sendVoiceCommand("play");
        remote.sendVoiceCommand("pause");
        remote.sendVoiceCommand("continue");
    }
}
