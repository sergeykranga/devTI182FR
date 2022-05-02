package com.tmps.lab3.command;

import java.util.HashMap;
import java.util.Map;

import com.tmps.lab1.singleton.MusicPlayer;

public class MusicPlayerRemote {
    private Map<String, MusicPlayerRemoteCommand> commands;

    public MusicPlayerRemote(MusicPlayer musicPlayer) {
        commands = new HashMap<>();
        commands.put("play", new RemotePlayButton(musicPlayer));
        commands.put("pause", new RemotePauseButton(musicPlayer));
    }

    public void sendVoiceCommand(String voiceCommand) {
        if (!commands.containsKey(voiceCommand)) {
            System.out.println("Unrecognized voice command: " + voiceCommand);
        } else {
            commands.get(voiceCommand).action();
        }
    }
}
