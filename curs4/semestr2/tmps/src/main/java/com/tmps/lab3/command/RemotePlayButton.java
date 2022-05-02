package com.tmps.lab3.command;

import com.tmps.lab1.singleton.MusicPlayer;

public class RemotePlayButton extends MusicPlayerRemoteCommand {

    public RemotePlayButton(MusicPlayer musicPlayer) {
        super(musicPlayer);
    }

    @Override
    public void action() {
        musicPlayer.play();
    }
}
