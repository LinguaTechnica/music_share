package com.lotech.musicshare.playlists;

import com.lotech.musicshare.lib.PlaylistInvalidError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaylistService {
    private final PlaylistRepository repository;

    @Autowired
    public PlaylistService (PlaylistRepository repository) {
        this.repository = repository;
    }

    public Playlist save(Playlist playlist) throws PlaylistInvalidError {
        if (playlist.getTitle() == null) {
            throw new PlaylistInvalidError("A title is required.");
        }
        return repository.save(playlist);
    }

    public Playlist getById(Long playlistId) {
        return repository.findById(playlistId).get();
    }
}
