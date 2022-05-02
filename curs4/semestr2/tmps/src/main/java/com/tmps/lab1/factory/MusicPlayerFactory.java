package com.tmps.lab1.factory;

import com.tmps.lab1.singleton.CDMusicPlayer;
import com.tmps.lab1.singleton.MusicPlayer;

public class MusicPlayerFactory {

    public static MusicPlayer createMusicPlayer(String type) {
        if (type == null || type.isEmpty()) {
            return null;
        }

        switch (type) {
            case "CD": {
                return CDMusicPlayer.getInstance();
            }
            default: {
                return null;
            }
        }
    }
}
