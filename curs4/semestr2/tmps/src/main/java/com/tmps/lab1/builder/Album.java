package com.tmps.lab1.builder;

public class Album {
    private final String artist;
    private final String name;
    private final String genre;
    private final String year;

    private Album(AlbumBuilder albumBuilder) {
        this.artist = albumBuilder.getArtist();
        this.name = albumBuilder.getName();
        this.genre = albumBuilder.getGenre();
        this.year = albumBuilder.getYear();
    }

    public String getArtist() {
        return artist;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public String getYear() {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return com.google.common.base.Objects.equal(artist, album.artist) && com.google.common.base.Objects.equal(name, album.name) && com.google.common.base.Objects.equal(genre, album.genre) && com.google.common.base.Objects.equal(year, album.year);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(artist, name, genre, year);
    }

    @Override
    public String toString() {
        return "Album{" +
                "artist='" + artist + '\'' +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    public static class AlbumBuilder {
        private final String name;
        private String artist;
        private String genre;
        private String year;

        public AlbumBuilder(String name) {
            this.name = name;
        }

        public AlbumBuilder withArtist(String artist) {
            this.artist = artist;
            return this;
        }

        public AlbumBuilder withGenre(String genre) {
            this.genre = genre;
            return this;
        }

        public AlbumBuilder withYear(String year) {
            this.year = year;
            return this;
        }

        public Album build() {
            return new Album(this);
        }

        String getName() {
            return name;
        }

        String getArtist() {
            return artist;
        }

        String getGenre() {
            return genre;
        }

        String getYear() {
            return year;
        }
    }
}
