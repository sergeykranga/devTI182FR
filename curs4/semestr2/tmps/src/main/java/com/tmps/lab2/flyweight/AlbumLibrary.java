package com.tmps.lab2.flyweight;

import java.util.ArrayList;
import java.util.List;

import com.tmps.lab1.builder.Album;

public class AlbumLibrary {
    private static final List<Album> downloadedAlbums = new ArrayList<>();

    public static Album getAlbum(Album album) {
        if (!downloadedAlbums.contains(album)) {
            downloadAlbum(album);
        }
        return downloadedAlbums.get(downloadedAlbums.indexOf(album));
    }

    private static void downloadAlbum(Album album) {
        System.out.println("Downloading album " + album);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Downloaded album " + album);
        downloadedAlbums.add(album);
    }
}
