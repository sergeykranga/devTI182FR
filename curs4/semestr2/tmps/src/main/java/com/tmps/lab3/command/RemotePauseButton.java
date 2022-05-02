package com.tmps.lab3.command;

import com.tmps.lab1.singleton.MusicPlayer;

public class RemotePauseButton extends MusicPlayerRemoteCommand {

    public RemotePauseButton(MusicPlayer musicPlayer) {
        super(musicPlayer);
    }

    @Override
    public void action() {
        musicPlayer.pause();
    }
}
