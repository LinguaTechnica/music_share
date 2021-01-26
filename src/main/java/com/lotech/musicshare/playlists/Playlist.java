package com.lotech.musicshare.playlists;

import com.lotech.musicshare.songs.Song;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Playlist {
    @Id
    @GeneratedValue
    private Long id;
    private String title;

    @JoinTable
    @OneToMany
    private List<Song> songs;

    public Playlist() {
        songs = new ArrayList<>();
    }

    public Playlist(String title) {
        this.title = title;
        songs = new ArrayList<>();
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(Song song) {
        songs.removeIf(s -> s.getId().equals(song.getId()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
