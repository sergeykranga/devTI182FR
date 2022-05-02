package com.tmps.lab3.command;

import com.tmps.lab1.singleton.MusicPlayer;

public abstract class MusicPlayerRemoteCommand {
    protected MusicPlayer musicPlayer;

    public MusicPlayerRemoteCommand(MusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
    }

    public abstract void action();
}
