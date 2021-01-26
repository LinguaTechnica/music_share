package com.lotech.musicshare.playlists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaylistService {
    private PlaylistRepository repository;

    @Autowired
    public PlaylistService (PlaylistRepository repository) {
        this.repository = repository;
    }

    public Playlist save(Playlist playlist) {
        return repository.save(playlist);
    }

    public Playlist getById(Long playlistId) {
        return repository.findById(playlistId).get();
    }

//    public List<Playlist> getByUserId(Long userId) {
//        return repository.findByUserId(userId);
//    }
}
